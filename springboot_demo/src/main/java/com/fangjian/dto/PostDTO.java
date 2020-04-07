package com.fangjian.dto;

import java.io.Serializable;

/**  
 * 文章类说明   
 *  
 * @author Jimmy.Fang
 * @date 2020年3月20日  新建  
 */
public class PostDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long postId;
	private String postName;
	private long userId;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	
	
}
