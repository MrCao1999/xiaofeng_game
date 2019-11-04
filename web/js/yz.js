$(function() {
	$("#checkForm").validate({
		rules: {
			username: {
				required: true,
				rangelength: [3, 6]
			},
			pwd: {
				required: true,
				digits: true,
				minlength: 6
			},
			repwd: {
				required: true,
				equalTo: "#pwd",
				minlength: 6
			},
			email: {
				required: true,
				email: true
			}
		},
		messages: {
			username: {
				required: "用户名不能为空",
				rangelength: "用户名必须为3-6位"
			},
			pwd: {
				required: "密码不能为空",
				digits: "密码必须是整数",
				minlength: "密码不得少于6位"
			},
			repwd: {
				required: "确认密码不能为空",
				digits: "确认密码必须是整数",
				equalTo: "两次输入的密码不一样"
			},
			email: {
				required: "email不能为空",
				email: "请输入正确的email地址"
			}
		},
		errorElemet: "label",
		success: function(label) {
			label.text(" ").addClass("success");
		}
	});
});