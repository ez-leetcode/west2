public class OverdraftBalanceException extends RuntimeException{
    protected double message;
    public OverdraftBalanceException(double message){
        this.message = message;
    }
    public void PrintInformation(){
        System.out.println("进货失败！进货费用超出拥有余额，请及时补充资金！");
        System.out.println("资金缺口：" + message);
    }
}