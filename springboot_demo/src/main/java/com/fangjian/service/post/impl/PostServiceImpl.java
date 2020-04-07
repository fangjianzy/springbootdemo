package com.fangjian.service.post.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fangjian.dto.PostDTO;
import com.fangjian.service.post.PostService;

/**  
 * 类说明   
 *  
 * @author Jimmy.Fang
 * @date 2020年3月20日  新建  
 */
@Service
public class PostServiceImpl implements PostService {

	@Override
	public List<PostDTO> fingUserPostList(long userId) {
		try { Thread.sleep(1000L); } catch (InterruptedException e) {}
		List<PostDTO> list = new ArrayList<PostDTO>();
		PostDTO p1 = new PostDTO();
		p1.setUserId(userId);
		p1.setPostId(Long.parseLong("12"));
		p1.setPostName("人间喜剧");
		
		PostDTO p2 = new PostDTO();
		p2.setUserId(userId);
		p2.setPostId(Long.parseLong("13"));
		p2.setPostName("jaba");
		list.add(p1);
		list.add(p2);
		return list;
	}

}
