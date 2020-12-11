public class LocalDate {
    protected int year;
    protected int month;
    protected int day;
    public LocalDate(int year,int month,int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public String toString(){
        return year + " 年 " + month + " 月 " + day + " 日 ";
    }
}