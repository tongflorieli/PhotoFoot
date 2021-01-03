<#setting datetime_format="MMM dd yyyy hh:mm a">
<#list lcomments as comment>
    <div>
		<label class="commentedby">${comment.getUserInfo().getFirstName()?upper_case} ${comment.getUserInfo().getLastName()?upper_case}</label>
        <p>${comment.getComment()}</p>
        <label>${comment.getCreated()}</label>
    </div>
</#list>