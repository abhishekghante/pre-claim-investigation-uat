<%@page import = "java.util.List"%>
<%@page import = "com.preclaim.models.CaseCategoryList"%>
<%
List<CaseCategoryList>active_list=(List<CaseCategoryList>)session.getAttribute("active_list");
session.removeAttribute("active_list");
List<String> user_permission=(List<String>)session.getAttribute("user_permission");
boolean allow_statusChg = user_permission.contains("caseCategory/status");
boolean allow_delete = user_permission.contains("caseCategory/delete");
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
           <i class="icon-check"></i>
            <span class="caption-subject font-green-sharp sbold">Active Case Category</span>
        </div>
        <div class="actions">
            <div class="btn-group">
              <a href="${pageContext.request.contextPath}/caseCategory/add" data-toggle="tooltip" title="Add" class="btn green-haze btn-outline btn-xs pull-right" style="margin-right: 5px;" data-original-title="Add New">
                <i class="fa fa-plus"></i>
              </a>
            </div>
        </div>
      </div>
    </div>

    <div class="box box-primary">
      <div class="box-body">
          <div class="row">
            <div>
                <div class="box-body no-padding">
                  <table id="active_group_list" class="table table-striped table-bordered table-hover table-checkable dataTable data-tbl">
                    <thead>
                      <tr class="tbl_head_bg">
                        <th class="head1 no-sort">#</th>
                        <th class="head1 no-sort">Case Status</th>
                        <th class="head1 no-sort">Case Category</th>
                        <th class="head1 no-sort">Created Date</th>
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
                        <th class="head2 no-sort">Status</th>
                        <th class="head2 no-sort"></th>
                      </tr>
                    </tfoot>
                    <tbody>
                             <%
                                 if(active_list!=null){        	
                             		for(CaseCategoryList list_group : active_list){
                              %>
                    		<tr>
                    			<td data-label = ""><%=list_group.getSrNo() %></td>
                    		    <td data-label = "Case Status"><%=list_group.getCaseStatus() %></td>
                    		    <td data-label = "Case Category"><%=list_group.getCaseCategory() %></td>
                    		    <td data-label = "Created Date"><%=list_group.getCreatedDate() %></td>
                    		    <td data-label = "Status">
	                    		    <% if(list_group.getStatus()==1){ %> 
	                    		    	<span class="label label-sm label-success">Active</span>
	                    		    <%}else{%>
	                    		    	<span class="label label-sm label-danger">Inactive</span>
	                    		   <%} %>
                    		    </td>
                    		    <td data-label = "Action">
                    		         <a href="${pageContext.request.contextPath}/caseCategory/pending?caseStatus=<%=list_group.getCaseStatus() %>&caseCategory=<%=list_group.getCaseCategory() %>&caseCategoryId=<%=list_group.getCaseCategoryId() %>" 
                    		         	data-toggle="tooltip" title="Edit" class="btn btn-primary btn-xs">
                    		         	<i class="glyphicon glyphicon-edit"></i>
                   		         	</a>
               		         	  	<% if(list_group.getStatus()==1){ %> 
	                    		         <a href="javascript:;" data-toggle="tooltip" title="Inactive" onClick="return updateCaseCategoryStatus('<%=list_group.getCaseCategoryId() %>',2,<%=allow_statusChg%>);" 
	                    		             class="btn btn-warning btn-xs"><i class="glyphicon glyphicon-ban-circle"></i>               		            
	                   		             </a>
               		               	<%}else{%>
	                  		              <a href="javascript:;" data-toggle="tooltip" title="Active" onClick="return updateCaseCategoryStatus('<%=list_group.getCaseCategoryId() %>',1,<%=allow_statusChg%>);" 
	                   		            	 class="btn btn-success btn-xs"><i class="glyphicon glyphicon-ok-circle"></i>
	                  		              </a>
               		                <%} %>
                     		         <a href="javascript:;" data-toggle="tooltip" title="Delete" onClick="return deleteCaseCategory('<%=list_group.getCaseCategoryId() %>',<%=allow_delete %>);" 
                    		             class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-remove"></i>
                   		             </a>
                    		    </td>
                    		</tr>
                    		
                    		<%
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
      </div><!-- panel body -->
    </div>
  </div><!-- content -->
</div>
<script type="text/javascript">
$(document).ready(function() {
  var i = 0;
  $('#active_group_list tfoot th').each( function () {
    if( i == 1 || i == 3 ){
      $(this).html( '<input type="text" class="form-control" placeholder="" />' );
    }
    i++;
  });

  // DataTable
  var table = $('#active_group_list').DataTable();

  // Apply the search
  table.columns().every( function () {
    var that = this;
    $( 'input', this.footer() ).on( 'keyup change', function () {
      if ( that.search() !== this.value ) {
        that.search( this.value ).draw();
      }
    });
    $( 'select', this.footer() ).on( 'change', function () {
      if ( that.search() !== this.value ) {
        that.search( this.value ).draw();
      }
    });
  });
});
</script>