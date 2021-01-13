<div id="divcuruser">
	<div id="curusername">${curua.getFirstName()?upper_case} ${curua.getLastName()?upper_case}</div>
	<div id="divmybuttons">
		<button id="profile" class="bluebutton">PROFILE</button>
		<button id="logout" class="bluebutton">LOGOUT</button>
	</div>
	<div>
		<#switch curua.getActivity().getType()>
			<#case "User Registered">
				<img id="icon_${curua.getId()}" data-atype="User Registered" class="myicon" src="/image/register.png">
				<span class="myactivity">You </span><span id="adetail_${curua.getId()}" class="myactivity">registered as a user</span>
			<#break>
			<#case "Login">
				<img id="icon_${curua.getId()}" data-atype="Login" class="myicon" src="/image/login.png">
				<span class="myactivity">You </span><span id="adetail_${curua.getId()}" class="myactivity">logged in</span>
			<#break>
			<#case "Logout">
				<img id="icon_${curua.getId()}" data-atype="Logout" class="myicon" src="/image/logout.png">
				<span class="myactivity">You </span><span id="adetail_${curua.getId()}" class="myactivity">logged out</span>
			<#break>
			<#case "Photo Upload">
				<img id="icon_${curua.getId()}" data-atype="Photo Upload" class="myicon" src="/PhotoFoot/GetImage?Id=${curua.getActivity().getRelated_Id()}">
				<span class="myactivity">You </span><span id="adetail_${curua.getId()}" class="myactivity">posted a photo</span>
			<#break>
			<#case "Comment">
                <img id="icon_${curua.getId()}" data-atype="Comment" class="myicon" src="/image/comment.png">
                <span class="myactivity">You </span><span id="adetail_${curua.getId()}" class="myactivity">added a comment</span>
            <#break>
            <#default>
                <img id="icon_${curua.getId()}" data-atype="Default" class="myicon" src="/image/no_activity.png">
                <span id="adetail_${curua.getId()}" class="myactivity">no recent activity</span>
		</#switch>
	</div>
</div>
<#list lua as ui>
    <div class="divUser">
		<#switch ui.getActivity().getType()>
			<#case "User Registered">
				<div class="ulleft">
					<img id="icon_${ui.getId()}" data-atype="User Registered" class="ulicon" src="/image/register.png" alt="register" title="register">
				</div>
				<div class="ulright">
					<div class="ulname">${ui.getFirstName()?upper_case} ${ui.getLastName()?upper_case}</div>
					<div id="adetail_${ui.getId()}" class="uldetails">registered as a user</div>
				</div>
				<#break>
			<#case "Login">
				<div class="ulleft">
					<img id="icon_${ui.getId()}" data-atype="Login" class="ulicon" src="/image/login.png">
				</div>
				<div class="ulright">
					<div class="ulname">${ui.getFirstName()?upper_case} ${ui.getLastName()?upper_case}</div>
					<div id="adetail_${ui.getId()}" class="uldetails">logged in</div>
				</div>
				<#break>
			<#case "Logout">
                <div class="ulleft">
                    <img id="icon_${ui.getId()}" data-atype="Logout" class="ulicon" src="/image/logout.png">
                </div>
                <div class="ulright">
                    <div class="ulname">${ui.getFirstName()?upper_case} ${ui.getLastName()?upper_case}</div>
                    <div id="adetail_${ui.getId()}" class="uldetails">logged out</div>
                </div>
                <#break>
			<#case "Photo Upload">
                <div class="ulleft">
                    <img id="icon_${ui.getId()}" data-atype="Photo Upload" class="ulicon" src="/PhotoFoot/GetImage?Id=${ui.getActivity().getRelated_Id()}">
                </div>
                <div class="ulright">
                    <div class="ulname">${ui.getFirstName()?upper_case} ${ui.getLastName()?upper_case}</div>
                    <div id="adetail_${ui.getId()}" class="uldetails">posted a photo</div>
                </div>
                <#break>
			<#case "Comment">
                <div class="ulleft">
                    <img id="icon_${ui.getId()}" data-atype="Comment" class="ulicon" src="/image/comment.png">
                </div>
                <div class="ulright">
                    <div class="ulname">${ui.getFirstName()?upper_case} ${ui.getLastName()?upper_case}</div>
                    <div id="adetail_${ui.getId()}" class="uldetails">added a comment</div>
                </div>
                <#break>
            <#default>
                <div class="ulleft">
                    <img id="icon_${ui.getId()}" data-atype="Default" class="ulicon" src="/image/no_activity.png">
                </div>
                <div class="ulright">
                    <div class="ulname">${ui.getFirstName()?upper_case} ${ui.getLastName()?upper_case}</div>
                    <div id="adetail_${ui.getId()}" class="uldetails">no recent activity</div>
                </div>
                <#break>
		</#switch>
    </div>
</#list>