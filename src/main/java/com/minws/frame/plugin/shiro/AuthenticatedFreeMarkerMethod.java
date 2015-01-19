package com.minws.frame.plugin.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class AuthenticatedFreeMarkerMethod implements TemplateMethodModel {
	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List list) throws TemplateModelException {
		return getSubject() != null && getSubject().isAuthenticated();
	}

	private static Subject getSubject() {
		return SecurityUtils.getSubject();
	}
}
