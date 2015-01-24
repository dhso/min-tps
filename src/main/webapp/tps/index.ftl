<#assign head_title = "首页">
<!DOCTYPE html>
<html lang="en">
<head><#include "common/head.ftl" encoding="UTF-8">
</head>
<body>
	<div class="easyui-layout"  data-options="fit:true">
        <div data-options="region:'north'" style="height:10%">
        亲爱的
        	<@shiro.hasRole name="admin">
		管理员
		</@shiro.hasRole><@shiro.principal/>你好，
			<@shiro.authenticated>
		  		<li><a href="${baseUrl}/security/signout">退出</a></li>
			</@shiro.authenticated></div>
        <div data-options="region:'south',split:true" style="height:50px;"></div>
        <div data-options="region:'east',split:true" title="East" style="width:180px;">
            <ul class="easyui-tree" data-options="url:'tree_data1.json',method:'get',animate:true,dnd:true"></ul>
        </div>
        <div data-options="region:'west',split:true" title="West" style="width:100px;">
            <div class="easyui-accordion" data-options="fit:true,border:false">
                <div title="Title1" style="padding:10px;">
                    content1
                </div>
                <div title="Title2" data-options="selected:true" style="padding:10px;">
                    content2
                </div>
                <div title="Title3" style="padding:10px">
                    content3
                </div>
            </div>
        </div>
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