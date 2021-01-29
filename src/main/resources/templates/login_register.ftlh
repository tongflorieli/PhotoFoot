<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PhotoFoot</title>
    <link href="/css/login_register.css" rel="stylesheet"/>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/login_register.js"></script>
    <link rel="icon" type="image/png" href="/image/comment.png" />
</head>
<body>
	<div id="header">
		<div id="headertxt">PhotoFoot</div>
	</div>
	<div id="content">
	    <#if error??>
	        <div class="error">${error}</div>
	    </#if>
	    <form action="Login" method="post" authentication-failure-url="/Login?error" >
		    <div class="field">
		        <label for="username">Username</label><input type="text" id="username" name="username"><br/>
	        </div>
	        <div class="field">
		        <label for="password">Password</label><input type="text" id="password" name="password"><br/>
	        </div>
	        <div class="btnfield">
	            <input class="redbutton" name="submit" type="submit" value="LOGIN">
	        </div>
	    </form>

		<div class="field">
		    <label for="register_username">Username</label><input type="text" class="fieldinput" id="register_username"><br/>
	    </div>
	    <div class="field">
		    <label for="register_password_1">Password</label><input type="text" class="fieldinput" id="register_password_1"><br/>
	    </div>
	    <div class="field">
		    <label for="register_password_2">Password</label><input type="text" class="fieldinput" id="register_password_2"><br/>
	    </div>
	    <div class="field">
		    <label for="register_firstname">First Name</label><input type="text" class="fieldinput" id="register_firstname"><br/>
	    </div>
	    <div class="field">
		    <label for="register_lastname">Last Name</label><input type="text" class="fieldinput" id="register_lastname"><br/>
	    </div>
	    <div class="field">
		    <label for="register_location">Location</label><input type="text" class="fieldinput" id="register_location"><br/>
	    </div>
	    <div class="field">
		    <label for="register_description">Description</label><input type="text" class="fieldinput" id="register_description"><br/>
	    </div>
	    <div class="field">
		    <label for="register_occupation">Occupation</label><input type="text" class="fieldinput" id="register_occupation"><br/>
	    </div>
	    <div class="field">
		    <button type="button" id="btnregister" class="redbutton">REGISTER ME</button>
	    </div>
	    <div id="msg"></div>
    </div>
</body>
</html>