public abstract class Drinks {
    protected String name;
    protected double cost;
    protected int bzq;
    protected LocalDate s;
    public Drinks(String name,double cost,int year,int month,int day){
        this.name = name;
        this.cost = cost;
        s = new LocalDate(year,month,day);
    }
    public Drinks(String name){          //给静态的SetMeal合适的构造函数，因为套餐中只关心饮料的名字
        this.name = name;
    }
    public abstract String toString();
    public boolean is_overLocalDate(int cur_year,int cur_month,int cur_day){  //以下是一个根据日期来
         int [] a = {31,28,31,30,31,30,31,31,30,31,30,31};
         int cnt1 = 0, cnt2 = 0;
         if(cur_year % 400 == 0 || cur_year % 4 == 0 && cur_year % 100 != 0){
             a[1] = 29;
         }
         for(int i = 0;i < cur_month-1;i++){
             cnt1 += a[i];
         }
         cnt1 += cur_day;
         a[1] = 28;
         if(s.year % 400 == 0 || s.year % 4 == 0 && s.year % 100 != 0){
            a[1] = 29;
         }
         for(int i = 0;i < s.month-1;i++){
             cnt2 += a[i];
         }
         cnt2 += s.day;
         if(cur_year == s.year && cnt1-cnt2 > bzq) {
             return true;
         }else if(cur_year == s.year && cnt1-cnt2 <= bzq){
             return false;
         }else if(cur_year != s.year){
             int cnt3;//跨年情况
             if(cur_month>1||s.month<12){  //考虑到啤酒和果汁的保质期为2/30天就不考虑类似多年和多个月之间的精确地时间间隔计算（因为肯定过保质期了嘛）
                 return true;
             }else{
                 cnt3 = (365-cnt2) + cnt1;
             }
             if(a[1] == 29){     //闰年多减了一天加回去
                 cnt3++;
             }
             return cnt3 > bzq;
         }
         return false;
    }
}