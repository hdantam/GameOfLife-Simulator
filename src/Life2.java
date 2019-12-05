import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

/**
 * Life2
 *
 * @author Hemant Dantam
 * @version (2019 - 05)
 * <p>
 * Main game of life class, cells follow a certain pattern, imitating Conway's Game of Life
 * Learned through following tutorials and examples, improving my own Java knowledge along the way.
 * <p>
 * Sources used:
 * Coding Train: https://www.youtube.com/watch?v=FWSR_7kZuYg&t=1664s
 * Jason Galbraith: https://www.youtube.com/watch?v=amiDMYRgYbU
 */


public class Life2 implements MouseListener, ActionListener, Runnable {

    boolean[][] arr = new boolean[25][25];
    JFrame frame = new JFrame("Life2 sim");
    LifePanel panel = new LifePanel(arr);
    Container north = new Container();
    JButton start = new JButton("Start");
    JButton stop = new JButton("Stop");
    boolean running = false;

    public Life2() {

        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        panel.addMouseListener(this);
        panel.setBackground(Color.WHITE);
        north.setLayout(new GridLayout(1, 3));
        north.add(start);
        start.addActionListener(this);
        north.add(stop);
        stop.addActionListener(this);
        frame.add(north, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public static void main(String[] args) {

        new Life2();

    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(start)) {
            if (running == false) {
                running = true;
                Thread t = new Thread(this);
                t.start();

            }

        }
        if (event.getSource().equals(stop)) {
            running = false;

        }
    }

    public void run() {
        while (running == true) {
            step();
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void step() {
        boolean[][] nextArr = new boolean[arr.length][arr[0].length];
        for (int row = 0; row < arr.length; row++) {
            for (int column = 0; column < arr[0].length; column++) {
                int n = 0;
                if (row > 0 && column > 0 && arr[row - 1][column - 1] == true)
                    n++;

                if (row > 0 && arr[row - 1][column] == true)
                    n++;

                if (row > 0 && column < arr[0].length - 1 && arr[row - 1][column + 1] == true)
                    n++;

                if (column > 0 && arr[row][column - 1] == true)
                    n++;

                if (column < arr[0].length - 1 && arr[row][column + 1] == true)
                    n++;

                if (row < arr.length - 1 && column > 0 && arr[row + 1][column - 1] == true)
                    n++;

                if (row < arr.length - 1 && arr[row + 1][column] == true)
                    n++;

                if (row < arr.length - 1 && column < arr[0].length - 1 && arr[row + 1][column + 1] == true)
                    n++;

                if (arr[row][column] == true) {
                    if (n == 2 || n == 3) {
                        nextArr[row][column] = true;
                    } else {
                        nextArr[row][column] = false;
                    }
                } else {
                    if (n == 3) {
                        nextArr[row][column] = true;
                    } else {
                        nextArr[row][column] = false;
                    }

                }
            }
        }
        arr = nextArr;
        panel.setArr(nextArr);
        frame.repaint();
    }

    public void mouseClicked(MouseEvent event) {

    }

    public void mouseEntered(MouseEvent event) {

    }

    public void mouseExited(MouseEvent event) {

    }

    public void mousePressed(MouseEvent event) {

    }

    public void mouseReleased(MouseEvent event) {
        double width = (double) panel.getWidth() / arr[0].length;
        double height = (double) panel.getHeight() / arr.length;
        int column = Math.min(arr[0].length - 1, (int) (event.getX() / width));
        int row = Math.min(arr.length - 1, (int) (event.getY() / height));
        //System.out.println(column + "," + row);
        arr[row][column] = !arr[row][column];
        frame.repaint();

    }

}