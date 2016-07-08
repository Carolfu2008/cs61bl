public class FrenchRevolutionaryDate extends Date {

    // In a nonleap year in the French Revolutionary Calendar,
    // the first twelve months have 30 days and month 13 has five days.
    public FrenchRevolutionaryDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public Date nextDate() {
        int D = this.dayOfYear() + 1;
        if(D == 366) return new GregorianDate(year()+1,1,1);
        int i = 0;
        for(i = 0;i < 12 && D - 30 > 0;i++){
            D -= 30;
        }
        return new GregorianDate(year(), i+1, D);
    }

    @Override
    public int dayOfYear() {
        return (month() - 1) * 30 + dayOfMonth();
    }



}
