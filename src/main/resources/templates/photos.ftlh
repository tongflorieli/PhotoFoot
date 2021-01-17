<#setting datetime_format="MMM dd yyyy hh:mm a">
<#list lphotos as photo>
    <div class="divphoto">
        <div id="divimg">
            <img class="imgPhoto" src="/PhotoFoot/GetImage?Id=${photo.getId()}" alt="photo" title="photo">
        </div>
        <div class="divphotodetails">
	        <#if photo.getFav()>
		        <img class="imgstar" id="star_${photo.getId()}" data-fav="1" data-photoid="${photo.getId()}" src="/Image/yellow star.png">
	        <#else>
	            <img class="imgstar" id="star_${photo.getId()}" data-fav="0" data-photoid="${photo.getId()}" src="/Image/grey star.png">
	        </#if>
	        <span class="datetime photocreated">${photo.getCreated()}</span>
        </div>
        <div class="comments" id="comments_${photo.getId()}" data-photoid="${photo.getId()}"></div>
        <div class="divcomment">
            <input type="text" class="fieldinput newcomment" data-photoid="${photo.getId()}" placeholder="Comment on this photo!">
        </div>
    </div>
</#list>