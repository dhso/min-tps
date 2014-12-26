package com.minws.wish.frame.sdk.qqConnect;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minws.wish.frame.kit.StringUtils;
import com.minws.wish.frame.sdk.ace.AceKit;

public class QQConnect {

	/**
	 * 获取accessToken和expiresIn
	 * 
	 * @param appId
	 * @param appKey
	 * @param code
	 * @param redirectUrl
	 * @return
	 */
	public static QQToken getToken(String appId, String appKey, String code, String redirectUrl) {
		String accessTokenUrl = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=" + appId + "&client_secret=" + appKey + "&code=" + code + "&redirect_uri=" + redirectUrl;
		String AccessTokenRes = AceKit.httpGet(accessTokenUrl);
		String accessToken = "";
		Integer expiresIn = -1;
		if (StringUtils.isNotBlank(AccessTokenRes)) {
			Map<String, Object> mapres = StringUtils.getUrlParams(AccessTokenRes);
			Object accessTokenObj = mapres.get("access_token");
			accessToken = (null != accessTokenObj) ? accessTokenObj.toString() : "";
			Object expiresInObj = mapres.get("expires_in");
			expiresIn = (null != expiresInObj) ? StringUtils.toInteger(expiresInObj.toString()) : -1;
		}
		return new QQToken(accessToken, expiresIn);
	}

	/**
	 * 获取openId和clientId
	 * 
	 * @param accessToken
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public static QQMe getMe(String accessToken) throws JsonProcessingException, IOException {
		String openId = "";
		String clientId = "";
		if (StringUtils.isNotBlank(accessToken)) {
			String openIdUrl = "https://graph.qq.com/oauth2.0/me?access_token=" + accessToken;
			String openIdRes = AceKit.httpGet(openIdUrl);
			if (StringUtils.isNotBlank(openIdRes)) {
				openIdRes = StringUtils.replace(openIdRes, "callback(", "");
				openIdRes = StringUtils.replace(openIdRes, ");", "");
				JsonNode rootNode = new ObjectMapper().readTree(openIdRes);
				clientId = rootNode.path("client_id").asText();
				openId = rootNode.path("openid").asText();
			}
		}
		return new QQMe(openId, clientId);
	}

	/**
	 * 获取QQUserInfoStr
	 * 
	 * @param accessToken
	 * @param clientId
	 * @param openId
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String getUserInfoStr(String accessToken, String clientId, String openId) throws JsonParseException, JsonMappingException, IOException {
		String userInfoRes = "";
		if (StringUtils.isNotBlank(openId) && StringUtils.isNotBlank(clientId)) {
			String userInfoUrl = "https://graph.qq.com/user/get_user_info?access_token=" + accessToken + "&oauth_consumer_key=" + clientId + "&openid=" + openId;
			userInfoRes = AceKit.httpGet(userInfoUrl);
		}
		return userInfoRes;
	}

	/**
	 * convertUserInfoStr2UserInfo
	 * 
	 * @param qqUserInfoStr
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static QQUserInfo convertUserInfoStr2UserInfo(String qqUserInfoStr) throws JsonParseException, JsonMappingException, IOException {
		QQUserInfo qqUserInfo = new QQUserInfo();
		if (StringUtils.isNotBlank(qqUserInfoStr)) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			qqUserInfo = mapper.readValue(qqUserInfoStr, QQUserInfo.class);
		}
		return qqUserInfo;
	}

	/**
	 * 获取QQUserInfo
	 * 
	 * @param accessToken
	 * @param clientId
	 * @param openId
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static QQUserInfo getUserInfo(String accessToken, String clientId, String openId) throws JsonParseException, JsonMappingException, IOException {
		String userInfoRes = getUserInfoStr(accessToken, clientId, openId);
		return convertUserInfoStr2UserInfo(userInfoRes);
	}

}
