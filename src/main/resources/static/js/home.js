$(document).ready(function () {
	$("#divul").load("/PhotoFoot/UAList");
	var updateULinterval = setInterval(function() {
        UpdateUserList();
    }, 3000);

    $(document).on("click", "#choosefile", function () {
        $("#upImage").click();
    });

    $(document).on("click", "#logout", function () {
        window.location.href = "/logout";
    });

    $(document).on("click", "#btnactivities, #refreshactvity", function () {
        $("#divcontent").load("/PhotoFoot/Activities");
    });

    $(document).on("click", "#myprofile", function () {
        $("#divcontent").load("/PhotoFoot/Photos?ismyphotos=true", function(){
            $(".comments").each(function(){
                $(this).load("/PhotoFoot/Comments?photoid="+$(this).data("photoid"));
            });
        });
    });

    $(document).on("click", "#upload", function () {
        $(this).prop("disable", true);
	    if(document.getElementById("upImage").files[0] == undefined){
	        alert("Please select an image fist.");
	        return;
	    }
	    if (!document.getElementById("upImage").files[0].name.match(/.(jpg|jpeg|png)$/i)) {
            alert("Please upload file having extensions .jpeg/.jpg/.png only.");
            return;
        }
        UploadImg();
    });

    $(document).on("keypress", ".newcomment", function (event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
		if(keycode === 13){
			AddComment($(this));
		}
    });
});

function UpdateUserList(){
	$.get("/PhotoFoot/UpdateUserList", function(data) {
		for (var i=0; i<data.length; i++) {
			$.each(data, function (index, ulavm) {
	            if($("#icon_"+ulavm.Name).data("atype") != ulavm.Value){
		            $("#icon_"+ulavm.Name).data("atype", ulavm.Value);
		            switch(ulavm.Value){
		                case 'User Registered':
		                    $("#divul").load("/PhotoFoot/UAList");
		                    break;
		                case 'Login':
		                    $("#icon_"+ulavm.Name).attr('src','/image/login.png');
		                    $('#adetail_'+ulavm.Name).html('logged in');
		                    break;
		                case 'Logout':
		                    $("#icon_"+ulavm.Name).attr('src','/image/logout.png');
		                    $('#adetail_'+ulavm.Name).html('logged out');
		                    break;
		                case 'Photo Upload':
		                    $("#icon_"+ulavm.Name).attr('src','/PhotoFoot/GetImage?Id='+ulavm.Related_Id);
		                    $('#adetail_'+ulavm.Name).html('posted a photo');
		                    break;
		                case 'Comment':
		                    $("#icon_"+ulavm.Name).attr('src','/image/comment.png');
		                    $('#adetail_'+ulavm.Name).html('added a comment');
		                    break;
	                    default:
	                        $("#icon_"+ulavm.Name).attr('src','/image/no_activity.png');
	                        $('#adetail_'+ulavm.Name).html('no recent activity');
	                        break;
		            }
	            }
            });
        }
	}).fail(function(){
		alert("error when updating user list.");
	});
}

function AddComment(thisele){
	var comment = thisele.val();
	var photoid = thisele.data("photoid");
	if(comment == ""){
		alert("Please enter a comment!");
		return;
	}
	$.ajax({
        url: '/PhotoFoot/AddComment',
        data: {
            'comment': comment,
            "photoid": photoid
        },
        type: "post",
        cache: false,
        success: function(data) {
            if(data.Name == "success"){
				thisele.val("");
				$("#comments_"+thisele.data("photoid")).load("/PhotoFoot/Comments?photoid="+thisele.data("photoid"));
				UpdateUserList();
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert("Error(s) encountered while adding comment." + thrownError.toString());
            isvalid = false;
        }
    });
}

function UploadImg(){
	var formData = new FormData();
	formData.append("imgfile", document.getElementById("upImage").files[0]);
	$.ajax({
        url: '/PhotoFoot/UploadImage',
        data: formData,
        enctype: 'multipart/form-data',
        type: "post",
        processData: false,
        contentType: false,
        cache: false,
        success: function(data) {
            $("#upload").prop("disable", false);
            UpdateUserList();
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert("Error(s) encountered while uploading image." + thrownError.toString());
        }
    });
}