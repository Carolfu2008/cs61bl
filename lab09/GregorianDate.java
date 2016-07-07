public class GregorianDate extends Date {

    public static int[] monthLengths = {31, 28, 31, 30, 31, 30, 31,
        31, 30, 31, 30, 31};

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public Date nextDate() {
        int D = this.dayOfYear() + 1;
        if(D == 366) return new GregorianDate(year()+1,1,1);
        int i = 0;
        for(i = 0;i < 12 && D - monthLengths[i] > 0;i++){
            D -= monthLengths[i];
        }
        return new GregorianDate(year(), i+1, D);
    }

    @Override
    public int dayOfYear() {
        int rtnValue = 0;
        for (int m = 0; m < month() - 1; m++) {
            rtnValue += monthLengths[m];
        }
        return rtnValue + dayOfMonth();
    }
}
