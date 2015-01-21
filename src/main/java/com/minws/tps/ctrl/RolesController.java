package com.minws.tps.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.minws.frame.plugin.easyui.DataGrid;
import com.minws.tps.shiro.model.Role;

public class RolesController extends Controller {

    @RequiresPermissions("/roles")
    public void index() {
        render("index.html");
    }

    @RequiresPermissions("/roles/datagrid")
    public void datagrid() {
        List<Object> param = new ArrayList<Object>();
        StringBuffer where = new StringBuffer();
        /** 添加查询字段条件*/
        String sortName = this.getPara("sort");
        String sortOrder = this.getPara("order", "desc");
        String qryField=this.getPara("qryField");//查询字段 以逗号分隔
        if(qryField!=null&&!"".equals(qryField)){
            String[] k=qryField.split(",");
            for(String q:k){
                Object t=this.getPara(q);
                if(t!=null&&!"".equals(t)){
                    where.append(" and ");
                    if(q.endsWith("start")){
                        where.append(q.replace("start",""));
                        where.append(" >=? ");
                    }else if(q.endsWith("end")){
                        where.append(q.replace("end",""));
                        where.append(" <=? ");
                    }else{
                        where.append(q);
                        where.append(" =? ");
                    }
                    param.add(t);
                }
            }
        }
        if (sortName != null) {
            where.append(" order by ");
            where.append(sortName);
            where.append(" ");
            where.append(sortOrder);
        }
        Page<Record> p = Db.paginate(
                this.getParaToInt("page", 1),
                this.getParaToInt("rows", 20),
                "select * ", "from roles where 1=1 " + where.toString(), param.toArray());
        DataGrid dg = new DataGrid();
        dg.setRows(p.getList());
        dg.setTotal(p.getTotalRow());
        this.renderJson(dg);
    }

    @RequiresPermissions("/roles/add")
    public void add() {}

    @RequiresPermissions("/roles/insert")
    public void insert() {
        Role role = getModel(Role.class);

        if (role.save()) {
            setAttr("success", true);
            setAttr("msg", "添加成功！");
        } else {
            setAttr("success", false);
            setAttr("msg", "添加失败！");
        }

        renderJson();
    }

    @RequiresPermissions("/roles/edit")
    public void edit() {}

    @RequiresPermissions("/roles/update")
    public void update() {
        Role role = getModel(Role.class);

        if (role.update()) {
            setAttr("success", true);
            setAttr("msg", "更新成功！");
        } else {
            setAttr("success", false);
            setAttr("msg", "更新失败！");
        }

        renderJson();
    }

    @RequiresPermissions("/roles/remove")
    public void remove() {
        Role role = getModel(Role.class);

        if (role.delete()) {
            setAttr("success", true);
            setAttr("msg", "删除成功！");
        } else {
            setAttr("success", false);
            setAttr("msg", "删除失败！");
        }

        renderJson();
    }
}