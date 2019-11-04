$(function() {
	$(".top-2-2-1  ul li:eq(0)").addClass("ysli2");
	$(".top-2-2-1  ul li").mouseover(function() {
		var _index = $(this).index();
		$(".top-2-2-1  ul li:eq(0)").removeClass("ysli2");
		$(this).addClass("ysli").siblings().removeClass("ysli");
		$(".top-2-2-2 div").eq(_index).show().siblings().hide();
	});
	$(".top-3 ul li:eq(0)").find("span:eq(1)").hide();
	$(".top-3 ul li:eq(0)").css("height", "150px").siblings().css("height",
			"50px");
	$(".top-3 ul li:eq(0)").find("div").addClass("book");
	$(".top-3 ul li:eq(0)").siblings().find("div").css("display", "none");
	$(".top-3 ul li").mouseover(function() {
		$(this).css("height", "150px").siblings().css("height", "50px");
		$(this).find("div").show();
		$(this).find("span:eq(1)").hide();
		$(this).siblings().find("span:eq(1)").show();
		$(this).siblings().find("div").hide();
	});
	$(".top-3 ul li div a").mouseover(function() {
		$(this).addClass("afont");
		$(this).css("color", "orange");

	});
	$(".top-3 ul li div a").mouseout(function() {
		$(this).removeClass("afont");
		$(this).css("color", "black");

	});
	$(".lbt ul li:eq(0)").show().siblings().hide();
});

//轮播图
$(function() {
	var index = 0;
	var stop = false;
	var $li = $(".middle").find(".lbt_img").children("li");
	var $page = $(".middle").find(".lbt_number").children("li");
	$page.eq(index).addClass("lbt_number_over").stop(true).siblings()
			.removeClass("lbt_number_over");
	$page.mousemove(
			function() {
				stop = true;
				index = $page.index($(this));
				$li.eq(index).stop(true).fadeIn().siblings().fadeOut();
				$(this).addClass("lbt_number_over").stop(true).siblings()
						.removeClass("lbt_number_over");
			}).mouseout(function() {
		stop = false;
	});
	setInterval(function() {
		if (stop)
			return;
		index++;
		if (index >= $li.length) {
			index = 0;
		}
		$li.eq(index).stop(true).fadeIn().siblings().fadeOut();
		$page.eq(index).addClass("lbt_number_over").stop(true).siblings()
				.removeClass("lbt_number_over");
	}, 3000);
});