public class IngredientSortOutException extends RuntimeException{
    protected Drinks message;
    public IngredientSortOutException(Drinks message){
        this.message = message;
    }
    public void PrintInformation(){
        System.out.println("出售失败，请检查库存是否充足！");
        if(message instanceof Beer){
            System.out.println("失败原因：啤酒已售完。");
            System.out.println("已售完啤酒：" + message.name + " 酒精度：" + ((Beer) message).jjd) ;    //toString方法有生产日期，出售失败的饮料是没有生产日期属性，所以不用toString
        }else{
            System.out.println("失败原因：果汁已售完。");
            System.out.println("已售完果汁：" + message.name);
        }
    }
}