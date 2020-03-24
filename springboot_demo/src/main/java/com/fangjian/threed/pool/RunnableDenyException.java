package com.fangjian.threed.pool;
/**
 * @description:通知任务提交者，任务队列无法接收新任务
 * @author: 
 * @create: 
 **/

public class RunnableDenyException extends RuntimeException {

    public RunnableDenyException(String s) {
        super(s);
    }
}