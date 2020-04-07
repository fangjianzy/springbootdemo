package com.fangjian.threed.pool;
/**
 * @description: 任务队列满时的拒绝策略
 * @author: 
 * @create: 
 **/

@FunctionalInterface
public interface DenyPolicy {

    void reject(Runnable runnable, ThreadPool threadPool);

    //丢弃式拒绝
    class DiscardDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            //do nothing
        }
    }

    //抛出异常式拒绝
    class AbortDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new RunnableDenyException("The runnable " + runnable + "will be abort.");
        }
    }

    //提交者线程中执行任务

    class RunnerDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if (!threadPool.isShutdown()){
                runnable.run();
            }
        }
    }
}