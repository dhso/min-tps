/**
 * 
 */
/**
 * @author Administrator
 *
 */
package com.minws.wish.system;

import com.jfinal.core.Controller;

public class SystemController extends Controller{
	public void index() {
		
		render("index.ftl");
		return;
	}
	
	public void login(){
		render("login.jsp");
		return;
	}
	
	public void dashboard(){
		render("dashboard.jsp");
		return;
	}
	
	public void forgotPassword(){
		render("forgot_password.jsp");
		return;
	}
	
	public void forms(){
		render("forms.jsp");
		return;
	}
	
	public void tables(){
		render("tables.jsp");
		return;
	}
}
