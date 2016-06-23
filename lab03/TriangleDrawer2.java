/**
 * Created by ENVY on 6/23/2016.
 */
public class TriangleDrawer2 {
    public static void main(String[] args) {
        System.out.println();
        for (int row = 0,SIZE = 10;row < SIZE;row++) {
            for (int col = 0;col < row;col++) {
                System.out.print ('*');
            }
            System.out.println('*');
        }
    }
}
