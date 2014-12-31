/**
 * @author hadong
 *
 */
package com.minws.blog.entity;

import java.io.Serializable;

public class ArticleContent implements Serializable {

	private static final long serialVersionUID = -793434851346619196L;
	private Long id;
	private Long tid;
	private String content;

	public Long getId() {
		return id;
	}

	public Long getTid() {
		return tid;
	}

	public String getContent() {
		return content;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public void setContent(String content) {
		this.content = content;
	}

}