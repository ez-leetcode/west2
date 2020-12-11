public class SetMeal {
    protected String name;
    protected String chicken_name;
    protected double price;
    protected Drinks drink;
    public SetMeal(String name,String chicken_name,double price,Drinks drink){
        this.name = name;
        this.chicken_name = chicken_name;
        this.price = price;
        this.drink = drink;
    }
    public String toString(){
        return "套餐：" + name + '\n'
                + "价格：" + price + '\n'
                + "套餐包含以下内容：" + '\n'
                + "炸鸡：" + chicken_name + '\n'
                + "饮料：" + drink.name + '\n';
    }
}