<#list lphotos as photo>
    <div class="divphoto">
        <div id="divimg">
            <img class="imgPhoto" src="/PhotoFoot/GetImage?Id=${photo.getId()}" alt="photo" title="photo">
        </div>
        <div class="comments" id="comments_${photo.getId()}" data-photoid="${photo.getId()}"></div>
        <div class="divcomment">
            <input type="text" class="fieldinput newcomment" data-photoid="${photo.getId()}" placeholder="Comment on this photo!">
        </div>
    </div>
</#list>