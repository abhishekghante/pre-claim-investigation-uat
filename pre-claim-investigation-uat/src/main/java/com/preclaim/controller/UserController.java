package com.preclaim.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.preclaim.config.CustomMethods;
import com.preclaim.dao.LocationDao;
import com.preclaim.dao.MailConfigDao;
import com.preclaim.dao.UserDAO;
import com.preclaim.models.MailConfig;
import com.preclaim.models.ScreenDetails;
import com.preclaim.models.UserDetails;
import com.preclaim.models.UserList;
import com.preclaim.models.UserRole;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserDAO dao;
	
	@Autowired
	LocationDao locationDao;
	
	@Autowired
	MailConfigDao mailConfigDao;
	
	@RequestMapping(value = "/add_user", method = RequestMethod.GET)
	public String add_user(HttpSession session,Model m)
	{
		UserDetails user = (UserDetails) session.getAttribute("User_Login");
		if(user == null)
			return "common/login";
		
		session.removeAttribute("ScreenDetails");    	
		ScreenDetails details = new ScreenDetails();
    	details.setScreen_name("../user/add_user.jsp");
    	details.setScreen_title("Manage User");
    	details.setMain_menu("Users");
    	details.setSub_menu1("Add User");
    	details.setSub_menu2("Manage Users");
    	details.setSub_menu2_path("/user/user_list");
    	session.setAttribute("ScreenDetails", details);    	
    	List<UserRole> role = dao.role_lists();
    	m.addAttribute("role_list", role);
    	session.setAttribute("location_list", locationDao.getActiveLocationList());
    	
		return "common/templatecontent";
	}
	
	@RequestMapping(value = "/create_user", method = RequestMethod.POST)
	public String create_user(@ModelAttribute("user_details") UserDetails user, 
			HttpSession session, HttpServletRequest request)
	{
		UserDetails userlogin = (UserDetails) session.getAttribute("User_Login");
		user.setCreatedBy(userlogin.getUsername());
		String message = dao.create_user(user);
		System.out.println(user.toString());
		session.removeAttribute("ScreenDetails");    	
		ScreenDetails details = new ScreenDetails();
    	details.setScreen_name("../user/user_list.jsp");
    	details.setScreen_title("User List");
    	details.setMain_menu("Users");
    	details.setSub_menu1("User Lists");
    	if(message.equals("****"))
    	{
    		
    		try {
				MailConfig mail = mailConfigDao.getActiveConfig();
				if (mail != null) {
					// From ID
					mail.setSubject("User Registration");
					String message_body = "Dear <User>, \n Registration successfully done. Your Login Credential are Below \n Username :- "+user.getUsername() +"\n Pass :- "+user.getDecodedPassword()+"\n";
					message_body = message_body.replaceAll("<User>", user.getFull_name());
					message_body += "Thanks & Regards,\n Claims";
					mail.setMessageBody(message_body);
					mail.setReceipent(user.getUser_email());
					mailConfigDao.sendMail(mail);

				}
			} catch (Exception e) {
				CustomMethods.logError(e);
			}
    		
    		
    		details.setSuccess_message1("User created successfully");
    		dao.activity_log("USER", user.getUsername(), "ADD", userlogin.getUsername());
    	}    		
    	else
    		details.setError_message1(message);
    	session.setAttribute("ScreenDetails", details);
    	
    	List<UserList> user_list = dao.user_list();
    	session.setAttribute("user_list",user_list);
    	List<UserRole> role = dao.role_lists();
    	session.setAttribute("role_list", role);  	
    	
		return "common/templatecontent";
	}
	
	@RequestMapping(value = "/user_list", method = RequestMethod.GET)
	public String user_list(HttpSession session)
	{
		UserDetails user = (UserDetails) session.getAttribute("User_Login");
		if(user == null)
			return "common/login";
		
		session.removeAttribute("ScreenDetails");    	
		ScreenDetails details = new ScreenDetails();
    	details.setScreen_name("../user/user_list.jsp");
    	details.setScreen_title("User List");
    	details.setMain_menu("Users");
    	details.setSub_menu1("User Lists");
    	if(session.getAttribute("success_message") != null)
    	{
    		details.setSuccess_message1((String)session.getAttribute("success_message"));
    		session.removeAttribute("success_message");
    	}
    	session.setAttribute("ScreenDetails", details);
    	List<UserList> user_list = dao.user_list();
    	session.setAttribute("user_list",user_list);
    	List<UserRole> role = dao.role_lists();
    	session.setAttribute("role_list", role);  	
    	return "common/templatecontent";
	}
	
	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public String role(HttpSession session, Model m)
	{
		UserDetails user = (UserDetails) session.getAttribute("User_Login");
		if(user == null)
			return "common/login";
		
		session.removeAttribute("ScreenDetails");    	
		ScreenDetails details = new ScreenDetails();
    	details.setScreen_name("../role/add_role.jsp");
    	details.setScreen_title("Role lists");
    	details.setMain_menu("Users");
    	details.setSub_menu1("User Role");
    	if(session.getAttribute("success_message") != null)
    	{
    		details.setSuccess_message1((String)session.getAttribute("success_message"));
    		session.removeAttribute("success_message");
    	}
    	session.setAttribute("ScreenDetails", details);
    	List<UserRole> role = dao.role_lists();
    	m.addAttribute("role_list", role);
		return "common/templatecontent";
	}
	
	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	public @ResponseBody String add_role(HttpSession session, HttpServletRequest request)
	{
		UserDetails user = (UserDetails) session.getAttribute("User_Login");
		if(user == null)
			return "common/login";
		UserRole role = new UserRole();
		role.setRole(request.getParameter("role"));
		role.setRole_code(request.getParameter("role_code"));
		role.setStatus(1);
		String message = dao.create_role(role);
		if(message.equals("****"))
		{
			session.setAttribute("success_message", "Role added successfully");
			dao.activity_log("ROLE", role.getRole_code(), "ADD", user.getUsername());
		}
		return message;
	}
	
	@RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
	public @ResponseBody String delete_role(HttpSession session, HttpServletRequest request)
	{
		UserDetails user = (UserDetails) session.getAttribute("User_Login");
		if(user == null)
			return "common/login";
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		String message = dao.delete_role(roleId);
		if(message.equals("****"))
		{
			session.setAttribute("success_message", "Role & permission deleted successfully");
			dao.activity_log("ROLE", String.valueOf(roleId), "DELETE", user.getUsername());
			dao.activity_log("PERMISSION", String.valueOf(roleId), "DELETE", user.getUsername());
		}
		return message;
	}
	
	@RequestMapping(value = "/updateRole", method = RequestMethod.POST)
	public @ResponseBody String update_role(HttpSession session, HttpServletRequest request)
	{
		UserDetails user = (UserDetails) session.getAttribute("User_Login");
		if(user == null)
			return "common/login";
		UserRole role = new UserRole();
		role.setRoleId(Integer.parseInt(request.getParameter("roleId")));
		role.setRole_code(request.getParameter("role_code"));
		role.setRole(request.getParameter("role"));
		System.out.println(role.toString());
		String message = dao.updateUserRole(role);
		if(message.equals("****"))
		{
			session.setAttribute("success_message", "Role updated successfully");
			dao.activity_log("ROLE", role.getRole_code(), "UPDATE", user.getUsername());
		}
		return message;
	}
	@RequestMapping(value = "/updateUserStatus", method = RequestMethod.POST)
	public @ResponseBody String updateUserStatus(HttpSession session, HttpServletRequest request)
	{
		UserDetails user = (UserDetails) session.getAttribute("User_Login");
		if(user == null)
			return "common/login";
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int user_status = Integer.parseInt(request.getParameter("status"));
		System.out.println("User ID:" + user_id + " User_Status:" + user_status);
		String message = dao.updateUserStatus(user_id, user_status, user.getUsername());
		if(message.equals("****"))
		{
			session.setAttribute("success_message", "User status changed successfully");
			dao.activity_log("USER",String.valueOf(user_id), user_status == 1 ? "ACTIVE" : "DEACTIVE", 
					user.getUsername());
		}
		return message;
	}
	
	@RequestMapping(value = "/deleteAdminUser", method = RequestMethod.POST)
	public @ResponseBody String deleteAdminUser(HttpSession session, HttpServletRequest request)
	{
		UserDetails user = (UserDetails) session.getAttribute("User_Login");
		if(user == null)
			return "common/login";
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		System.out.println("User ID:" + user_id);
		String message = dao.deleteAdminUser(user_id);
		if(message.equals("****"))
		{
			session.setAttribute("success_message", "User deleted successfully");
			dao.activity_log("USER",String.valueOf(user_id), "DELETE", user.getUsername());
		}
		return message;
	}
	
	@RequestMapping(value = "/edit/{UserID}", method = RequestMethod.GET)
	public String edit(@PathVariable("UserID") int UserID, HttpSession session, Model m)
	{
		UserDetails user = (UserDetails) session.getAttribute("User_Login");
		if(user == null)
			return "common/login";
		session.removeAttribute("ScreenDetails");    	
		ScreenDetails details = new ScreenDetails();
    	details.setScreen_name("../user/edit_user.jsp");
    	details.setScreen_title("Manage User");
    	details.setMain_menu("Users");
    	details.setSub_menu1("Add User");
    	details.setSub_menu2("Edit Account");
    	details.setSub_menu2_path("/user/user_list");
    	if(session.getAttribute("success_message") != null)
    	{
    		details.setSuccess_message1((String)session.getAttribute("success_message"));
    		session.removeAttribute("success_message");
    	}
    	session.setAttribute("ScreenDetails", details);    	
    	List<UserRole> role = dao.role_lists();
    	session.setAttribute("role_list", role);
    	UserDetails user_details = dao.getUserDetails(UserID);
    	session.setAttribute("user_details",user_details);
    	session.setAttribute("location_list", locationDao.getActiveLocationList());
    	
    	return "common/templatecontent";
	}
	
	@RequestMapping(value = "/updateUserDetails", method = RequestMethod.POST)
	public @ResponseBody String updateUserDetails(HttpSession session, HttpServletRequest request)
	{
		UserDetails user = (UserDetails) session.getAttribute("User_Login");
		if(user == null)
			return "common/login";
		UserDetails user_details = new UserDetails();
		user_details.setUserID(Integer.parseInt(request.getParameter("user_id")));
		user_details.setFull_name(request.getParameter("full_name"));
		user_details.setAccount_type(request.getParameter("account_type"));
		user_details.setPassword(request.getParameter("password"));
		user_details.setStatus(Integer.parseInt(request.getParameter("status")));
		user_details.setUser_email(request.getParameter("user_email"));
		user_details.setUsername(request.getParameter("username"));
		user_details.setUserimage(request.getParameter("account_img"));	
		user_details.setCity(request.getParameter("city"));
		user_details.setState(request.getParameter("state"));
		user_details.setAddress1(request.getParameter("address1"));
		user_details.setAddress2(request.getParameter("address2"));
		user_details.setAddress3(request.getParameter("address3"));
		user_details.setContactNumber(request.getParameter("contactNumber"));
		user_details.setUpdatedBy(user.getUsername());
		String message = dao.updateUserDetails(user_details);
		if(message.equals("****"))
		{
			session.setAttribute("success_message", "User Details updated successfully");
			dao.activity_log("USER",user_details.getUsername(), "UPDATE", user_details.getUsername());
		}
		return message;
	}
	
	@RequestMapping(value = "/permission/{roleCode}", method = RequestMethod.GET)
	public String permission(@PathVariable("roleCode") String roleCode, HttpSession session)
	{
		UserDetails user = (UserDetails) session.getAttribute("User_Login");
		if(user == null)
			return "common/login";
		session.removeAttribute("ScreenDetails");    	
		ScreenDetails details = new ScreenDetails();
    	details.setScreen_name("../role/add_permission_form.jsp");
    	details.setScreen_title("Manage Permission");
    	session.setAttribute("ScreenDetails", details);
    	List<String> role_permission = dao.retrievePermission(roleCode);
    	session.setAttribute("role_code", String.valueOf(roleCode));
    	session.setAttribute("permission", role_permission);
        session.setAttribute("user role",dao.getUserRole(roleCode)); 
    	List<UserRole> role = dao.role_lists();
    	session.setAttribute("role_list", role);
    	return "common/templatecontent";
	}
	
	@RequestMapping(value = "/addPermission", method = RequestMethod.POST)
	public @ResponseBody String addPermission(HttpSession session, HttpServletRequest request)
	{
		UserDetails user = (UserDetails) session.getAttribute("User_Login");
		if(user == null)
			return "common/login";
		String role_code = request.getParameter("role_code");
		Enumeration<String> parameterNames = request.getParameterNames();
		List<String> role_permission = new ArrayList<String>();
		while (parameterNames.hasMoreElements()) 
		{
			String paramName = parameterNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			for (int i = 0; i < paramValues.length; i++) {
				String paramValue = paramValues[i];
				role_permission.add(paramValue);
			}
		}
		role_permission.remove(role_permission.size() - 1);
		String message = dao.addPermission(role_permission, role_code);
		if(message.equals("****"))
			dao.activity_log("PERMISSION",role_code, "ADD", user.getUsername());
		return message;
	}
	
	@RequestMapping(value = "/accountValidate", method = RequestMethod.POST)
	public @ResponseBody String accountValidate(HttpServletRequest request)
	{
		String username = request.getParameter("username"); 
		System.out.println(username);
		return dao.accountValidate(username);
	}
	
}
