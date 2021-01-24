<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PhotoFoot</title>
    <link href="/css/home.css" rel="stylesheet"/>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/home.js"></script>
    <link rel="icon" type="image/png" href="/image/comment.png" />
</head>
<body>
	<div id="header">
		<div id="headertxtleft" class="headertxt">Hi ${myui.getFirstName()}</div>
		<button type="button" id="btnactivities" class="redbutton">ACTIVITIES</button>
		<button type="button" id="btnfav" class="redbutton">FAVORITES</button>
		<input id="upImage" type="file" style="display:none;"/>
		<button type="button" id="choosefile" class="redbutton">CHOOSE FILE</button>
		<button type="button" id="upload" class="redbutton">UPLOAD</button>
		<div id="headertxtright" class="headertxt"></div>
	</div>
	<div id="divul"></div>
	<div id="divcontent"></div>
</body>
</html>