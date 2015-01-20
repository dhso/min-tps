<#assign head_title = "首页">
<!DOCTYPE html>
<html lang="en">
<head><#include "common/head.ftl" encoding="UTF-8">
</head>
<body>
	<div class="m-head">啦啦啦</div>
	<div class="blank-split"></div>
	<div class="m-content">
		<@shiro.authenticated>
		  <li><a href="/user/center"><@shiro.principal name="full_name"/></a></li>
		      |
		  <li><a href="/signout">退出</a></li>
		</@shiro.authenticated>
		<@shiro.notAuthenticated>
		  <li><a href="login">登陆</a></li>
		</@shiro.notAuthenticated>
		<@shiro.isLoginFailure name="shiroLoginFailure">
		  <@shiro.loginException name="shiroLoginFailure"/>
		</@shiro.isLoginFailure>
		<@shiro.hasRole name="ROLE_ADMIN">
		我是admin
		</@shiro.hasRole>
		<@shiro.hasPermission name="P_ORDER_CONTROL">
		  <li><a href="/order/branch" class="<#if activebar=='branch'>nav-active</#if>">全部订单</a></li>
		</@shiro.hasPermission>
	</div>
	<div class="m-foot"><#include "common/foot.ftl" encoding="UTF-8">
	</div>
</body>
</html>