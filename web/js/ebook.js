$(function() {
	$(".ebook").find("img:eq(0)").show().siblings().hide();
	$(".ebook-1 ul li:odd").css("background-color", "#f5f2ed");
	$(".ebook-1 ul li:even").css("background-color", "#faf7f2");
});
$(function() {
	var index = 0;
	var stop = false;
	var $li = $(".ebook_lbt").find(".ebook_lbt_img").children("li");
	var $page = $(".ebook_lbt").find(".ebook_lbt_number").children("li");
	$page.eq(index).addClass("ebook_lbt_number_over").stop(true).siblings().removeClass("ebook_lbt_number_over");
	$page.mousemove(function() {
		stop = true;
		index = $page.index($(this));
		$li.eq(index).stop(true).fadeIn().siblings().fadeOut();
		$(this).addClass("ebook_lbt_number_over").stop(true).siblings().removeClass("ebook_lbt_number_over");
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
		$page.eq(index).addClass("ebook_lbt_number_over").stop(true).siblings().removeClass("ebook_lbt_number_over");
	}, 3000);
});