$(function() {
	var index = 0;
	var stop = false;
	var $li = $(".network_lbt").find(".network_lbt_img").children("li");
	var $page = $(".network_lbt").find(".network_lbt_number").children("li");
	$page.eq(index).addClass("network_lbt_number_over").stop(true).siblings().removeClass("network_lbt_number_over");
	$page.mousemove(function() {
		stop = true;
		index = $page.index($(this));
		$li.eq(index).stop(true).fadeIn().siblings().fadeOut();
		$(this).addClass("network_lbt_number_over").stop(true).siblings().removeClass("network_lbt_number_over");
	}).mouseout(function() {
		stop = false;
	});
	setInterval(function() {
		if(stop) return;
		index++;
		if(index >= $li.length) {
			index = 0;
		}
		$li.eq(index).stop(true).fadeIn().siblings().fadeOut();
		$page.eq(index).addClass("network_lbt_number_over").stop(true).siblings().removeClass("network_lbt_number_over");
	}, 3000);
});