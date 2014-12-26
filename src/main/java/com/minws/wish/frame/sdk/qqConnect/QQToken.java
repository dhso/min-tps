package com.minws.wish.frame.sdk.qqConnect;

import java.io.Serializable;

public class QQToken implements Serializable {

	private static final long serialVersionUID = -2692589625768025008L;
	private String accessToken;
	private Integer expiresIn;

	public String getAccessToken() {
		return accessToken;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public QQToken() {

	}

	public QQToken(String accessToken, Integer expiresIn) {
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
	}
}
