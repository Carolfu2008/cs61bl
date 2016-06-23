/**
 * Created by ENVY on 6/23/2016.
 */
public class TriangleDrawer2 {
    public static void main(String[] args) {
        System.out.println();
        for (int col = 1,row = 1,SIZE = 10;row <= SIZE;row++,col=1) {
            for (;col <= row;col++) {
                System.out.print ('*');
                }
            System.out.println();
        }
    }
}
