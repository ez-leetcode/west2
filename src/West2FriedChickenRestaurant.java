import java.util.ArrayList;
import java.util.LinkedList;

public class West2FriedChickenRestaurant implements FriedChickenRestaurant{        //实现FriedChickenRestaurant接口
    private double Account;
    protected LinkedList <Beer> BeerList = new LinkedList<>();
    protected LinkedList <Juice> JuiceList = new LinkedList<>();
    //啤酒列表和果汁列表经常涉及出售套餐(即删除)，进货(即插入)操作，如果使用普通数组或者ArrayList
    //首先，数组大小无法确定，普通数组会浪费空间，用ArrayList虽然不会浪费空间
    //但是删除操作的平均时间复杂度O(n)(因为涉及后续元素前移)，时间开销比较大
    //在频繁地进货和出售的情况下(除非生意不好...)，使用插入和删除操作仅需O(1)时间的LinkedList更好
    protected static ArrayList <SetMeal> MealList = new ArrayList<>();
    //由于套餐初始化后就不改变，为了加快访问效率，使用ArrayList
    public West2FriedChickenRestaurant(double Account){
        this.Account = Account;
        System.out.println("西二炸鸡店创建成功！启动资金：" + Account);
        System.out.println("加油！打工人");
        /* MealList.add(new SetMeal("超值套餐1","无骨炸鸡",15,new Beer("雪津",4.0,2020,11,30,3)));
        MealList.add(new SetMeal("超值套餐2", "韩式炸鸡", 18,new Juice("橙汁",5.0,2020,11,30)));
        MealList.add(new SetMeal("经典套餐1","脆皮炸鸡",20,new Beer("百威",6.0,2020,11,30,5)));
        MealList.add(new SetMeal("经典套餐2","蜂蜜芥末炸鸡",22,new Juice("柠檬水",3.0,2020,11,30)));*/
    }
    static {
        MealList.add(new SetMeal("超值套餐1","无骨炸鸡",15,new Beer("雪津",3)));             //静态菜单MealList
        MealList.add(new SetMeal("超值套餐2", "韩式炸鸡", 18,new Juice("橙汁")));
        MealList.add(new SetMeal("经典套餐1","脆皮炸鸡",20,new Beer("百威",5)));
        MealList.add(new SetMeal("经典套餐2","蜂蜜芥末炸鸡",22,new Juice("柠檬水")));
    }
    private boolean use(Beer b ,int curr_year,int curr_month,int curr_day){
        boolean succeed = false;
        for(int i = 0 ; i < BeerList.size();i++){                         //foreach不能在遍历中进行删除操作，所以不用
            Beer x = BeerList.get(i);
            if(x.is_overLocalDate(curr_year,curr_month,curr_day)){
                BeerList.remove(i);
                i--;                            //由于remove，所以要减一来抵消i++
                System.out.println("发现已过期的啤酒，已销毁");
                System.out.println("具体信息：");
                System.out.println(x.toString());
            }else if(!x.is_overLocalDate(curr_year,curr_month,curr_day) && x.name.equals(b.name) && x.jjd == b.jjd){       //不仅名称相同，酒精度也要相同
                b = x;                         //把具体的啤酒信息给b,来toString
                BeerList.remove(i);
                succeed = true;
                break;
            }
        }
        try {
            CheckOverIngredient(b,succeed);
        }catch (IngredientSortOutException e1){
            e1.PrintInformation();
        }
        return succeed;
    }
    private boolean use(Juice j,int curr_year,int curr_month,int curr_day){
        boolean succeed = false;
        for(int i = 0; i < JuiceList.size();i++){
            Juice x = JuiceList.get(i);
            if(x.is_overLocalDate(curr_year,curr_month,curr_day)){
                JuiceList.remove(x);
                i--;                      //同理
                System.out.println("发现已过期的果汁，已销毁");
                System.out.println("具体信息：");
                System.out.println(x.toString());
            }else if(!x.is_overLocalDate(curr_year,curr_month,curr_day) && x.name.equals(j.name)){
                j = x;                    //同理
                JuiceList.remove(x);
                succeed = true;
                break;
            }
        }
        try {
            CheckOverIngredient(j,succeed);
        }catch (IngredientSortOutException e1){
            e1.PrintInformation();
        }
        return succeed;
    }
    public void CheckOverAccount(Drinks drink,double cost) throws OverdraftBalanceException{                     //尝试进货的函数，出现资金不够的情况抛出OverdraftBalanceException
        if(Account < cost){
            throw new OverdraftBalanceException(cost - Account);
        }else{
            Account -= cost;                                                 //资金足够情况下，支付资金，输出进货信息
            System.out.println("进货成功！");
            System.out.println("商品价格：" + cost +" 余额：" + Account);
            System.out.println("商品具体信息：");
            System.out.println(drink.toString());
        }
    }
    public void CheckOverIngredient(Drinks drinks,boolean flag) throws IngredientSortOutException{             //判断是否找到所需饮料，没找到抛出IngredientSortOutException
        if(!flag){
            throw new IngredientSortOutException(drinks);
        }else{
            System.out.println("饮料出库成功！");
            System.out.println("饮料具体信息：");
            System.out.println(drinks.toString());
        }
    }
    public void Sale(int number,int curr_year,int curr_month,int curr_day) {
        SetMeal my_meals = MealList.get(number);
        boolean judge;                                       //judge判断饮料查找是否成功
        if(my_meals.drink instanceof Beer){
            judge = use((Beer) my_meals.drink,curr_year,curr_month,curr_day);
        }else{
            judge = use((Juice) my_meals.drink,curr_year,curr_month,curr_day);
        }
        if(judge){                                     //饮料查找成功，资金进账同时输出套餐具体信息，余额等信息
            Account += my_meals.price;
            System.out.println("出餐成功！");
            System.out.println("套餐具体信息：");
            System.out.println(my_meals.toString());
            System.out.println("收入：" + my_meals.price + " 当前余额：" + Account);
        }
    }
    public void GetGoods(Drinks [] drink){    //批量进货，用数组对应下标
        for(Drinks i:drink){
            try{
                CheckOverAccount(i,i.cost);
            }catch (OverdraftBalanceException e1){
                e1.PrintInformation();
                //break;                                  //出现资金不够的情况不会break，会继续尝试进接下来的货
            }
            if(i instanceof Beer){
                BeerList.add((Beer)i);
            }else{
                JuiceList.add((Juice)i);
            }
        }
    }
}