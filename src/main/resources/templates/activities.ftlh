<#setting datetime_format="MMM dd yyyy hh:mm a">
<#list lactivities as activity>
    <div class="divactivity">
		<#switch activity.getType()>
			<#case "User Registered">
				<div class="activityleft">
					<img class="activityicon" src="/image/register.png" alt="register" title="register">
				</div>
				<div class="activityright">
					<div class="activitydetails">${activity.getModified()}</div>
					<div class="activitydetails">${activity.getUi().getFirstName()} ${activity.getUi().getLastName()} registered as a user</div>
				</div>
				<#break>
			<#case "Login">
				<div class="activityleft">
					<img class="activityicon" src="/image/login.png" alt="register" title="register">
				</div>
				<div class="activityright">
					<div class="activitydetails">${activity.getModified()}</div>
					<div class="activitydetails">${activity.getUi().getFirstName()} ${activity.getUi().getLastName()} logged in</div>
				</div>
				<#break>
			<#case "Logout">
                <div class="activityleft">
                    <img class="activityicon" src="/image/logout.png" alt="register" title="register">
                </div>
                <div class="activityright">
                    <div class="activitydetails">${activity.getModified()}</div>
                    <div class="activitydetails">${activity.getUi().getFirstName()} ${activity.getUi().getLastName()} logged out</div>
                </div>
                <#break>
			<#case "Photo Upload">
                <div class="activityleft">
                    <img class="activityicon" src="/PhotoFoot/GetImage?Id=${activity.getPhoto().getId()}" alt="register" title="register">
                </div>
                <div class="activityright">
                    <div class="activitydetails">${activity.getModified()}</div>
                    <div class="activitydetails">${activity.getPhoto().getUserInfo().getFirstName()} ${activity.getPhoto().getUserInfo().getLastName()} posted a photo</div>
                </div>
                <#break>
			<#case "Comment">
                <div class="activityleft">
                    <img class="activityicon" src="/PhotoFoot/GetImage?Id=${activity.getPhoto().getId()}" alt="register" title="register">
                </div>
                <div class="activityright">
                    <div class="activitydetails">${activity.getModified()}</div>
                    <div class="activitydetails">${activity.getComment().getUserInfo().getFirstName()} ${activity.getComment().getUserInfo().getLastName()} added a comment on ${activity.getPhoto().getUserInfo().getFirstName()} ${activity.getPhoto().getUserInfo().getLastName()}'s photo</div>
                </div>
                <#break>
		</#switch>
    </div>
</#list>