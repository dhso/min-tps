package com.minws.tps.ctrl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

@ControllerBind(controllerKey = "/")
public class MainController extends Controller {

    @RequiresAuthentication
    public void index() {
        render("index.jsp");
    }

    public void login() {
        render("login.html");
    }

    public void doLogin() {
        String username = getPara("username");
        String password = getPara("password");
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);
            redirect("/");
        } catch (Exception e) {
            //登录失败
            e.printStackTrace();
            forwardAction("/login");
        }
    }

    public void logout() {
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.isAuthenticated()){
            currentUser.logout();
        }
        forwardAction("/login");
    }

    public void head() {
        render("head.html");
    }

    public void west() {
        render("west.html");
    }

    public void center() {
        render("center.html");
    }

    public void err401() {
        setAttr("msg", "401 Unauthorized");
        setAttr("success", false);

        renderJson();
    }

    public void err403() {
        setAttr("msg", "403 Forbidden");
        setAttr("success", false);

        renderJson();
    }

    public void err404() {
        render("err404.html");
    }

    public void err500() {
        render("err500.html");
    }
}