<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="shortcut icon" href="${baseUrl}/static/img/favicon.ico" />
	<link rel="stylesheet" type="text/css" href="${baseUrl}/static/easyui/themes/bootstrap/easyui.css">
	<style type="text/css">
	.login-pannel{
		margin:200px auto;
	}
	</style>
</head>
<body>
    <div class="easyui-panel" title="登录" style="width:260px;" data-options="cls:'login-pannel'">
        <div style="padding:10px">
        <form id="login_form" action="${baseUrl}/security/signin" method="post">
            <table cellpadding="5">
                <tr>
                    <td>用户:</td>
                    <td><input class="easyui-textbox" type="text" name="username" id="username" data-options="required:true,missingMessage:'请输入用户名/手机号'"></input></td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td><input class="easyui-textbox" type="password" name="password" id="password" data-options="required:true,missingMessage:'请输入密码'"></input></td>
                </tr>
                <tr>
                	<td colspan=2>
                		<input type="checkbox" name="rememberMe" id="rememberMe" checked="checked" /><label for="rememberMe">记住我</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
					</td>
                </tr>
            </table>
        </form>
        </div>
    </div>
    <script type="text/javascript" src="${baseUrl}/static/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${baseUrl}/static/easyui/jquery.easyui.min.js"></script>
    <script>
    	function submitForm(){
    		$('#login_form').form('submit');
    	}
        function clearForm(){
            $('#login_form').form('clear');
        }
    </script>
</body>
</html>