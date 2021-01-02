$(document).ready(function () {
    $(document).on("click", "#btnregister", function () {
        RegisterUser();
    });
});

function RegisterUser(){
    var isvalid = true;
    hideErrors();
    var username = $("#register_username").val();
    var psw1 = $("#register_password_1").val();
    var psw2 = $("#register_password_2").val();
    var firstname = $("#register_firstname").val();
    var lastname = $("#register_lastname").val();
    var location = $("#register_location").val();
    var description = $("#register_description").val();
    var occupation = $("#register_occupation").val();
    if(username == ""){
        showError("register_username", "Please enter a username");
        isvalid = false;
    }
    if(psw1 != psw2){
        showError("register_password_2", "Passwords do not match!");
        isvalid = false;
    }
    if(psw1 == ""){
        showError("register_password_1", "Please enter a password");
        isvalid = false;
    }
    if(psw2 == ""){
        showError("register_password_2", "Please enter a password");
        isvalid = false;
    }
    //check username already exist
    $.ajax({
        url: '/Account/CheckUsernameExist',
        data: {
            'username': username
        },
        type: "post",
        cache: false,
        success: function(isexist) {
			if(isexist.Value == "already exist"){
				showError("register_username", "Username already exists!");
				isvalid = false;
			}
        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert("Error(s) encountered while checking username exist" + thrownError.toString());
            isvalid = false;
        }
    });
    //add user
    if(isvalid){
        $.ajax({
            url: '/Account/AddUser',
            data: {
                'username': username,
                "password": psw1,
                "firstname": firstname,
                "lastname": lastname,
                "location": location,
                "description": description,
                "occupation": occupation
            },
            type: "post",
            cache: false,
            success: function(data) {
    			if(data.Name == "success"){
    			    clearFields();
					$("#msg").html("<p class='green'>App saved!</p>");
                    setTimeout(function () {
                        $("#msg").html("");
                    }, 1500);
    			}
            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert("Error(s) encountered while checking username exist" + thrownError.toString());
                isvalid = false;
            }
        });
    }
}

function showError(element, message) {
    var err = '<div class="fielderror" id="err' + element + '">' + message + '</div>';
    $("#" + element).parent(".field").append(err);
    $("#" + element).addClass("fieldinputerror");
}

function hideErrors() {
    $(".fielderror").remove();
    $(".fieldinput").removeClass("fieldinputerror");
}

function clearFields(){
	$("#register_username").val("") ;
    $("#register_password_1").val("");
    $("#register_password_2").val("");
    $("#register_firstname").val("");
    $("#register_lastname").val("");
    $("#register_location").val("");
    $("#register_description").val("");
    $("#register_occupation").val("");
}