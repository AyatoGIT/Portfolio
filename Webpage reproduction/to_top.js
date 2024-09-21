$(function(){
	let pagetop = $('#to_top');
	pagetop.hide();
	$(window).scroll(function() {
		if ($(this).scrollTop() > 700) {
			pagetop.fadeIn();
		} else {
			pagetop.fadeOut();
		}
	});
	pagetop.click(function(){
		scrollTop: 0
	}, 500);
	return false;
});