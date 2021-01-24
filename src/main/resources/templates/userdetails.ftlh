<div class="udline">
	<img class="udicon" src="/image/ud_person.png" alt="register" title="register">
	${ui.getFirstName()} ${ui.getLastName()}
</div>
<div class="udline">
	<img class="udicon" src="/image/home.png" alt="register" title="register">
	Lives in ${ui.getLocation()}
</div>
<div class="udline">
	<img class="udicon" src="/image/suitcase.png" alt="register" title="register">
	Occupation ${ui.getOccupation()}
</div>
<div class="udline">
	<img class="udicon" src="/image/info.png" alt="register" title="register">
	About: ${ui.getDescription()}
</div>
<div style="text-align:left;">
	<button type="button" id="btnviewphotos" class="redbutton" data-username="${ui.getUsername()}" data-userfullname="${ui.getFirstName()} ${ui.getLastName()}">VIEW PHOTOS</button>
</div>