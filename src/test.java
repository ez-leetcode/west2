public class test {
    public static void main(String[] args) {
        West2FriedChickenRestaurant Dsj = new West2FriedChickenRestaurant(100.0);   //实例化炸鸡店对象
        Juice juice1 = new Juice("橙汁",5.0,2020,12,1);
        Juice juice2 = new Juice("橙汁",5.0,2020,12,2);
        Juice juice3 = new Juice("柠檬水",3.0,2020,12,3);
        Juice juice4 = new Juice("葡萄汁",3.0,2020,12,3);
        Beer beer1 = new Beer("百威",6.0,2020,10,24,4);
        Beer beer2 = new Beer("百威",6.0,2020,11,28,5);
        Beer beer3 = new Beer("雪津",4.0,2020,11,30,3);
        Beer beer4 = new Beer("菠萝啤",4.0,2020,12,2,2);
        Drinks [] drinks = new Drinks[] {juice1,juice2,juice3,juice4,beer1,beer2,beer3,beer4};   //toString,is_overLocalDate等函数调用已经在出售和进货等操作中出现，就不单独测试了~
        System.out.println("-----------测试进货操作(成功情况)------------");                      //进货操作成功会扣除相应的余额并输出进货商品信息
        Dsj.GetGoods(drinks);
        System.out.println("---------测试因余额不足而进货失败情况----------");                    //余额不足会抛出异常OverdraftBalanceException，输出缺少的资金
        Beer beer5 = new Beer("茅台",10000.0,2020,12,2,18);    //显然茅台资金不够，会抛出异常，输出缺少资金，但是仍然会尝试进剩下的饮料
        Juice juice5 = new Juice("西瓜汁",3.5,2020,12,2);
        Drinks [] SpDrinks = new Drinks[] {beer5,juice5};
        Dsj.GetGoods(SpDrinks);
        System.out.println("---------测试正常情况下成功出售套餐情况---------");             //出餐成功情况下会输出成功的提示信息并且输出出库饮料信息，同时获得收入，余额增加
        Dsj.Sale(3,2020,12,3);
        System.out.println("-------测试有饮料过期情况下成功出售套餐情况------");             //在出餐成功的对应的输出的基础上，会出现饮料过期被扔掉的提示，同时输出被扔掉饮料的信息
        Dsj.Sale(2,2020,12,4);                       //待售套餐含有百威，显然beer1在查找过程中发现过期了，会被丢掉并输出饮料信息
        System.out.println("--------测试饮料不存在出售套餐失败的情况--------");             //显然一瓶百威由于过期被扔掉了，一瓶已经被出售，仓库里没有百威了，套餐出售失败
        Dsj.Sale(2,2020,12,3);                       //抛出IngredientSortOutException异常，输出缺少的饮料信息
        System.out.println("------测试待售饮料过期下出售套餐失败情况--------");
        Dsj.Sale(0,2020,12,31);                      //原来的雪津已经过期了，也没有其他雪津，故出售失败，同时把它丢掉，输出饮料信息
        //大概就这么多吧~
    }
}