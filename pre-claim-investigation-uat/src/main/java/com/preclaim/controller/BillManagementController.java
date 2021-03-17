package com.preclaim.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.preclaim.config.Config;
import com.preclaim.dao.BillingManagementDao;
import com.preclaim.dao.IntimationTypeDao;
import com.preclaim.dao.InvestigationTypeDao;
import com.preclaim.models.ScreenDetails;
import com.preclaim.models.UserDetails;

@Controller
@RequestMapping(value = "/billManagement")
public class BillManagementController {
		
	@Autowired 
	BillingManagementDao billingDao;

	@Autowired
	InvestigationTypeDao investigationDao;

	@Autowired
	IntimationTypeDao intimationTypeDao;
	
	@RequestMapping(value = "/bill_enquiry", method = RequestMethod.GET)  
	public String pending_message(HttpSession session) 
	{  	  
		UserDetails user = (UserDetails) session.getAttribute("User_Login");
		if(user == null)
			return "common/login";
		
		session.removeAttribute("ScreenDetails");
		ScreenDetails details = new ScreenDetails();
		details.setScreen_name("../billManagement/bill_enquiry.jsp");
		details.setScreen_title("Bill Enquiry Lists");
		details.setMain_menu("Billing Management");			
		details.setSub_menu1("Bill Enquiry");
    	
		if(session.getAttribute("success_message") != null)
		{
			details.setSuccess_message1((String)session.getAttribute("success_message"));	
			session.removeAttribute("success_message");
		}
		session.setAttribute("ScreenDetails", details);
		session.setAttribute("billingEnquiryList",billingDao.billEnquiryList());
		session.setAttribute("investigation_list", investigationDao.getActiveInvestigationList());
		session.setAttribute("intimation_list", intimationTypeDao.getActiveIntimationType());
		
		return "common/templatecontent";     
	}
	
	  @RequestMapping(value = "/bill_payment",method = RequestMethod.GET)	
	  public String bill_payment(HttpSession session, HttpServletRequest request) 
	  {	
		  UserDetails user = (UserDetails) session.getAttribute("User_Login");	
		  if(user == null)
			  return "common/login";
			
		  session.removeAttribute("ScreenDetails");
		  ScreenDetails details=new ScreenDetails();
		  details.setScreen_name("../billManagement/bill_payment.jsp");
		  details.setScreen_title("Bill Payment Lists");
		  details.setMain_menu("Billing Management");
		  details.setSub_menu1("Bill Payment");
		  if(session.getAttribute("success_message") != null)
		  {
			  details.setSuccess_message1((String)session.getAttribute("success_message"));
			  session.removeAttribute("success_message");
		  }
		  session.setAttribute("ScreenDetails", details);
		  session.setAttribute("billingPendingList",billingDao.billPaymentList());
		  session.setAttribute("investigation_list", investigationDao.getActiveInvestigationList());
		  session.setAttribute("intimation_list", intimationTypeDao.getActiveIntimationType());
			
		  return "common/templatecontent";
	  } 
	  
	  @RequestMapping(value = "/getCheckboxValue",method = RequestMethod.POST)
	  public @ResponseBody String getCheckboxValue(HttpSession session, HttpServletRequest request) throws IOException 
	  {
		  UserDetails user = (UserDetails) session.getAttribute("User_Login");   
		  String tempStr[] = request.getParameterValues("selected[]");
		  String selectedValues = "";
		  for(String values: tempStr) 
		  	  selectedValues += values + ",";
		  selectedValues = selectedValues.substring(0, selectedValues.length()-1); 
		  //Create blank workbook
		  XSSFWorkbook workbook = new XSSFWorkbook();  
		  XSSFCellStyle style1 = workbook.createCellStyle();
		  //Create a blank sheet        
		  XSSFSheet spreadsheet = workbook.createSheet( "Claims Info");       
		  //Create row object         
		  XSSFRow row;


		  //This data needs to be written (Object[])         
		  Map<Integer, Object[]> empinfo = billingDao.billPaymentList(selectedValues);   
		  billingDao.UpdateFees(selectedValues, user.getUsername()); 
		  //Iterate over data and write to sheet
	        
		  Set<Integer> keyid = empinfo.keySet();
		  int rowid = 0;
		  for (Integer key : keyid) 
		  { 
			  row = spreadsheet.createRow(rowid++); Object []
		      objectArr = empinfo.get(key); int cellid = 0;
		   
		      for (Object obj : objectArr)		    
		      { 
		    	  Cell cell = row.createCell(cellid++);
		    	    style1.setBorderLeft(BorderStyle.THIN);  
		            style1.setRightBorderColor(IndexedColors.BLACK.getIndex());
		            style1.setBorderRight(BorderStyle.THIN);  
		            style1.setRightBorderColor(IndexedColors.BLACK.getIndex());
		            style1.setBorderTop(BorderStyle.THIN);  
		            style1.setRightBorderColor(IndexedColors.BLACK.getIndex());
		            style1.setBorderBottom(BorderStyle.THIN);  
		            style1.setRightBorderColor(IndexedColors.BLACK.getIndex());
					cell.setCellStyle(style1);
		    	  cell.setCellValue(obj.toString()); 
		      } 
		  }
		  
		  spreadsheet.autoSizeColumn(1);
		  spreadsheet.autoSizeColumn(2);
		  spreadsheet.autoSizeColumn(3);
		  spreadsheet.autoSizeColumn(4);
		  spreadsheet.autoSizeColumn(5);
		  spreadsheet.autoSizeColumn(6);
		  spreadsheet.autoSizeColumn(7);
		  spreadsheet.autoSizeColumn(8);
		  spreadsheet.autoSizeColumn(9);
		  spreadsheet.autoSizeColumn(10);
		  spreadsheet.autoSizeColumn(11);
		  spreadsheet.autoSizeColumn(12);
		  spreadsheet.autoSizeColumn(13);
		  spreadsheet.autoSizeColumn(14);
		  spreadsheet.autoSizeColumn(15);
		  spreadsheet.autoSizeColumn(16);
		  spreadsheet.autoSizeColumn(17);
		  spreadsheet.autoSizeColumn(18);
		  spreadsheet.autoSizeColumn(19);
		  String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh-mm-ss"));
		  
		  //Write the workbook in file system
		  String filename = "Bill Payment_" + currentDate + ".xlsx";
		  FileOutputStream out = new FileOutputStream( new
		  File(Config.upload_directory + filename));
		  
		  workbook.write(out); 
		  out.close();
		  workbook.close();
		  return filename; 
	  }		
} 
	  

