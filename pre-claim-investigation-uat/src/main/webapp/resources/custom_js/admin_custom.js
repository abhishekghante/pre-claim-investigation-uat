//Delete Intimation Type
function deleteIntimationType( intimationId, checkAuthority ) {
	if(!checkAuthority)
	{
		toastr.error("Access Denied", "Error");
		return false;
	}
    $( '#small_modal' ).modal();
    $( '#sm_modal_title' ).html( 'Are you Sure?' );
    $( '#sm_modal_body' ).html( 'Do you really want to delete this record?' );
    $( '#sm_modal_footer' ).html( '<button type="button" class="btn dark btn-outline" data-dismiss="modal">Cancel</button><button type="button" id="continuemodal'+intimationId+'" class="btn green">Yes</button>' );
    $( '#continuemodal'+intimationId ).click( function() {
        $.ajax({
            type : 'POST',
            url  : 'deleteIntimationType',
            data : { 'IntimationId' : intimationId },
            beforeSend: function() { 
                $("#continuemodal"+intimationId).html('<img src="../resources/img/input-spinner.gif"> Loading...');
                $("#continuemodal"+intimationId).prop('disabled', true);
            },
            success : function( msg ) 
            {
                $("#continuemodal"+intimationId).html('Yes');
                $("#continuemodal"+intimationId).prop('disabled', false);
                $('#small_modal').modal('hide');
                if(msg == "****")
                {
                	location.reload();
                }
                else
                	toastr.error(msg,"Error");
            }
        });
    });
}
function updateIntimationTypeStatus( intimationId, status, checkAuthority ) {
	if(!checkAuthority)
	{
		toastr.error("Access Denied", "Error");
		return false;
	}
    if(status == 1){
        $( '#sm_modal_body' ).html( 'Do you really want to activate?' );
    }else{
        $( '#sm_modal_body' ).html( 'Do you really want to deactivate?' );
    }
    $( '#small_modal' ).modal();
    $( '#sm_modal_title' ).html( 'Are you Sure?' );
    $( '#sm_modal_footer' ).html( '<button type="button" class="btn dark btn-outline" data-dismiss="modal">Cancel</button><button type="button" id="continuemodal'+intimationId+'" class="btn green">Yes</button>' );
    $( '#continuemodal'+intimationId ).click( function() {
        $.ajax({
            type : 'POST',
            url  : 'updateIntimationTypeStatus',
            data : { 'IntimationId' : intimationId, 'status' : status },
            beforeSend: function() { 
                $("#continuemodal"+intimationId).html('<img src="../resources/img/input-spinner.gif"> Loading...');
                $("#continuemodal"+intimationId).prop('disabled', true);
            },
            success : function( msg ) 
            {
                $("#continuemodal"+intimationId).html('Yes');
	            $("#continuemodal"+intimationId).prop('disabled', false);
	            $('#small_modal').modal('hide');		            
                if(msg="****")
                {
                  	location.reload();
	            }
	            else
	            	 toastr.error(msg,'Error');
            }
        });
    });
}
//Delete Case Status
function deleteCaseStatus( caseStatusId, checkAuthority ) {
	if(!checkAuthority)
	{
		toastr.error("Access Denied", "Error");
		return false;
	}
    $( '#small_modal' ).modal();
    $( '#sm_modal_title' ).html( 'Are you Sure?' );
    $( '#sm_modal_body' ).html( 'Do you really want to delete this record?' );
    $( '#sm_modal_footer' ).html( '<button type="button" class="btn dark btn-outline" data-dismiss="modal">Cancel</button><button type="button" id="continuemodal'+caseStatusId+'" class="btn green">Yes</button>' );
    $( '#continuemodal'+caseStatusId ).click( function() {
        $.ajax({
            type : 'POST',
            url  : 'deleteCaseStatus',
            data : { 'caseStatusId' : caseStatusId },
            beforeSend: function() { 
                $("#continuemodal"+caseStatusId).html('<img src="../resources/img/input-spinner.gif"> Loading...');
                $("#continuemodal"+caseStatusId).prop('disabled', true);
            },
            success : function( msg ) 
            {
                $("#continuemodal"+caseStatusId).html('Yes');
                $("#continuemodal"+caseStatusId).prop('disabled', false);
                $('#small_modal').modal('hide');
                if(msg == "****")
                {
                	location.reload();
                }
                else
                	toastr.error(msg,"Error");
            }
        });
    });
}
function updateStatus( caseStatusId, status, checkAuthority ) {
	if(!checkAuthority)
	{
		toastr.error("Access Denied", "Error");
		return false;
	}
    if(status == 1){
        $( '#sm_modal_body' ).html( 'Do you really want to activate?' );
    }else{
        $( '#sm_modal_body' ).html( 'Do you really want to deactivate?' );
    }
    $( '#small_modal' ).modal();
    $( '#sm_modal_title' ).html( 'Are you Sure?' );
    $( '#sm_modal_footer' ).html( '<button type="button" class="btn dark btn-outline" data-dismiss="modal">Cancel</button><button type="button" id="continuemodal'+caseStatusId+'" class="btn green">Yes</button>' );
    $( '#continuemodal'+caseStatusId ).click( function() {
        $.ajax({
            type : 'POST',
            url  : 'updateStatus',
            data : { 'caseStatusId' : caseStatusId, 'status' : status },
            beforeSend: function() { 
                $("#continuemodal"+caseStatusId).html('<img src="../resources/img/input-spinner.gif"> Loading...');
                $("#continuemodal"+caseStatusId).prop('disabled', true);
            },
            success : function( msg ) 
            {
                $("#continuemodal"+caseStatusId).html('Yes');
	            $("#continuemodal"+caseStatusId).prop('disabled', false);
	            $('#small_modal').modal('hide');		            
                if(msg="****")
                {
                  	location.reload();
	            }
	            else
	            	 toastr.error(msg,'Error');
            }
        });
    });
}
//Delete Case Category
function deleteCaseCategory( caseCategoryId, checkAuthority ) {
	if(!checkAuthority)
	{
		toastr.error("Access Denied", "Error");
		return false;
	}
    $( '#small_modal' ).modal();
    $( '#sm_modal_title' ).html( 'Are you Sure?' );
    $( '#sm_modal_body' ).html( 'Do you really want to delete this record?' );
    $( '#sm_modal_footer' ).html( '<button type="button" class="btn dark btn-outline" data-dismiss="modal">Cancel</button><button type="button" id="continuemodal'+caseCategoryId+'" class="btn green">Yes</button>' );
    $( '#continuemodal'+caseCategoryId ).click( function() {
        $.ajax({
            type : 'POST',
            url  : 'deleteCaseCategory',
            data : { 'caseCategoryId' : caseCategoryId },
            beforeSend: function() { 
                $("#continuemodal"+caseCategoryId).html('<img src="../resources/img/input-spinner.gif"> Loading...');
                $("#continuemodal"+caseCategoryId).prop('disabled', true);
            },
            success : function( msg ) 
            {
                $("#continuemodal"+caseCategoryId).html('Yes');
                $("#continuemodal"+caseCategoryId).prop('disabled', false);
                $('#small_modal').modal('hide');
                if(msg == "****")
                {
                	location.reload();
                }
                else
                	toastr.error(msg,"Error");
            }
        });
    });
}
function updateCaseCategoryStatus( caseCategoryId, status, checkAuthority ) {
	if(!checkAuthority)
	{
		toastr.error("Access Denied", "Error");
		return false;
	}
    if(status == 1){
        $( '#sm_modal_body' ).html( 'Do you really want to activate?' );
    }else{
        $( '#sm_modal_body' ).html( 'Do you really want to deactivate?' );
    }
    $( '#small_modal' ).modal();
    $( '#sm_modal_title' ).html( 'Are you Sure?' );
    $( '#sm_modal_footer' ).html( '<button type="button" class="btn dark btn-outline" data-dismiss="modal">Cancel</button><button type="button" id="continuemodal'+caseCategoryId+'" class="btn green">Yes</button>' );
    $( '#continuemodal'+caseCategoryId ).click( function() {
        $.ajax({
            type : 'POST',
            url  : 'updateCaseCategoryStatus',
            data : { 'caseCategoryId' : caseCategoryId, 'status' : status },
            beforeSend: function() { 
                $("#continuemodal"+caseCategoryId).html('<img src="../resources/img/input-spinner.gif"> Loading...');
                $("#continuemodal"+caseCategoryId).prop('disabled', true);
            },
            success : function( msg ) 
            {
                $("#continuemodal"+caseCategoryId).html('Yes');
	            $("#continuemodal"+caseCategoryId).prop('disabled', false);
	            $('#small_modal').modal('hide');		            
                if(msg="****")
                {
                  	location.reload();
	            }
	            else
	            	 toastr.error(msg,'Error');
            }
        });
    });
}
//DELETE Mail Config
function deleteConfig( mailConfigId , checkAuthority) {
	if(!checkAuthority)
	{
		toastr.error("Access Denied", "Error");
		return false;
	}
    $( '#small_modal' ).modal();
    $( '#sm_modal_title' ).html( 'Are you Sure?' );
    $( '#sm_modal_body' ).html( 'Do you really want to delete this record?' );
    $( '#sm_modal_footer' ).html( '<button type="button" class="btn dark btn-outline" data-dismiss="modal">Cancel</button><button type="button" id="continuemodal' + mailConfigId + '" class="btn green">Yes</button>' );
    $( '#continuemodal' + mailConfigId ).click( function() {
        $.ajax({
            type : 'POST',
            url  : 'delete',
            data : { 'mailConfigId' : mailConfigId },
            beforeSend: function() { 
                $("#continuemodal" + mailConfigId).html('<img src="../resources/img/input-spinner.gif"> Loading...');
                $("#continuemodal" + mailConfigId).prop('disabled', true);
            },
            success : function( msg ) 
            {
                $("#continuemodal" + mailConfigId).html('Yes');
                $("#continuemodal" + mailConfigId).prop('disabled', false);
                $('#small_modal').modal('hide');
                if(msg="****")
	            {
	               location.reload();              
	            }
	            else
	                toastr.error(msg,'Error');
            }
        });
    });
}
function updateConfigStatus( mailConfigId, status, checkAuthority ) {
	if(!checkAuthority)
	{
		toastr.error("Access Denied", "Error");
		return false;
	}
    if(status == 1){
        $( '#sm_modal_body' ).html( 'Do you really want to activate?' );
    }else{
        $( '#sm_modal_body' ).html( 'Do you really want to deactivate?' );
    }
    $( '#small_modal' ).modal();
    $( '#sm_modal_title' ).html( 'Are you Sure?' );
    $( '#sm_modal_footer' ).html( '<button type="button" class="btn dark btn-outline" data-dismiss="modal">Cancel</button><button type="button" id="continuemodal' + mailConfigId + '" class="btn green">Yes</button>' );
    $( '#continuemodal' + mailConfigId ).click( function() {
        $.ajax({
            type : 'POST',
            url  : 'updateStatus',
            data : { 'mailConfigId' : mailConfigId, 'status' : status },
            beforeSend: function() { 
                $("#continuemodal" + mailConfigId).html('<img src="../resources/img/input-spinner.gif"> Loading...');
                $("#continuemodal" + mailConfigId).prop('disabled', true);
            },
            success : function( msg ) 
            {
            	$("#continuemodal" + mailConfigId).html('Yes');
                $("#continuemodal" + mailConfigId).prop('disabled', false);
                $('#small_modal').modal('hide');
                
	            if (msg == "****")
	            {
	            	location.reload();
	            }
	            else
	            	toastr.error(msg,"Error");
            }                      
        });
    });
}
//DELETE Location
function deleteLocation( locationId , checkAuthority) {
	if(!checkAuthority)
	{
		toastr.error("Access Denied", "Error");
		return false;
	}
    $( '#small_modal' ).modal();
    $( '#sm_modal_title' ).html( 'Are you Sure?' );
    $( '#sm_modal_body' ).html( 'Do you really want to delete this record?' );
    $( '#sm_modal_footer' ).html( '<button type="button" class="btn dark btn-outline" data-dismiss="modal">Cancel</button><button type="button" id="continuemodal' + locationId + '" class="btn green">Yes</button>' );
    $( '#continuemodal'+ locationId ).click( function() {
        $.ajax({
            type : 'POST',
            url  : 'deleteLocation',
            data : { 'locationId' : locationId },
            beforeSend: function() { 
                $("#continuemodal" + locationId).html('<img src="../resources/img/input-spinner.gif"> Loading...');
                $("#continuemodal" + locationId).prop('disabled', true);
            },
            success : function( msg ) 
            {
                $("#continuemodal" + locationId).html('Yes');
                $("#continuemodal" + locationId).prop('disabled', false);
                $('#small_modal').modal('hide');
                if(msg="****")
	            {
	               toastr.success("Location deleted successfully",'Success');
	               location.reload();              
	            }
	            else
	                toastr.error(msg,'Error');
            }
        });
    });
}
function updateLocationStatus(locationId, status, checkAuthority ) {
	if(!checkAuthority)
	{
		toastr.error("Access Denied", "Error");
		return false;
	}
    if(status == 1){
        $( '#sm_modal_body' ).html( 'Do you really want to activate?' );
    }else{
        $( '#sm_modal_body' ).html( 'Do you really want to deactivate?' );
    }
    $( '#small_modal' ).modal();
    $( '#sm_modal_title' ).html( 'Are you Sure?' );
    $( '#sm_modal_footer' ).html( '<button type="button" class="btn dark btn-outline" data-dismiss="modal">Cancel</button><button type="button" id="continuemodal' + locationId + '" class="btn green">Yes</button>' );
    $( '#continuemodal' + locationId ).click( function() {
        $.ajax({
            type : 'POST',
            url  : 'updateLocationStatus',
            data : { 'locationId' : locationId, 'status' : status },
            beforeSend: function() { 
                $("#continuemodal" + locationId).html('<img src="../resources/img/input-spinner.gif"> Loading...');
                $("#continuemodal" + locationId).prop('disabled', true);
            },
            success : function( msg ) 
            {
	            $("#continuemodal" + locationId).html('Yes');
	            $("#continuemodal" + locationId).prop('disabled', false);
	            $('#small_modal').modal('hide');	                
	            if (msg="****")
	            {
	            	location.reload();
	            }
	            else
	            	toastr.error(msg,Error)
            }         
        });
    });
}
//DELETE CATEGORY
function deleteInvestigationType(investigationId, checkAuthority) {
	if(!checkAuthority)
	{
		toastr.error("Access Denied", "Error");
		return false;
	}
    $( '#small_modal' ).modal();
    $( '#sm_modal_title' ).html( 'Are you Sure?' );
    $( '#sm_modal_body' ).html( 'Do you really want to delete this record?' );
    $( '#sm_modal_footer' ).html( '<button type="button" class="btn dark btn-outline" data-dismiss="modal">Cancel</button><button type="button" id="continuemodal'+investigationId+'" class="btn green">Yes</button>' );
    $( '#continuemodal'+investigationId ).click( function() {
        $.ajax({
            type : 'POST',
            url  : 'deleteInvestigation',
            data : { 'investigationId' : investigationId },
            beforeSend: function() { 
                $("#continuemodal" + investigationId).html('<img src="../resources/img/input-spinner.gif"> Loading...');
                $("#continuemodal" + investigationId).prop('disabled', true);
            },
            success : function( msg ) 
            {
                $("#continuemodal" + investigationId).html('Yes');
                $("#continuemodal" + investigationId).prop('disabled', false);
                $('#small_modal').modal('hide');
                if(msg == "****")
                {
                	toastr.error("Investigation Type deleted successfully", "Success");
                	location.reload();
                }
                else
                	toastr.error(msg,"Error");
            }
        });
        return false;
    });
}
function updateInvestigationTypeStatus(investigationId, status, checkAuthority ) {
	if(!checkAuthority)
	{
		toastr.error("Access Denied", "Error");
		return false;
	}
    if(status == 1)
        $( '#sm_modal_body' ).html( 'Do you really want to activate?' );
    else
        $( '#sm_modal_body' ).html( 'Do you really want to deactivate?' );
    $( '#small_modal' ).modal();
    $( '#sm_modal_title' ).html( 'Are you Sure?' );
    $( '#sm_modal_footer' ).html( '<button type="button" class="btn dark btn-outline" data-dismiss="modal">Cancel</button><button type="button" id="continuemodal'+investigationId+'" class="btn green">Yes</button>' );
    $( '#continuemodal'+investigationId ).click( function() {
        $.ajax({
            type : 'POST',
            url  : 'updateInvestigationStatus',
            data : { 'investigationId' : investigationId, 'status' : status },
            beforeSend: function() { 
                $("#continuemodal"+investigationId).html('<img src="../resources/img/input-spinner.gif"> Loading...');
                $("#continuemodal"+investigationId).prop('disabled', true);
            },
            success : function( msg ) 
            {
        		$("#continuemodal" + investigationId).html('Yes');
                $("#continuemodal" + investigationId).prop('disabled', false);
                $('#small_modal').modal('hide');	                
                if(msg=="****")
                {
                	location.reload();	                
            	}
            	else
             		toastr.error(msg,Error);
            }
        });
    });
}
//DELETE ADMIN USER
function deleteAdminUser( user_id, checkAuthority  ) {
	if(!checkAuthority)
	{
		toastr.error("Access Denied", "Error");
		return false;
	}
    $( '#small_modal' ).modal();
    $( '#sm_modal_title' ).html( 'Are you Sure?' );
    $( '#sm_modal_body' ).html( 'Do you really want to delete this record?' );
    $( '#sm_modal_footer' ).html( '<button type="button" class="btn dark btn-outline" data-dismiss="modal">Cancel</button><button type="button" id="continuemodal'+user_id+'" class="btn green">Yes</button>' );
    $( '#continuemodal'+user_id ).click( function() {
        $.ajax({
            type : 'POST',
            url  : 'deleteAdminUser',
            data : { 'user_id' : user_id },
            beforeSend: function() {
                $("#continuemodal" + user_id).html('<img src="../resources/img/input-spinner.gif"> Loading...');
                $("#continuemodal" + user_id).prop('disabled', true);
            },
            success : function( msg ) 
            {
                $("#continuemodal" + user_id).html('Yes');
                $("#continuemodal" + user_id).prop('disabled', false);
                $('#small_modal').modal('hide');
                if(msg == "****")
                {
                	location.reload();
                }
                else
                	toastr.error(msg,"Error");
            }
        });
        return false;
    });
}
//DELETE USER ROLE
function deleteRole( roleId, checkAuthority  ) {
	if(!checkAuthority)
	{
		toastr.error("Access Denied", "Error");
		return false;
	}
    $( '#small_modal' ).modal();
    $( '#sm_modal_title' ).html( 'Are you Sure?' );
    $( '#sm_modal_body' ).html( 'Do you really want to delete this record?' );
    $( '#sm_modal_footer' ).html( '<button type="button" class="btn dark btn-outline" data-dismiss="modal">Cancel</button><button type="button" id="continuemodal" class="btn green">Yes</button>' );
    $( '#continuemodal').click( function() {
        $.ajax({
            type : 'POST',
            url  : 'deleteRole',
            data : { 'roleId' : roleId },
            beforeSend: function() {
                $("#continuemodal" + roleId).html('<img src="../resources/img/input-spinner.gif"> Loading...');
                $("#continuemodal" + roleId).prop('disabled', true);
            },
            success : function( msg ) 
            {
                $("#continuemodal" + roleId).html('Yes');
                $("#continuemodal" + roleId).prop('disabled', false);
                $('#small_modal').modal('hide');
                if(msg == "****")
                {
                	location.reload();
                }
                else
                	toastr.error(msg,"Error");
            }
        });
        return false;
    });
}
//UPDATE ADMIN USER STATUS
function updateUserStatus( user_id, status, checkAuthority ) {
	if(!checkAuthority)
	{
		toastr.error("Access Denied", "Error");
		return false;
	}
    if(status == 1){
        $( '#sm_modal_body' ).html( 'Do you really want to activate?' );
    }else{
        $( '#sm_modal_body' ).html( 'Do you really want to deactivate?' );
    }
    $( '#small_modal' ).modal();
    $( '#sm_modal_title' ).html( 'Are you Sure?' );
    $( '#sm_modal_footer' ).html( '<button type="button" class="btn dark btn-outline" data-dismiss="modal">Cancel</button><button type="button" id="continuemodal'+user_id+'" class="btn green">Yes</button>' );
    $( '#continuemodal'+user_id ).click( function() {
        $.ajax({
            type : 'POST',
            url  : 'updateUserStatus',
            data : { 'user_id' : user_id, 'status' : status },
            beforeSend: function() { 
                $("#continuemodal"+user_id).html('<img src="../resources/img/input-spinner.gif"> Loading...');
                $("#continuemodal"+user_id).prop('disabled', true);
            },
            success : function( msg ) 
            {
                $("#continuemodal"+user_id).html('Yes');
                $("#continuemodal"+user_id).prop('disabled', false);
                $('#small_modal').modal('hide');
                if(msg == "****")
                {
                	location.reload();
                }
                else
                	toastr.error(msg,"Error");
            }
        });
    });
}
//DELETE APP USER
function deleteAppUser( appUserId, checkAuthority  ) {
	if(!checkAuthority)
	{
		toastr.error("Access Denied", "Error");
		return false;
	}
    var table2 = $('#app_user_list').DataTable();
    $( '#small_modal' ).modal();
    $( '#sm_modal_title' ).html( 'Are you Sure?' );
    $( '#sm_modal_body' ).html( 'Do you really want to delete this record?' );
    $( '#sm_modal_footer' ).html( '<button type="button" class="btn dark btn-outline" data-dismiss="modal">Cancel</button><button type="button" id="continuemodal'+appUserId+'" class="btn green">Yes</button>' );
    $( '#continuemodal'+appUserId ).click( function() {
        $.ajax({
            type : 'POST',
            url  : 'deleteAppUser',
            data : { 'appUserId' : appUserId },
            beforeSend: function() {
                $("#continuemodal"+appUserId).html('<img src="../resources/img/input-spinner.gif"> Loading...');
                $("#continuemodal"+appUserId).prop('disabled', true);
            },
            success : function( msg ) {
                $("#continuemodal"+appUserId).html('Yes');
                $("#continuemodal"+appUserId).prop('disabled', false);
                $('#small_modal').modal('hide');
                if(msg == "****")
                {
                	toastr.error("App user deleted successfully", "Success");
                	location.reload();
                }
                else
                	toastr.error(msg,"Error");
            }
        });
        return false;
    });
}
function updateAppUserStatus( appUserId, status, checkAuthority  ) {
	if(!checkAuthority)
	{
		toastr.error("Access Denied", "Error");
		return false;
	}
    var table2 = $('#app_user_list').DataTable();
    if(status == 1){
        $( '#sm_modal_body' ).html( 'Do you really want to deactivate?' );
    }else{
        $( '#sm_modal_body' ).html( 'Do you really want to activate?' );
    }
    $( '#small_modal' ).modal();
    $( '#sm_modal_title' ).html( 'Are you Sure?' );
    $( '#sm_modal_footer' ).html( '<button type="button" class="btn dark btn-outline" data-dismiss="modal">Cancel</button><button type="button" id="continuemodal'+appUserId+'" class="btn green">Yes</button>' );
    $( '#continuemodal'+appUserId ).click( function() {
        $.ajax({
            type : 'POST',
            url  : 'updateAppUserStatus',
            data : { 'appUserId' : appUserId, 'status' : status },
            beforeSend: function() { 
                $("#continuemodal"+appUserId).html('<img src="../resources/img/input-spinner.gif"> Loading...');
                $("#continuemodal"+appUserId).prop('disabled', true);
            },
            success : function( msg ) {
                $("#continuemodal"+appUserId).html('Yes');
                $("#continuemodal"+appUserId).prop('disabled', false);
                $('#small_modal').modal('hide');    
                if(msg == "****")
                {
	                toastr.success("AppUser status updated successfully", "Success");
	                location.reload();
                }
                else
	                toastr.error(msg, "Error");
            }
        });
    });
}
//MESSAGE STATUS
function updateMessageStatus( msgId, caseSubStatus, checkAuthority  ) {
	if(!checkAuthority)
	{
		toastr.error("Access Denied", "Error");
		return false;
	}    
    if(caseSubStatus.equals("PA"))
    {
        $( '#sm_modal_body' ).html( 'Do you really want to assign?' );
    }
    $( '#small_modal' ).modal();
    $( '#sm_modal_title' ).html( 'Are you Sure?' );
    $( '#sm_modal_footer' ).html( '<button type="button" class="btn dark btn-outline" data-dismiss="modal">Cancel</button><button type="button" id="continuemodal'+msgId+'" class="btn green">Yes</button>' );
    $( '#continuemodal'+msgId ).click( function() {
        $.ajax({
            type : 'POST',
            url  : 'updateMessageStatus',
            data : { 'msgId' : msgId, 'caseSubStatus' : caseSubStatus },
            beforeSend: function() { 
                $("#continuemodal"+msgId).html('<img src="../resources/img/input-spinner.gif"> Loading...');
                $("#continuemodal"+msgId).prop('disabled', true);
            },
            success : function( msg ) {
                $("#continuemodal"+msgId).html('Yes');
                $("#continuemodal"+msgId).prop('disabled', false);
                $('#small_modal').modal('hide');
                if(msg == "****")
                	location.reload();
            	else
            		toastr.error(msg, "Error");
            }
        });
    });
}
//DELETE MESSAGE
function deleteMessage( msgId, checkAuthority) {
	if(!checkAuthority)
	{
		toastr.error("Access Denied", "Error");
		return false;
	}
    $( '#small_modal' ).modal();
    $( '#sm_modal_title' ).html( 'Are you Sure?' );
    $( '#sm_modal_body' ).html( 'Do you really want to delete this record?' );
    $( '#sm_modal_footer' ).html( '<button type="button" class="btn dark btn-outline" data-dismiss="modal">Cancel</button><button type="button" id="continuemodal'+msgId+'" class="btn green">Yes</button>' );
    $( '#continuemodal'+msgId ).click( function() {
        $.ajax({
            type : 'POST',
            url  : 'deleteMessage',
            data : { 'msgId' : msgId },
            beforeSend: function() {
                $("#continuemodal"+msgId).html('<img src="../resources/img/input-spinner.gif"> Loading...');
                $("#continuemodal"+msgId).prop('disabled', true);
            },
            success : function( msg ) {
                $("#continuemodal"+msgId).html('Yes');
                $("#continuemodal"+msgId).prop('disabled', false);
                $('#small_modal').modal('hide');
                if(msg == "****")
                	location.reload();
                else
                	toastr.error(msg, "Error");
            }
        });
        return false;
    });
}

function ajaxDashMessage() {
    var data = {}
    $.ajax({
        type    : 'POST',
        url     : 'ajaxDashMessageList',
        data    : data,
        success : function( msg ) {
            $("#dash_message_lists").html(msg);
        }
    });
}
function ajaxDashCategory() {
    var data = {}
    $.ajax({
        type    : 'POST',
        url     : 'ajaxDashCategoryList',
        data    : data,
        success : function( msg ) {
            $("#dash_investigation_lists").html(msg);
        }
    });
}
//LOGIN VALIDATION
function change_passValidate() {
    var c_pass       = $( '#c_pass' ).val();
    var n_pass       = $( '#n_pass' ).val();
    var confirm_pass = $( '#confirm_pass' ).val();
    
    var data = { 'c_pass' : c_pass, 'n_pass': n_pass, 'confirm_pass' : confirm_pass }
    $.ajax({
        type    : 'POST',
        url     : 'change_passValidate',
        data    : data,
        beforeSend: function() {
            $("#changepasswordsubmit").html('<img src="../resources/img/input-spinner.gif"> Loading...');
            $("#changepasswordsubmit").prop('disabled', true);
        },
        success : function( msg ) {
            $("#changepasswordsubmit").html('Change Password');
            $("#changepasswordsubmit").prop('disabled', false);
            if( msg == 1 ) {
                $("#alert_msg").html('<div class="alert alert-success">Password updated successfully</div>');
                $("#alert_msg").show().delay(5000).fadeOut();
            } else {
                $("#alert_msg").html('<div class="alert alert-danger">'+msg+'</div>');
                $("#alert_msg").show();
                $("#alert_msg").show().delay(5000).fadeOut();
            }
        }
    });
}

$(document).ready(function() {

    $(document).on('change', '.select_box', function() {
        $("#company_branch option:selected").prop("selected", false);
    });
    $('.username').keypress(function (e) {
        var regex = new RegExp("^[a-zA-Z0-9_]+$");
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        }
        e.preventDefault();
        return false;
    });
    $('.allow_password').keypress(function (e) {
        var regex = new RegExp("^[a-zA-Z0-9_!@#$&()]+$");
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        }
        e.preventDefault();
        return false;
    });
    $('.allow_uuid').keypress(function (e) {
        var regex = new RegExp("^[a-zA-Z0-9-]+$");
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        }
        e.preventDefault();
        return false;
    });
    $('.number').keypress(function(event) {
        if (event.which == 8 || event.keyCode == 37 || event.keyCode == 39 || event.keyCode == 46) {
            return true;
        }else if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
            event.preventDefault();
        }
    });
});

$('.float-input').keypress(function(event) {
      if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
        event.preventDefault();
      } else {
           var entered_value = $(this).val();
          var regexPattern = /^\d{0,8}(\.\d{1,2})?$/; 
          if(regexPattern.test(entered_value)) {
              $(this).css('background-color', 'white');
              $('.err-msg').html('');
          } else {
              $(this).css('background-color', 'red');
              $('.err-msg').html('Enter a valid Decimal Number');
          }
      }
});
function displayUploadImg(input, PlaceholderID) {
	  if (input.files && input.files[0]) {
	    var upfile = input.files[0];
	    var imagefile = upfile.type;
	    var match= ["image/jpeg","image/png","image/jpg"];
	    if(!((imagefile==match[0]) || (imagefile==match[1]) || (imagefile==match[2]))){
	      alert('Please select a valid image file (JPEG/JPG/PNG).');
	      $("#"+input.id).val('');
	      return false;
	    }
	    var file_size = upfile.size/1024/1024;
	    if(file_size < 2){
	      var reader = new FileReader();
	      reader.onload = function (e) {
	        $('#'+PlaceholderID)
	            .attr('src', e.target.result)
	            .width('auto')
	            .height(160);
	        };
	      reader.readAsDataURL(upfile);
	    }
	    else
	    {
	      alert('File too large. File must be less than 2 MB.');
	      $("#"+input.id).val('');
	      return false;
	    }
	  }
	}
