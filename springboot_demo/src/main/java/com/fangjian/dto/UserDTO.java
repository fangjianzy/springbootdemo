package com.fangjian.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**  
 * 类说明   
 *  
 * @author Jimmy.Fang
 * @date 2020年3月20日  新建  
 */
public class UserDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public long userId;
	private String userName;
	private List<PostDTO> posts = new ArrayList<PostDTO>();
	
	public List<PostDTO> getPosts() {
		return posts;
	}
	public void setPosts(List<PostDTO> posts) {
		this.posts = posts;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
