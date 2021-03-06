package com.preclaim.dao;

import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.preclaim.config.CustomMethods;
import com.preclaim.models.IntimationType;
import com.preclaim.models.IntimationTypeList;

public class IntimationTypeDaoImpl implements IntimationTypeDao {

	@Autowired
	DataSource datasource;

	private JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public String add_intimationType(IntimationType intimationType) {
		try 
		{
			String IntimationTypeCheck = "select count(*) from intimation_type where intimationTypeName='" 
					+ intimationType.getIntimationType() + "'";
			int intimationTypeCount = this.template.queryForObject(IntimationTypeCheck, Integer.class);
			/* System.out.println(intimationType.toString()); */
			if (intimationTypeCount == 0) 
			{
				String query = "INSERT INTO intimation_type(intimationTypeName, createdBy, createdDate, updatedDate, "
						+ "updatedBy, status) values(?, ? , getdate(), getdate(), ?, ?)";
				template.update(query, intimationType.getIntimationType(), intimationType.getCreatedBy(), intimationType.getUpdatedBy(),
						intimationType.getStatus());
			}
			else
				return "Intimation Type already exists";
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return e.getMessage();
		}
		return "****";
	}

	@Override
	public List<IntimationTypeList> intimationType_list(int status) {
		String query = "";		
		if(status == 0) 
			query = "SELECT * FROM intimation_type WHERE status = " + status;
		else 
			query = "SELECT * FROM intimation_type WHERE status = 1 or status = 2";
		return this.template.query(query, (ResultSet rs, int rowNum) -> {
			IntimationTypeList intimationTypeList = new IntimationTypeList();
			intimationTypeList.setIntimationId(rs.getInt("intimationTypeId"));
			intimationTypeList.setSrNo(rowNum + 1);
			intimationTypeList.setIntimationType(rs.getString("intimationTypeName"));
			intimationTypeList.setCreatedDate(rs.getString("createdDate"));
			intimationTypeList.setStatus(rs.getInt("status"));
			return intimationTypeList;
		});
	}

	@Override
	public String deleteIntimationType(int intimationId) {
		try 
		{
			String query = "DELETE FROM intimation_type WHERE intimationTypeId = ?";
			template.update(query, intimationId);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			CustomMethods.logError(e);
			return e.getMessage();
		}
		return "****";
	}

	@Override
	public String updateIntimationType(int intimationId, String intimationType, String username) {
		try {
			String IntimationTypeCheck = "select count(*) from intimation_type where intimationTypeName='" + intimationType + "'";
			int intimationTypeCount = this.template.queryForObject(IntimationTypeCheck, Integer.class);
			if(intimationTypeCount > 0)
				return "Intimation Type already exists";
			String sql = "UPDATE intimation_type SET intimationTypeName = ? , updatedDate = getdate(), "
					+ "updatedBy = ? WHERE intimationTypeId = ?";
			template.update(sql, intimationType, username, intimationId);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			CustomMethods.logError(e);
			return e.getMessage();
		}
		return "****";
	}

	@Override
	public String updateIntimationTypeStatus(int intimationId, int status, String username) {
		try 
		{
			String sql="UPDATE intimation_type SET status = ?, updatedDate = getdate(), updatedBy = ? "
					+ "WHERE intimationTypeId = ?";       
			template.update(sql, status, username, intimationId);	
           return  "****";
		}
		catch(Exception e)
		{
			CustomMethods.logError(e);
			return e.getMessage();
		}
	}
		
	@Override
	public List<IntimationType> getActiveIntimationType()
	{
		String sql = "SELECT * FROM intimation_type where status = 1";
		return template.query(sql,
				(ResultSet rs, int rowNum) ->
				{
				
					IntimationType intimation = new IntimationType();
					intimation.setIntimationId(rs.getInt("intimationTypeId"));
					intimation.setIntimationType(rs.getString("intimationTypeName"));
					return intimation;
				}
				);
	}

	@Override
	public List<String> getActiveIntimationTypeStringList() {
		String sql = "SELECT * FROM intimation_type where status = 1";
		return template.query(sql, (ResultSet rs, int rowNum) ->
				{return rs.getString("intimationTypeName");});
	}
	

}
