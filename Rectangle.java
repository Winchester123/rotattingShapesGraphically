package OOM_FINAL;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Winchester
 */
class RectanglePanel extends JPanel {

    static int flag = 0;
    double angle1 = Math.PI / 180;
    double angle2 = Math.PI / 180;
    Random r = new Random();

    double x1 = 100, y1 = 100;
    double x2 = 200, y2 = 100;
    double x3 = 100, y3 = 200;
    double x4 = 200, y4 = 200;

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(new Color(230, 230, 230));
        g2d.fillRect(0, 0, getWidth(), getHeight());   //Background Rectangle of panel

        g2d.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        g2d.setColor(new Color(0, 113, 197));

        /*g2d.setColor(new Color(229, 229, 229));
         g2d.fillRect(0, 0, getWidth(), getHeight());

         g2d.setColor(new Color(51, 105, 232));
         */
        g2d.draw(new Line2D.Double(x1, y1, x2, y2));
        g2d.draw(new Line2D.Double(x2, y2, x4, y4));
        g2d.draw(new Line2D.Double(x3, y3, x1, y1));
        g2d.draw(new Line2D.Double(x4, y4, x3, y3));

        if (flag == 1) {

            double xx = -50 * Math.cos(angle1) + 100;
            double yy = 25 * Math.sin(angle1) + 100;

            x1 = xx;
            y1 = yy;

            xx = 50 * Math.cos(angle2) + 100;
            yy = 25 * Math.sin(angle2) + 100;

            x2 = xx;
            y2 = yy;

            xx = -50 * Math.cos(angle2) + 100;
            yy = 25 * Math.sin(angle2) + 200;

            x3 = xx;
            y3 = yy;

            xx = 50 * Math.cos(angle1) + 100;
            yy = 25 * Math.sin(angle1) + 200;

            x4 = xx;
            y4 = yy;

            angle1 += (2 * Math.PI / 180) % (2 * Math.PI);
            angle2 -= (2 * Math.PI / 180) % (2 * Math.PI);
            g2d.dispose();
            
        } else if (flag == 0) {
            
            double xx = -50 * Math.cos(angle1) + 100;
            double yy = -25 * Math.sin(angle1) + 100;

            x1 = xx;
            y1 = yy;

            xx = 50 * Math.cos(angle2) + 100;
            yy = -25 * Math.sin(angle2) + 100;

            x2 = xx;
            y2 = yy;

            xx = -50 * Math.cos(angle2) + 100;
            yy = -25 * Math.sin(angle2) + 200;

            x3 = xx;
            y3 = yy;

            xx = 50 * Math.cos(angle1) + 100;
            yy = -25 * Math.sin(angle1) + 200;

            x4 = xx;
            y4 = yy;

            angle1 += (2 * Math.PI / 180) % (2 * Math.PI);
            angle2 -= (2 * Math.PI / 180) % (2 * Math.PI);
            g2d.dispose();
        }
    }
}

public class Rectangle {

    static Timer t;

    public Rectangle() {
        JFrame f = new JFrame();

        JPanel panel = new RectanglePanel();
        JPanel p = new JPanel(new BorderLayout());
        JPanel p1 = new JPanel(new GridLayout(3, 1));

        f.setLocationRelativeTo(null);
        f.add(panel);

        JButton halt = new JButton("Halt Rotation");
        JButton clockwise = new JButton("Rotate Clockwise");
        JButton anticlockwise = new JButton("Rotate AntiClockwise");

        clockwise.setBackground(new Color(0, 113, 197));
        clockwise.setForeground(new Color(255, 255, 255));
        clockwise.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));

        anticlockwise.setBackground(new Color(0, 113, 197));
        anticlockwise.setForeground(new Color(255, 255, 255));
        anticlockwise.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));

        halt.setBackground(new Color(0, 113, 197));
        halt.setForeground(new Color(255, 255, 255));
        halt.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));

        p.setBackground(Color.WHITE);

        //p.add(begin);
        p1.add(anticlockwise);
        p1.add(clockwise);
        p1.add(halt);

        p.add(p1);

        f.add(p, BorderLayout.WEST);
        f.setSize(550, 300);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);

        Action c = new Action();
        //begin.addActionListener(c);
        halt.addActionListener(c);
        clockwise.addActionListener(c);
        anticlockwise.addActionListener(c);
        
        t = new Timer(10, (ActionEvent a) -> {
            panel.repaint();
        });
    }

    private static class Action extends RectanglePanel implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if ("Rotate Clockwise".equals(e.getActionCommand())) {
                super.flag = 1;
                t.setInitialDelay(100);
                t.setRepeats(true);
                t.start();
            } else if ("Halt Rotation".equals(e.getActionCommand())) {
                t.stop();
            } else if ("Rotate AntiClockwise".equals(e.getActionCommand())) {
                super.flag = 0;
                t.setInitialDelay(100);
                t.setRepeats(true);
                t.start();
            }
        }
    }

    public static void main(String[] args) {
        new Rectangle();
    }

}
