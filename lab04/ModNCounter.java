/**
 * Created by ENVY on 6/24/2016.
 */
public class ModNCounter extends Counter{
    int N;
    public ModNCounter(int N) {
        this.N = N;
        setMyCount(getMyCount() % N);
    }
    @Override
    public int value(){
        return getMyCount() % N;
    }
    public static void main(String arg[]){
        ModNCounter modCounter = new ModNCounter(3);
        modCounter.increment();
        modCounter.increment();
        modCounter.increment();
        modCounter.increment();
        System.out.println(modCounter.value()); // prints 1

    }
}
