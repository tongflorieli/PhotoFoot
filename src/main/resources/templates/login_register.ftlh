<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>index</title>
    <link href="/css/wer.css" rel="stylesheet"/>
</head>
<body>
    <#if error??>
        <div class="error">${error}</div>
    </#if>
    <form action="Login" method="post" authentication-failure-url="/Login?error" >
        <label for="username">Username</label><input type="text" id="username" name="username"><br/>
        <label for="password">Password</label><input type="text" id="password" name="password"><br/>
       <input name="submit" type="submit" value="Login">
    </form>
</body>
</html>