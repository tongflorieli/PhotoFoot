<#list lphotos as photo>
    <div>
        <div id="divimg">
            <img id="imgPhoto" src="/PhotoFoot/GetImage?Id=${photo.getId()}" alt="photo" title="photo">
        </div>
    </div>
</#list>