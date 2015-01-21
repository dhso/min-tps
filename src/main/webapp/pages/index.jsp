<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>WebDemo</title>
    <link rel="stylesheet" type="text/css" href="${baseUrl}/static/easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="${baseUrl}/static/easyui/themes/icon.css">
    <script type="application/javascript" src="${baseUrl}/static/easyui/jquery.min.js"></script>
    <script type="application/javascript" src="${baseUrl}/static/easyui/jquery.easyui.min.js"></script>
</head>
<body>
<div class="easyui-layout" style="width:100%;height:100%;">
    <div data-options="region:'north', href:'${baseUrl}/head'" style="height: 100px"></div>
    <div data-options="region:'west', split:true, href:'${baseUrl}/west'" style="width: 200px" title="导航窗口"></div>
    <div data-options="region:'center', href:'${baseUrl}/center'"></div>
</div>
</body>
</html>
