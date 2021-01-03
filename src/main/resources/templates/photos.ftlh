<#list lphotos as photo>
    <div>
        <div id="divimg">
            <img id="imgPhoto" src="/PhotoFoot/GetImage?Id=${photo.getId()}" alt="photo" title="photo">
        </div>
        <div class="comments" id="comments_${photo.getId()}" data-photoid="${photo.getId()}"></div>
        <div class="field">
            <input type="text" class="fieldinput newcomment" data-photoid="${photo.getId()}" placeholder="Comment">
        </div>
    </div>
</#list>