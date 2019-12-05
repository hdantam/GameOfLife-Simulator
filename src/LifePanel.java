import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;

/**
 * LifePanel class
 *
 * @author Hemant Dantam
 * @version (2019 - 05)
 * <p>
 * Sets out how the grid should work when painting/clearing spots
 */


//Draws grid/cells
public class LifePanel extends JPanel {

    boolean[][] arr;
    double width;
    double height;


    public LifePanel(boolean[][] a) {
        arr = a;
    }

    public void setArr(boolean[][] a) {
        arr = a;
    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        width = (double) this.getWidth() / arr[0].length;
        height = (double) this.getHeight() / arr.length;

        //Draws cells
        g.setColor(Color.RED);
        for (int row = 0; row < arr.length; row++) {
            for (int column = 0; column < arr[0].length; column++) {
                if (arr[row][column] == true) {
                    g.fillRect((int) Math.round(column * width), (int) Math.round(row * height), (int) width + 1, (int) height + 1);
                }
            }
        }

        //Draws grids
        g.setColor(Color.BLACK);
        for (int i = 0; i < arr[0].length + 1; i++) {
            g.drawLine((int) Math.round(i * width), 0, (int) Math.round(i * width), this.getHeight());
        }

        for (int j = 0; j < arr.length + 1; j++) {
            g.drawLine(0, (int) Math.round(j * height), this.getWidth(), (int) Math.round(j * height));
        }

    }

}