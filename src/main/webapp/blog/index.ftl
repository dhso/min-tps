<#assign head_title = "首页">
<!DOCTYPE html>
<html lang="en">
<head><#include "common/head.ftl" encoding="UTF-8">
</head>
<body class="metro">
	<div class="m-head">
		<#include "common/navigation_bar.ftl" encoding="UTF-8">
	</div>
	<div class="blank-split"></div>
	<div class="m-content">
		<#include "common/fluentmenu.ftl" encoding="UTF-8">
		<div class="blank-split"></div>
		<#include "common/articleList.ftl" encoding="UTF-8">
		<div class="blank-split"></div>
		<#include "common/pagination.ftl" encoding="UTF-8">
		<div class="progress-bar small" data-role="progress-bar" data-color="#ccc"></div>
	</div>
	<div class="m-foot">
		<#include "common/foot.ftl" encoding="UTF-8">
	</div>
</body>
</html>