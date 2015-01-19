package com.minws.frame.plugin.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class HasRoleFreeMarkerMethod implements TemplateMethodModel {
	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List list) throws TemplateModelException {
		if (null == list || 1 != list.size()) {
			throw new TemplateModelException("Wrong arguments: only one argument is allowed");
		}

		String roleName = (String) list.get(0);
		return getSubject() != null && roleName != null && roleName.length() > 0 && getSubject().hasRole(roleName);
	}

	private static Subject getSubject() {
		return SecurityUtils.getSubject();
	}
}
