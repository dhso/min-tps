/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.tps.ctrl;

import cn.dreampie.shiro.core.SubjectKit;

import com.jfinal.core.Controller;
import com.jfinal.log.Logger;
import com.minws.frame.plugin.shiro.model.User;
import com.minws.frame.route.ControllerBind;

@ControllerBind(controllerKey = "/auth", viewPath = "page")
public class AuthController extends Controller {
	private static final Logger logger = Logger.getLogger(AuthController.class);

	public void login() {
		render("login.ftl");
	}

	public void authed() {
		setAttr("isAuthed", SubjectKit.isAuthed());
		renderJson();
	}

	/***
	   */
	public void forget() {
		User u = getModel(User.class);
		User user = User.dao.findFirstBy("email =? AND deleted_at is null", u.get("email").toString());

	}

}
