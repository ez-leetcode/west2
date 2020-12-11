public class Beer extends Drinks{
    protected float jjd;
    public Beer(String name,double cost,int year,int month,int day,float jjd){
        super(name,cost,year,month,day);
        this.jjd = jjd;
        this.bzq = 30;
    }
    public Beer(String name,float jjd){
        super(name);
        this.jjd = jjd;
    }
    public String toString(){
        return  "饮料：" + name + '\n'
                + "成本：" + cost + '\n'
                + "保质期：" + bzq + "天" + '\n'
                + "酒精度：" + jjd + '\n'
                + "生产日期：" + s.toString() + '\n';
    }
}