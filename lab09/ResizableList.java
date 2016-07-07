/**
 * Created by ENVY on 7/7/2016.
 */
public class ResizableList extends FixedSizeList {
    public ResizableList() {}
    public void add(int i, int k) {
        if(count == values.length){
            int[] val = new int[values.length];
            for (int p = 0; p < values.length; p++){
                val[p] = values[p];
            }
            values = new int[(val.length+1) * 2];
            for (int p = 0; p < val.length;p++){
                values[p] = val[p];
            }
        }
        for (int p = i + 1; p <= count; p++) {
            values[p] = values[p-1];
        }
        values[i] = k;
        count++;
    }
    public void add(int k) {
        // YOUR CODE HERE
        if(count == values.length){
            int[] val = new int[values.length];
            for (int i = 0; i<values.length; i++){
                val[i] = values[i];
            }
            values = new int[(val.length+1) * 2];
            for (int i = 0; i < val.length;i++){
                values[i] = val[i];
            }
        }
        for(int i = 0; i < values.length; i++){
            if(values[i] == 0){
                values[i] = k;
                break;
            }
        }
        count ++;
    }
}
