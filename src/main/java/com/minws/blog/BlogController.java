/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.blog;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.jfinal.core.Controller;
import com.jfinal.log.Logger;
import com.minws.frame.kit.HttpUtils;
import com.minws.frame.kit.StringUtils;

public class BlogController extends Controller {
	private static final Logger logger = Logger.getLogger(BlogController.class);
	
	public void index() {
		//List list = QiniuKit.list(ProsMap.getStrPro("wish.qiniu.bucket"));
		render("index.ftl");
		return;
	}
	
	public void qqLogin() throws ClientProtocolException, IOException{
		String openId =getPara("openid","");
		String accessToken = getPara("access_token","");
		String getRes = "";
		if(StringUtils.isNotBlank(openId) && StringUtils.isNotBlank(accessToken)){
			String url = "https://graph.qq.com/user/get_user_info?access_token="+accessToken+"&oauth_consumer_key=101167626&openid="+openId;
			//getRes = AceKit.httpGet(url);
			getRes = HttpUtils.httpGet(url);
		}
		logger.debug(getRes);
		renderJson(getRes);
		return;
	}
}