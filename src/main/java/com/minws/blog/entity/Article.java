package com.minws.blog.entity;

import java.io.Serializable;

public class Article implements Serializable {

	private static final long serialVersionUID = -8971257061804106887L;

	private ArticleNode articleNode;
	private ArticleContent articleContent;

	public ArticleNode getArticleNode() {
		return articleNode;
	}

	public ArticleContent getArticleContent() {
		return articleContent;
	}

	public void setArticleNode(ArticleNode articleNode) {
		this.articleNode = articleNode;
	}

	public void setArticleContent(ArticleContent articleContent) {
		this.articleContent = articleContent;
	}

}
