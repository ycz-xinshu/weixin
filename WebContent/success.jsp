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
background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#EDFDEA), to(#78DE81));
background: -webkit-linear-gradient(top, #EDFDEA, #78DE81);
background: -moz-linear-gradient(top, #EDFDEA, #78DE81);
background: -o-linear-gradient(top, #EDFDEA, #78DE81);
background: -ms-linear-gradient(top, #EDFDEA, #78DE81);
FILTER: progid:DXImageTransform.Microsoft.Gradient(startColorStr=#EDFDEA, endColorStr=#78DE81);
}
.text {
position: absolute;
color: green;
top:10px;
left:130px;
}
.img {
margin: 30px;
}
</style>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript">
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
<title>综合信息服务平台绑定成功</title>
</head>
<body>
	<div>
		<div class="img">
			<img width="50px" height="50px" src="/images/right.png">
		</div>
		
		<div class="text">
			<h3>绑定成功</h3>
			<span>已成功绑定综合信息平台!</span><br><br>
			<a href="javascript:void(0)" id="closeLink">点击此处返回公众号</a>
		</div>

		<script type="text/javascript">
			var readyFunc = function onBridgeReady() {
				// 关闭当前webview窗口 - closeWindow
				document.querySelector('#closeLink').addEventListener(
						'click',
						function(e) {
							WeixinJSBridge.invoke('closeWindow', {}, function(
									res) {

							});
						});
			};

			if (typeof WeixinJSBridge === "undefined") {
				document.addEventListener('WeixinJSBridgeReady', readyFunc,
						false);
			} else {
				readyFunc();
			}
		</script>
	</div>
</body>
</html>