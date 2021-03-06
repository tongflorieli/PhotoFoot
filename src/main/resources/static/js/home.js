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
        $("#divcontent").load("/PhotoFoot/Activities", function(){
           $("#headertxtright").text("Recent Activities");
       });
    });

    $(document).on("click", "#btnfav", function () {
        $("#divcontent").load("/PhotoFoot/Favorites", function(){
            $("#headertxtright").text("My Favorites");
        });
    });

    $(document).on("click", ".divfavimg", function () {
        $("#favpopupcontent").load("/PhotoFoot/FavPopup?photoid="+$(this).data("photoid"),function(){
            $("#favpopup").show();
        });
    });

    $(document).on("click", ".unfav", function () {
        DeleteFav($(this).data("photoid"));
    });

    $(window).click(function(event){
		if (event.target == document.getElementById("favpopup")) {
            $("#favpopup").hide();
		}
    });

    $(document).on("click", "#profile", function () {
        $("#divcontent").load("/PhotoFoot/UserDetails?id="+$(this).data("uiid"), function(){
            $("#headertxtright").text("My Profile");
        });
    });

    $(document).on("click", "#btnviewphotos", function () {
        var fullname = $(this).data("userfullname");
        $("#divcontent").load("/PhotoFoot/Photos?username="+$(this).data("username"), function(){
            $(".comments").each(function(){
                $(this).load("/PhotoFoot/Comments?photoid="+$(this).data("photoid"));
            });
            $("#headertxtright").text(fullname+"'s Photos");
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

    $(document).on("click", ".imgstar", function () {
        ToggleFav($(this));
    });

    $(document).on("click", ".ulname", function () {
        var fullname = $(this).data("userfullname");
        $("#divcontent").load("/PhotoFoot/UserDetails?id="+$(this).data("uiid"),function(){
            $("#headertxtright").text(fullname+"'s Profile");
        });
    });
});

function DeleteFav(photoid){
	$.ajax({
        url: '/PhotoFoot/UpdateFav',
        data: {
            'fav': 0,
            "photoid": photoid
        },
        type: "post",
        cache: false,
        success: function(data) {
            if(data.Name == "success"){
                $("#divcontent").load("/PhotoFoot/Favorites");
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert("Error(s) encountered while deleting fav." + thrownError.toString());
            isvalid = false;
        }
    });
}

function ToggleFav(thiselem){
	var fav = thiselem.data("fav");
	var photoid = thiselem.data("photoid");
	//toggle
	if(fav == 1){
		fav = 0;
	}else{
		fav = 1;
	}
	$.ajax({
            url: '/PhotoFoot/UpdateFav',
            data: {
                'fav': fav,
                "photoid": photoid
            },
            type: "post",
            cache: false,
            success: function(data) {
                if(data.Name == "success"){
    				thiselem.data("fav", fav);
    				if(fav == 1){
    				    thiselem.attr("src","/Image/yellow star.png");
    				}
    				if(fav == 0){
                        thiselem.attr("src","/Image/grey star.png");
                    }
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert("Error(s) encountered while updating fav." + thrownError.toString());
                isvalid = false;
            }
        });
}

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