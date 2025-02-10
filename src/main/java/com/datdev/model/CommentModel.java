package com.datdev.model;

import java.sql.Date;
import java.sql.Timestamp;

public class CommentModel extends AbstractModel<CommentModel> {
	private  String content;
	private Long  userId;
	private Long newsId;


	public CommentModel(long id, Timestamp createDate, Timestamp modifireDate, String createBy, String modifireBy, String content,
						Long userId, Long newsId) {
		super(id, createDate, modifireDate, createBy, modifireBy);
		this.content = content;
		this.userId = userId;
		this.newsId = newsId;
	}

	public CommentModel(String content, Long userId, Long newsId) {
		super();
		this.content = content;
		this.userId = userId;
		this.newsId = newsId;
	}

	public CommentModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}




}
