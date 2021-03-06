package com.preclaim.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.preclaim.config.Config;
import com.preclaim.dao.LoginDAO;
import com.preclaim.dao.MailConfigDao;
import com.preclaim.dao.UserDAO;
import com.preclaim.models.Login;
import com.preclaim.models.MailConfig;
import com.preclaim.models.UserDetails;

@Controller
public class LoginController {

	@Autowired
	LoginDAO dao;

	@Autowired
	UserDAO userDao;

	@Autowired
	MailConfigDao mailConfigDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login() {
		return "common/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login_method() {
		return "common/login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("User_Login");
		session.removeAttribute("user_role");
		return "common/login";
	}

	@RequestMapping(value = "/login_validate", method = RequestMethod.POST)
	public @ResponseBody String login_validate(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		String username = request.getParameter("username");
		Base64.Encoder encoder = Base64.getEncoder();
		String password = encoder.encodeToString(request.getParameter("password").getBytes());
		Login login = new Login(username, password);
		UserDetails user = dao.validateUser(login);
		 
		if (user != null) {
			if (user.getStatus() == 0)
				return "User ID disabled. Kindly contact system administrator";
			session.setAttribute("User_Login", user);
			session.setAttribute("user_role", userDao.getUserRole(user.getAccount_type()));
			List<String> user_permission = userDao.retrievePermission(user.getAccount_type());
			session.setAttribute("user_permission", user_permission);
			String remember_me = request.getParameter("remember_me");
			if (remember_me.equals("true")) {
				Cookie cookie_username = new Cookie("cHJlLWNsYWltLXVzZXI", username);
				Cookie cookie_password = new Cookie("cHJlLWNsYWltLXBhc3N3b3Jk", password);
				cookie_username.setMaxAge(60 * 60 * 24 * 365 * 10);
				cookie_password.setMaxAge(60 * 60 * 24 * 365 * 10);
				response.addCookie(cookie_username);
				response.addCookie(cookie_password);
			}
			return "****";
		} else
			return "Invalid Username or Password";
	}

	@RequestMapping(value = "/forgotpass", method = RequestMethod.GET)
	public String forgotpass(HttpSession session) {
		return "common/forgotpass";
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody String fileuploader(@RequestParam("file[]") ArrayList<MultipartFile> multipartFiles,
			HttpServletRequest request) {
		String prefix = request.getParameter("prefix");
		for (MultipartFile item : multipartFiles) {
			try {
				byte[] temp = item.getBytes();
				String filename = item.getOriginalFilename();
				filename = prefix == null ? filename : prefix + "_" + filename;
				Path path = Paths.get(Config.upload_directory + filename);
				Files.write(path, temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "****";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public @ResponseBody String changePassword(HttpServletRequest request) {
		String username = request.getParameter("username");

		Encoder encoder = Base64.getEncoder();
		String pass = RandomStringUtils.random(6, true, true);
		UserDetails user = dao.checkUser(username);
		if (user == null)
			return "Username not found";
		if (user.getUser_email().equals(""))
			return "Email ID not present. Kindly contact system administrator to reset the password";

		MailConfig mail = mailConfigDao.getActiveConfig();
		if (mail == null)
			return "Mail ID not setup. Kindly contact system administrator";
		mail.setReceipent(user.getUser_email());
		mail.setSubject("Forgot Password - " + user.getUsername());
		user.setPassword(encoder.encodeToString(pass.getBytes()));
		mail.setMessageBody("Your resetted password is " + pass);
		String message = mailConfigDao.sendMail(mail);

		if (!message.equals("****"))
			return message;
		message = dao.updatePassword(username, encoder.encodeToString(pass.getBytes()));
		return message;
	}

}
