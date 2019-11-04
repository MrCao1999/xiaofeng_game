$(function() {
	$("#del").click(function() {
		if(confirm("是否删除")) {

		} else {
			$("#del").attr('href', 'shopcart.jsp');
		}
	});
});
$(function() {
	$("#allbox").on('click', function() {
		$(".checkbox").prop("checked", this.checked);
});

$(".checkbox").on('click',function() {
	var $subs = $(".checkbox");
	$("#allbox").prop("checked",$subs.length == $subs.filter(":checked").leng ? true :false);
	});
});
$(function() {
	$("#all").on('click', function() {
		$(".checkbox").prop("checked", this.checked);
	});
	$(".checkbox").on('click',function() {
		var $subs = $(".checkbox");
		$("#all").prop("checked",$subs.length == $subs.filter(":checked").leng ? true :false);
	});
});