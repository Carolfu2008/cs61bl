/**
 * Created by ENVY on 6/23/2016.
 */
public class TriangleDrawer {
    public static void main(String[] args) {
        int col = 1;
        int row = 1;
        int SIZE = 10;
        while (row <= SIZE) {
            while (col <= row) {
                System.out.print ('*');
                col++;
            }
            System.out.println();
            row++;
            col = 0;
        }
    }
}
