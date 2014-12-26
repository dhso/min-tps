/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.wish.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.jfinal.core.Controller;
import com.jfinal.log.Logger;
import com.jfinal.upload.UploadFile;
import com.minws.wish.frame.kit.HttpUtils;
import com.minws.wish.frame.kit.StringUtils;
import com.minws.wish.frame.kit.properties.PropertiesLoader;
import com.minws.wish.frame.kit.properties.ProsMap;
import com.minws.wish.frame.sdk.qiniu.QiniuKit;
import com.qiniu.api.rsf.ListItem;

public class PageController extends Controller {
	private static final Logger logger = Logger.getLogger(PageController.class);
	
	public void index() {
		//List list = QiniuKit.list(ProsMap.getStrPro("wish.qiniu.bucket"));
		
		render("index.ftl");
		return;
	}
	
	public void upload(){
		UploadFile uf =  getFile();
		System.out.print(uf.getFileName());
		QiniuKit.put(ProsMap.getStrPro("wish.qiniu.bucket"), uf.getFileName(), uf.getFile());
	}
	
	public void wishWall(){
		render("wish_wall.ftl");
		return;
	}
	
	public void picWall(){
		List<ListItem> list = QiniuKit.list(ProsMap.getStrPro("wish.qiniu.bucket"),"wedding");
		List<String> picList = new ArrayList<String>();
		Iterator<ListItem> itr = list.iterator();
		while (itr.hasNext()) {
		    picList.add(itr.next().key);
		}
		logger.debug("qiniu");
		setAttr("picList", picList);
		render("pic_wall.ftl");
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