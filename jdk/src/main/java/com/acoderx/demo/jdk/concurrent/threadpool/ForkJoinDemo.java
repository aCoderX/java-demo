package com.acoderx.demo.jdk.concurrent.threadpool;

import java.util.concurrent.*;

/**
 * Created by xudi on 2018/7/2.
 */
public class ForkJoinDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask forkJoinTask = new PrintTask(2,5);
        forkJoinPool.execute(forkJoinTask);

        System.out.println("-----------");
        ForkJoinTask sumTask = new SumTask(1,10);
        ForkJoinTask task = forkJoinPool.submit(sumTask);
        System.out.println(task.get());

        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        forkJoinPool.shutdown();
    }
}
class PrintTask extends RecursiveAction{
    private static final int THRESHOLD = 5; //最多一次性打印5个数
    private int start;
    private int end;

    public PrintTask(int start, int end) {
        this.start = start;
        this.end = end;
    }
    @Override
    protected void compute() {
        if (end - start < THRESHOLD) {
            for (int i=start;i<=end;i++) {
                System.out.println(Thread.currentThread().getName()+"-----"+i);
            }
        }else{
            int mid = (end + start) / 2;
            new PrintTask(start, mid).fork();
            new PrintTask(mid+1, end).fork();
        }
    }
}

class SumTask extends RecursiveTask<Integer>{
    private int start;
    private int end;
    public SumTask(int start, int end){
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        if(end-start<3){
            int s=0;
            for (int i=start;i<=end;i++) {
                s = s + i;
            }
            return s;
        }else{
            int mid = (end + start) / 2;
            SumTask a = new SumTask(start,mid);
            SumTask b = new SumTask(mid+1,end);
            a.fork();
            b.fork();
            return a.join() + b.join();
        }
    }
}