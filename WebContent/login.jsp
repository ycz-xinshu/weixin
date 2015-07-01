<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-theme.min.css">
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
		if ($("input[name=code]").val()=="null") {
			window.location.href = "index.jsp";
		}
		$("input[name=msgcode]").removeClass("display");
		var $sendCode = $("#sendCode");
		$sendCode.on("click", function() {
			var username = $("input[name=username]").val();
			if (!username) {
				alert("请填写用户编号");
				return false;
			}
			$sendCode.attr("disabled", "true");
			var time = 60;
			var value = $sendCode.html();
			var t = setInterval(function() {
				if (time == 0) {
					clearInterval(t);
					$sendCode.removeAttr("disabled");
					$sendCode.html(value);
					return;
				}
				if ($sendCode) {
					$sendCode.html((time--)+"秒后重新获取");
				}
			}, 1000);
			$.post("/code", {
				username : username,
			}, function(data) {
				if (data.error) {
					clearInterval(t);
					$sendCode.removeAttr("disabled");
					$sendCode.html(value);
					alert(data.error);
				}
			});
		});

		var $submit = $("button[type=submit]");
		$submit.on("click", function() {
			var username = $("input[name=username]").val();
			var password = $("input[name=password]").val();
			var msgcode = $("input[name=msgcode]").val();
			if (!username) {
				alert("请输入用户编号");
				$("input[name=username]").focus();
				return false;
			}
			if (!password) {
				alert("请输入密码");
				$("input[name=password]").focus();
				return false;
			}
			if (!msgcode) {
				alert("请输入邮箱验证码");
				$("input[name=msgcode]").focus();
				return false;
			}
			re = /[0-9]{6}/;
			if(!re.test(msgcode)) {
				alert("请正确填写邮箱验证码");
				$("input[name=msgcode]").focus();
				return false;
			}
			return true;
		});
	});
</script>
<title>综合信息服务平台绑定</title>
</head>
<body>
	<div class="container">
		<form action="/login" role="form">
			<h4 align="center" style="color:#428bca; border:true">综合信息服务平台绑定</h4>
			<input type="text" name="username" class="form-control" placeholder="请输入用户编号" required
				autofocus>
			<div style="height: 10px; clear: both; display: block"></div>
			<input
				type="password" name="password"
				class="form-control" placeholder="请输入密码" required>
			<div style="height: 10px; clear: both; display: block"></div>
			<input
				type="text" name="msgcode" class="form-control" style="width:49%; display:inline"
				 placeholder="请输入邮箱验证码" required>
			<button id="sendCode" 
				class="btn btn-primary" style="width:49%; display:inline">获取邮箱验证码</button>
			<input type="hidden"
				name="code" value=<%=request.getParameter("code")%>>
			<div style="height: 10px; clear: both; display: block"></div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">绑定</button>
		</form>
	</div>
</body>
</html>