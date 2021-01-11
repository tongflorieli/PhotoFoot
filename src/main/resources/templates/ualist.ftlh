<div id="divcuruser">
	<div id="curusername">${curua.getFirstName()?upper_case} ${curua.getLastName()?upper_case}</div>
	<div id="divmybuttons">
		<button id="profile" class="bluebutton">PROFILE</button>
		<button id="logout" class="bluebutton">LOGOUT</button>
	</div>
	<div>
		<#switch curua.getActivity().getType()>
			<#case "User Registered">
				<img class="myicon" src="/image/register.png">
				<span class="myactivity">You registered as a user</span>
			<#break>
			<#case "Login">
				<img class="myicon" src="/image/login.png">
				<span class="myactivity">You logged in</span>
			<#break>
			<#case "Logout">
				<img class="myicon" src="/image/logout.png">
				<span class="myactivity">You logged out</span>
			<#break>
			<#case "Photo Upload">
				<img class="myicon" src="/PhotoFoot/GetImage?Id=${curua.getActivity().getRelated_Id()}">
				<span class="myactivity">You posted a photo</span>
			<#break>
			<#case "Comment">
                <img class="myicon" src="/image/comment.png">
                <span class="myactivity">You added a comment</span>
            <#break>
            <#default>
                <img class="myicon" src="/image/no_activity.png">
                <span class="myactivity">no recent activity</span>
		</#switch>
	</div>
</div>
<#list lua as ui>
    <div class="divUser">
		<#switch ui.getActivity().getType()>
			<#case "User Registered">
				<div class="ulleft">
					<img class="ulicon" src="/image/register.png" alt="register" title="register">
				</div>
				<div class="ulright">
					<div class="ulname">${ui.getFirstName()?upper_case} ${ui.getLastName()?upper_case}</div>
					<div class="uldetails">registered as a user</div>
				</div>
				<#break>
			<#case "Login">
				<div class="ulleft">
					<img class="ulicon" src="/image/login.png">
				</div>
				<div class="ulright">
					<div class="ulname">${ui.getFirstName()?upper_case} ${ui.getLastName()?upper_case}</div>
					<div class="uldetails">logged in</div>
				</div>
				<#break>
			<#case "Logout">
                <div class="ulleft">
                    <img class="ulicon" src="/image/logout.png">
                </div>
                <div class="ulright">
                    <div class="ulname">${ui.getFirstName()?upper_case} ${ui.getLastName()?upper_case}</div>
                    <div class="uldetails">logged out</div>
                </div>
                <#break>
			<#case "Photo Upload">
                <div class="ulleft">
                    <img class="ulicon" src="/PhotoFoot/GetImage?Id=${ui.getActivity().getRelated_Id()}">
                </div>
                <div class="ulright">
                    <div class="ulname">${ui.getFirstName()?upper_case} ${ui.getLastName()?upper_case}</div>
                    <div class="uldetails">posted a photo</div>
                </div>
                <#break>
			<#case "Comment">
                <div class="ulleft">
                    <img class="ulicon" src="/image/comment.png">
                </div>
                <div class="ulright">
                    <div class="ulname">${ui.getFirstName()?upper_case} ${ui.getLastName()?upper_case}</div>
                    <div class="uldetails">added a comment</div>
                </div>
                <#break>
            <#default>
                <div class="ulleft">
                    <img class="ulicon" src="/image/no_activity.png">
                </div>
                <div class="ulright">
                    <div class="ulname">${ui.getFirstName()?upper_case} ${ui.getLastName()?upper_case}</div>
                    <div class="uldetails">no recent activity</div>
                </div>
                <#break>
		</#switch>
    </div>
</#list>