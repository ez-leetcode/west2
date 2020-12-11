import java.util.Scanner;

public class MyThread implements Runnable{
    protected int x;
    protected long ans = 0;
    protected long start_way;
    public MyThread(int x,long start_way){
        this.x = x;
        this.start_way = start_way;
    }
    public static Boolean contain(long num,int x){
        return String.valueOf(num).contains(String.valueOf(x));
    }
    public void run(){
        for(long i = 25000000 * (start_way - 1);i < 25000000 * start_way;i++){
            if(contain(i,x)){
                ans += i;
            }
        }
    }
    public static long runs(int k){
        long ans1 = 0;
        for(long i = 0;i < 100000000;i++){
            if(contain(i,k)){
                ans1 += i;
            }
        }
        return ans1;
    }
    public static void main(String[] args) throws InterruptedException{
        System.out.println("------------请输入一个数来进行测试------------");
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        System.out.println("------测试开始运行，可能会慢一点，请耐心等待------");
        MyThread works1 = new MyThread(x,1);           //分成4个子任务
        MyThread works2 = new MyThread(x,2);
        MyThread works3 = new MyThread(x,3);
        MyThread works4 = new MyThread(x,4);
        Thread thread1 = new Thread(works1);
        Thread thread2 = new Thread(works2);
        Thread thread3 = new Thread(works3);
        Thread thread4 = new Thread(works4);
        double t1 = System.currentTimeMillis();   //获取并行运算开始时的系统时间
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();    //实现线程并行
        thread2.join();
        thread3.join();
        thread4.join();
        long ans1 = works1.ans + works2.ans + works3.ans + works4.ans;   //获取并行运算结果
        double t2 = System.currentTimeMillis();        //获取并行运算结束时的系统时间(同时也是串行运算的开始时间)
        long ans2 = runs(x);                           //获取串行运算结果
        double t3 = System.currentTimeMillis();        //获取串行运算结束时的系统时间
        System.out.println("并行运算结果：" + ans1);
        System.out.println("串行运算结果：" + ans2);
        System.out.println("并行运算耗时：" + (t2 - t1) + "ms");
        System.out.println("串行运算耗时：" + (t3 - t2) + "ms");
        System.out.println("并行运算加速比：" + ((t3 - t2) / (t2 - t1)) * 100 + "%");
    }
}
/*
原来的测试数据太大了，上述代码数据规模少了一个0，方便测试
可以直接运行然后随便输入一个数字，来测试哦~
以下是原数据(1-1000000000)在我的设备上的运行结果：
6
并行运算结果：313464208686535791
串行运算结果：313464208686535791
并行运算耗时：21589.0ms
串行运算耗时：68607.0ms
并行运算加速比：317.786835888647%
*/