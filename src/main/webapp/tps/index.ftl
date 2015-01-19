<#assign head_title = "首页">
<!DOCTYPE html>
<html lang="en">
<head><#include "common/head.ftl" encoding="UTF-8">
</head>
<body>
	<div class="m-head">啦啦啦</div>
	<div class="blank-split"></div>
	<div class="m-content">
		<#if hasAnyRoles("admin","user")>
			<input type="button" value="查看" />
		</#if>
		<#if hasRole("admin")>
			<input type="button" value="编辑" />
		</#if>
	</div>
	<div class="m-foot"><#include "common/foot.ftl" encoding="UTF-8">
	</div>
</body>
</html>