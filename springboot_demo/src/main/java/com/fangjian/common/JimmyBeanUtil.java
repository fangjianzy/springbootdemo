package com.fangjian.common;

import com.alibaba.fastjson.JSONObject;

/**  
 * 类说明   
 *  
 * @author Jimmy.Fang
 * @date 2020年3月20日  新建  
 */
public class JimmyBeanUtil {
	
	public static String toJson(Object bean){
		String obj = JSONObject.toJSONString(bean);
		return obj;
	}
	
}
