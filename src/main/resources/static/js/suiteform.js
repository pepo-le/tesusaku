var tesusaku = tesusaku || {};

(function (global) {
	tesusaku.assignSelect = document.getElementById('assignUsers');
	tesusaku.resetBtn = document.getElementById('select-reset-btn');
	
	tesusaku.resetBtn.addEventListener('click', function () {
		tesusaku.assignSelect.selectedIndex = -1;
	});
}(this));