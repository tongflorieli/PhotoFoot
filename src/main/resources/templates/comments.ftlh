<#setting datetime_format="MMM dd yyyy hh:mm a">
<#list lcomments as comment>
    <div class="divcomment">
		<span class="commentedby">${comment.getUserInfo().getFirstName()?upper_case} ${comment.getUserInfo().getLastName()?upper_case}</span>
        <span class="commentcontent">${comment.getComment()}</span>
        <br>
        <span class="datetime">${comment.getCreated()}</span>
    </div>
</#list>