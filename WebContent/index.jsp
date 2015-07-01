<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>ChinaDaaS Weixin</title>
</head>
<body>
	<%@page import="com.chinadaas.weixin.util.PropertiesUtil"%>
	<%
		String redirect_url = PropertiesUtil.getProperty("system.config.weixin.url.login");
	%>
	<script type="text/javascript">
		window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0c5c0ded2746f73a&redirect_uri=<%=redirect_url%>&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
	</script>
</body>
</html>