package com.minws.frame.plugin.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class HasAnyRolesFreeMarkerMethod implements TemplateMethodModel {
	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List list) throws TemplateModelException {
		// 参数不合法直接返回false
		if (null == list || list.isEmpty()) {
			return false;
		}
		// 循环判断当前用用户是否拥有其中的某一个角色
		boolean hasAny = false;
		for (Object obj : list) {
			System.out.println(obj);
			if (getSubject().hasRole((String) obj)) {
				hasAny = true;
				break;
			}
		}
		return hasAny;
	}

	private static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

}
