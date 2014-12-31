/**
 * @author hadong
 *
 */
package com.minws.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class ArticleNode implements Serializable {

	private static final long serialVersionUID = -793434851346619196L;
	private Long id;
	private Long pid;
	private String text;
	private String state;
	private String iconCls;
	private Integer node_type;
	private Integer open;
	private String writer;
	private Date update_dt;

	public Long getId() {
		return id;
	}

	public Long getPid() {
		return pid;
	}

	public String getText() {
		return text;
	}

	public String getState() {
		return state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public Integer getNode_type() {
		return node_type;
	}

	public Integer getOpen() {
		return open;
	}

	public String getWriter() {
		return writer;
	}

	public Date getUpdate_dt() {
		return update_dt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public void setNode_type(Integer node_type) {
		this.node_type = node_type;
	}

	public void setOpen(Integer open) {
		this.open = open;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public void setUpdate_dt(Date update_dt) {
		this.update_dt = update_dt;
	}

}