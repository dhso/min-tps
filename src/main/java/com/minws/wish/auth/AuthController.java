/**
 * 
 */
/**
 * @author Administrator
 *
 */
package com.minws.wish.auth;

import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;

import javax.servlet.ServletException;

import org.apache.http.client.ClientProtocolException;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.minws.wish.frame.kit.Identities;
import com.minws.wish.frame.kit.StringUtils;
import com.minws.wish.frame.kit.properties.ProsMap;
import com.minws.wish.frame.sdk.ace.AceKit;
import com.minws.wish.frame.sdk.qqConnect.QQConnect;
import com.minws.wish.frame.sdk.qqConnect.QQMe;
import com.minws.wish.frame.sdk.qqConnect.QQToken;
import com.minws.wish.frame.sdk.qqConnect.QQUserInfo;

public class AuthController extends Controller {

	@Before(LoginInterceptor.class)
	public void index() {
		String sso_access_token = getCookie("sso_access_token", "");
		Serializable ser = AceKit.cacheGet(sso_access_token);
		QQUserInfo qquerInfo = (null != ser) ? (QQUserInfo) ser : null;
		setAttr("qquerInfo", qquerInfo);
		render("index.jsp");
		return;
	}

	public void login() {
		String qstr = getRequest().getQueryString();
		qstr = (!StringUtils.isNotBlank(qstr)) ? "" : qstr;
		String key = Identities.uuid2();
		setAttr("sso_state", key);
		setAttr("sso_qstr", "?" + key);
		setCookie("sso_state", key, 365 * 24 * 60 * 60, "/", ProsMap.getStrPro("sso.cookie.domain"));
		// 缓存key->URL一天
		AceKit.cachePut(key, qstr, 60 * 60 * 24);
		render("login.jsp");
		return;
	}

	public void qqAuth() throws ClientProtocolException, IOException, ServletException {
		String state = getPara("state", "");
		String cookieState = getCookie("sso_state", "");
		// CSRF攻击检测
		if (!cookieState.equals(state)) {
			renderText("state不合法，可能受到CSRF攻击！");
			return;
		}

		String appId = ProsMap.getStrPro("sso.qqauth.appId");
		String appKey = ProsMap.getStrPro("sso.qqauth.appKey");
		String code = getPara("code", "");
		String redirectUrl = ProsMap.getStrPro("sso.qqauth.redirectQQInfoUrl") + "?" + state;
		String qqUserInfoStr = "";
		if (StringUtils.isNotBlank(appId) && StringUtils.isNotBlank(appKey) && StringUtils.isNotBlank(code) && StringUtils.isNotBlank(redirectUrl)) {
			// 获取accessToken、expiresIn
			QQToken qqToken = QQConnect.getToken(appId, appKey, code, redirectUrl);
			String accessToken = qqToken.getAccessToken();
			Integer expiresIn = qqToken.getExpiresIn();
			if (StringUtils.isNotBlank(accessToken)) {
				setCookie("sso_access_token", accessToken, expiresIn, "/", ProsMap.getStrPro("sso.cookie.domain"));
				// 获取openId、clientId
				QQMe qqMe = QQConnect.getMe(accessToken);
				String openId = qqMe.getOpenId();
				String clientId = qqMe.getClientId();
				if (StringUtils.isNotBlank(openId) && StringUtils.isNotBlank(clientId)) {
					// 获取QQUserInfoStr
					qqUserInfoStr = QQConnect.getUserInfoStr(accessToken, clientId, openId);
					QQUserInfo qqUserInfo = QQConnect.convertUserInfoStr2UserInfo(qqUserInfoStr);
					if (null != qqUserInfo && qqUserInfo.getRet() == 0) {
						// 缓存用户信息
						AceKit.cachePut(accessToken, qqUserInfoStr, expiresIn);
						setCookie("sso_nickName", URLEncoder.encode(qqUserInfo.getNickname(), "utf-8"), expiresIn, "/", ProsMap.getStrPro("sso.cookie.domain"));
					}
				}
			}
		}
		
		setAttr("qqUserInfoStr", qqUserInfoStr);

		String urlKey = getRequest().getQueryString().replaceAll("&code=" + code, "").replaceAll("/?code=" + code, "").replaceAll("&state=" + state, "").replaceAll("/?state=" + state, "");
		if (StringUtils.isNotBlank(urlKey)) {
			Serializable urlValue = AceKit.cacheGet(urlKey);
			String gotoUrl = (null != urlValue) ? urlValue.toString().replace("goto=", "") : "";
			if (StringUtils.isNotBlank(gotoUrl)) {
				setAttr("gotoUrl", gotoUrl);
				render("goto.jsp");
				return;
			}
		}

		render("qqInfo.jsp");
		return;
	}

}