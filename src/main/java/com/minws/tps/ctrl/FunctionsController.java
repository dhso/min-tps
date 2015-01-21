package com.minws.tps.ctrl;

import com.jfinal.core.Controller;

/**
 * Created by unlimited on 2014/4/9.
 */

public class FunctionsController extends Controller {

    public void index() {
        render("index.html");
    }

    public void login() {}

    public void logout() {}

    public void err404() {
        render("err404.html");
    }

    public void err500() {
        render("err500.html");
    }
}
