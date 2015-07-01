<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css">
<style type="text/css">
.gradient {
background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#F7DADD), to(#F99CA0));
background: -webkit-linear-gradient(top, #F7DADD, #F99CA0);
background: -moz-linear-gradient(top, #F7DADD, #F99CA0);
background: -o-linear-gradient(top, #F7DADD, #F99CA0);
background: -ms-linear-gradient(top, #F7DADD, #F99CA0);
FILTER: progid:DXImageTransform.Microsoft.Gradient(startColorStr=#F7DADD, endColorStr=#F99CA0);
}
.text {
position: absolute;
top:10px;
left:130px;
color: red;
}
.img {
margin: 30px;
}
</style>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	function back() {
		history.go(-1);
	}
	function onBridgeReady() {
		WeixinJSBridge.call('hideOptionMenu');
	}

	if (typeof WeixinJSBridge == "undefined") {
		if (document.addEventListener) {
			document.addEventListener('WeixinJSBridgeReady', onBridgeReady,
					false);
		} else if (document.attachEvent) {
			document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
			document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		}
	} else {
		onBridgeReady();
	}
</script>
<title>综合信息服务平台绑定失败</title>
</head>
<body>
	<div>
		<div id="hidden" style="display:none;" value="${code}"></div>
		<div class="img">
			<img width="50px" height="50px" src="/images/error.png">
		</div>
		
		<div class="text">
			<h3>绑定失败</h3>
			<span>失败原因: ${message}</span><br><br>
			<a href="javascript:void(0)" onclick="back()">点击此处返回绑定页面</a>
		</div>
	</div>
</body>
</html>