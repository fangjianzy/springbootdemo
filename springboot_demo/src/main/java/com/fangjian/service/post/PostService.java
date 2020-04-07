package com.fangjian.service.post;

import java.util.List;

import com.fangjian.dto.PostDTO;

/**  
 * 类说明   
 *  
 * @author Jimmy.Fang
 * @date 2020年3月20日  新建  
 */
public interface PostService {
	List<PostDTO> fingUserPostList(long userId);
}
