<%@page import = "java.util.List"%>
<%@page import = "com.preclaim.models.Location"%>
<%
List<Location> activeList = (List<Location>) session.getAttribute("active_location");
session.removeAttribute("active_location");
List<String> user_permission = (List<String>)session.getAttribute("user_permission");
boolean allow_statusChg = user_permission.contains("location/status");
boolean allow_delete = user_permission.contains("locations/delete");
%>
<link href="${pageContext.request.contextPath}/resources/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/resources/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
<style>
@media(max-width:576px)
{
	table thead, table tfoot
	{
		display:none;
	}

	table, table tbody, table tr, table td
	{
		display : block;
		width   : 100%;
	}
	
	table td
	{
		width : 90%;
		text-align: right;
		position   : relative;
		padding-left: 50%;
	}
	table tr
	{
		margin-bottom : 15px;
	}
	table td::before
	{
		 content: attr(data-label);
		 position : absolute;
		 left : 10px;
		 width : 50%;
		 text-align: left;
	}

}


</style>
<div class="row">
	<div class="col-xs-12 col-sm-12">
		<div class="portlet box">
			<div class="portlet-title">
				<div class="caption">
					<i class="icon-check"></i> <span
						class="caption-subject font-green-sharp sbold">Active
						Location</span>
				</div>
				<div class="actions">
					<div class="btn-group">
						<a href="${pageContext.request.contextPath}/location/add"
							data-toggle="tooltip" title="Add"
							class="btn green-haze btn-outline btn-xs pull-right"
							data-toggle="tooltip" title="" style="margin-right: 5px;"
							data-original-title="Add New"> <i class="fa fa-plus"></i>
						</a>
					</div>
				</div>
			</div>
		</div>

		<div class="box box-primary">
			<div class="box-body">
				<div class="row">
					<div class="col-md-12 table-container">
						<div class="box-body no-padding">
							<table id="active_location_list"
								class="table table-striped table-bordered table-hover table-checkable dataTable data-tbl">
								<thead>
									<tr class="tbl_head_bg">
										<th class="head1 no-sort">#</th>
										<th class="head1 no-sort">City</th>
										<th class="head1 no-sort">State</th>
										<th class="head1 no-sort">Zone</th>
										<th class="head1 no-sort">Status</th>
										<th class="head1 no-sort">Action</th>
									</tr>
								</thead>
								<tfoot>
									<tr class="tbl_head_bg">
										<th class="head2 no-sort"></th>
										<th class="head2 no-sort"></th>
										<th class="head2 no-sort"></th>
										<th class="head2 no-sort"></th>
										<th class="head2 no-sort"></th>
										<th class="head2 no-sort"></th>
									</tr>
								</tfoot>
								<tbody>
									<%
									if (activeList != null) {
										int i = 1;
										for (Location list_location : activeList) {
									%>
									<tr>
									     <td data-label = ""><%=i %></td>
									     <td data-label = "City"><%=list_location.getCity() %></td>
									 	 <td data-label = "State"><%=list_location.getState() %></td>
									 	 <td data-label = "Zone"><%=list_location.getZone() %></td>
									     <td data-label = "Status"><%if(list_location.getStatus() == 1){ %>
									     <span class="label label-sm label-success">Active</span>
									     <%}else{ %>
									     	<span class="label label-sm label-danger">Inactive</span>
									     <%} %>
									     </td>
									     <td data-label = "Action">
										     	<a href="${pageContext.request.contextPath}/location/pending?locationId=<%=list_location.getLocationId() %>" 
										     		data-toggle="tooltip" title="Edit" class="btn btn-primary btn-xs">
										     		<i class="glyphicon glyphicon-edit"></i>
									     		</a>
							     	    		
						     	    		<% if(list_location.getStatus() == 1){ %> 
									     			<a href="javascript:;" data-toggle="tooltip" title="Inactive" onClick="return updateLocationStatus('<%=list_location.getLocationId()%>',2,<%=allow_statusChg%>);" 
								     	     		class="btn btn-warning btn-xs"><i class="glyphicon glyphicon-ban-circle"></i></a>  
						     	     		<%}else{%>
						     	     				<a href="javascript:;" data-toggle="tooltip" title="Active" onClick="return updateLocationStatus('<%=list_location.getLocationId()%>',1,<%=allow_statusChg%>);" 
								     	     		class="btn btn-success btn-xs"><i class="glyphicon glyphicon-ok-circle"></i></a>  
								     	    <%} %>    
									     			<a href="javascript:;" data-toggle="tooltip" title="Delete" onClick="return deleteLocation('<%=list_location.getLocationId()%>',1,<%=allow_delete %>);" 
								     	     		class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-remove"></i>
								     	     		</a> 
								     	   
								     	     	
									     </td>
									     
									</tr>

									<%
									i++;
									}
									}
									%>


								</tbody>
							</table>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			<!-- panel body -->
		</div>
	</div>
	<!-- content -->
</div>
<script type="text/javascript">
$(document).ready(function() {
	var i = 0;
	$('#active_location_list tfoot th').each(function() {
						
		if (i == 1 || i == 2 || i == 3 || i == 4 ) {
			$(this).html('<input type="text" class="form-control">');
		}
/* 		else if(i == 4)
		{
			var select_box = "<select class = 'form-control'>";
			select_box += "<option value = '1'>Active</option><option value = '2'>Inactive</option>";
			select_box += "</select>";
		} */
		i++;
	});

	// DataTable
	var table = $('#active_location_list').DataTable();

	// Apply the search
	table.columns().every(function() {						
		var that = this;
		$('input', this.footer()).on('keyup change',function() {
			if (that.search() !== this.value) {
				that.search(this.value).draw();
			}
		});
		$('select', this.footer()).on('change',function() {
			if (that.search() !== this.value) {
				that.search(this.value).draw();
			}
		});
	});
});
</script>