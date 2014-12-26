package com.minws.wish.frame.sdk.qqConnect;

import java.io.Serializable;

public class QQMe implements Serializable {

	private static final long serialVersionUID = -2692589625768025008L;
	private String openId;
	private String clientId;

	public String getOpenId() {
		return openId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public QQMe() {

	}

	public QQMe(String openId, String clientId) {
		this.openId = openId;
		this.clientId = clientId;
	}
}
