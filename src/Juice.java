public class Juice extends Drinks{
    public Juice(String name,double cost,int year,int month,int day){
        super(name,cost,year,month,day);
        this.bzq = 2;
    }
    public Juice(String name){
        super(name);
    }
    public String toString(){
        return  "饮料："+ name +'\n'
                + "成本：" + cost + '\n'
                + "保质期：" + bzq + "天" +'\n'
                + "生产日期：" + s.toString() + '\n';
    }
}