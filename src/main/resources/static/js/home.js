$(document).ready(function () {
    $(document).on("click", "#choosefile", function () {
        $("#upImage").click();
    });

    $(document).on("click", "#myprofile", function () {
        $("#divcontent").load("/PhotoFoot/Photos?ismyphotos=true");
    });

    $(document).on("click", "#upload", function () {
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
});

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
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert("Error(s) encountered while uploading image." + thrownError.toString());
        }
    });
}