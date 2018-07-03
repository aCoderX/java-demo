package com.acoderx.demo.jdk.concurrent.atomic;

/**
 * Created by xudi on 17-4-13.
 */
public class UnAtomic1 {
    public static void main(String args[]) throws InterruptedException {
        EvenGenerator generator = new EvenGenerator();
        new Thread(new EvenChecker(generator)).start();
        new Thread(new EvenChecker(generator)).start();
        Thread.sleep(1000000);
    }

    static class EvenGenerator {
        private long num = 0;
        private boolean canceld = false;
        public long next(){
            //这是不安全的
            num=num++;
            num=num++;
            return num;
        }
        public boolean isCanceled(){
            return canceld;
        }
        public void cancel(){
            this.canceld=true;
        }
    }
    //多线程调用generator的next方法会产生错误
    static class EvenChecker implements Runnable{
        private EvenGenerator generator;
        EvenChecker(EvenGenerator generator){
            this.generator=generator;
        }
        @Override
        public void run() {
            while (!generator.isCanceled()){
                long num = generator.next();
                if(num%2!=0){
                    generator.cancel();
                    System.out.println("error!非偶数"+num);
                }
                if(num%100000==0){
                    System.out.println(num);
                }
            }
        }
    }
}



