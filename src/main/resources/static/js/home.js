$(document).ready(function () {
    $(document).on("click", "#choosefile", function () {
        $("#upImage").click();
    });

    $(document).on("click", "#logout", function () {
        window.location.href = "/logout";
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
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert("Error(s) encountered while uploading image." + thrownError.toString());
        }
    });
}