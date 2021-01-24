<div style="display: flex;flex-wrap: wrap;">
	<#list lfav as fav>
		<div class="divfav">
			<div class="divfavimg" data-photoid="${fav.getPhotoId()}">
				<img class="favImgPhoto" src="/PhotoFoot/GetImage?Id=${fav.getPhotoId()}" alt="photo">
			</div>
			<div class="unfav" data-photoid="${fav.getPhotoId()}">
				<img class="unfavstar" src="/Image/grey star.png">
				UNFAVORITE
			</div>
		</div>
	</#list>
</div>
<div id="favpopup" class="modal">
	<div id="favpopupcontent">
	</div>
</div>