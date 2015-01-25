<#assign head_title = "首页">
<!DOCTYPE html>
<html lang="en">
<head><#include "common/head.ftl" encoding="UTF-8">
</head>
<body>
	<div class="easyui-layout"  data-options="fit:true,border:true">
        <div data-options="region:'north'" style="height:80px;">
        	<div class="logo"></div>
        	<div class="f-right top-right">
        		<span>主题：</span><select id="cb-theme" style="width:120px"></select>
        		<p class="blank-split"></p>
        		<span>亲爱的 <@shiro.principal/> (<@shiro.hasRole name="admin">管理员</@shiro.hasRole>) 你好!</span>
	        	<@shiro.authenticated>
			  		&nbsp;<a href="${baseUrl}/security/signout">退出</a>
				</@shiro.authenticated>
        	</div>
			
		</div>
        
        <div data-options="region:'west',split:false" title="菜单" style="width:100px;">
            <#include "common/meun.ftl" encoding="UTF-8">
        </div>
        <div data-options="region:'east',split:false" title="我的信息" style="width:202px;">
            <div class="easyui-calendar" id="myCalendar"></div>
        </div>
        <div data-options="region:'south',split:false" style="height:50px;"></div>
        <div data-options="region:'center',title:'Main Title',iconCls:'icon-ok'">
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
                <div title="About" data-options="href:'_content.html'" style="padding:10px"></div>
                <div title="DataGrid" style="padding:5px">
                    <table class="easyui-datagrid"
                            data-options="url:'datagrid_data1.json',method:'get',singleSelect:true,fit:true,fitColumns:true">
                        <thead>
                            <tr>
                                <th data-options="field:'itemid'" width="80">Item ID</th>
                                <th data-options="field:'productid'" width="100">Product ID</th>
                                <th data-options="field:'listprice',align:'right'" width="80">List Price</th>
                                <th data-options="field:'unitcost',align:'right'" width="80">Unit Cost</th>
                                <th data-options="field:'attr1'" width="150">Attribute</th>
                                <th data-options="field:'status',align:'center'" width="50">Status</th>
                            </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
	<div class="m-foot"><#include "common/foot.ftl" encoding="UTF-8">
	</div>
</body>
</html>