package OOM_FINAL;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Hardik Dosi
 */
class SquarePanel extends JPanel {

    static int flag = 0;
    double angle = 2 * Math.PI / 500;
    double x1 = 100, y1 = 80;
    double x2 = 200, y2 = 80;
    double x3 = 200, y3 = 180;
    double x4 = 100, y4 = 180;

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(new Color(230, 230, 230));
        g2d.fillRect(0, 0, getWidth(), getHeight());   //Background Rectangle of panel
        g2d.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setColor(new Color(0, 113, 197));

        if (flag == 1) {
            double xx = ((x1 - 150) * Math.cos(angle) - (y1 - 130) * Math.sin(angle)) + 150;
            double yy = ((y1 - 130) * Math.cos(angle) + (x1 - 150) * Math.sin(angle)) + 130;

            x1 = xx;
            y1 = yy;

            xx = ((x2 - 150) * Math.cos(angle) - (y2 - 130) * Math.sin(angle)) + 150;
            yy = ((y2 - 130) * Math.cos(angle) + (x2 - 150) * Math.sin(angle)) + 130;

            x2 = xx;
            y2 = yy;

            xx = ((x3 - 150) * Math.cos(angle) - (y3 - 130) * Math.sin(angle)) + 150;
            yy = ((y3 - 130) * Math.cos(angle) + (x3 - 150) * Math.sin(angle)) + 130;

            x3 = xx;
            y3 = yy;

            xx = ((x4 - 150) * Math.cos(angle) - (y4 - 130) * Math.sin(angle)) + 150;
            yy = ((y4 - 130) * Math.cos(angle) + (x4 - 150) * Math.sin(angle)) + 130;

            x4 = xx;
            y4 = yy;

            g2d.draw(new Line2D.Double(x1, y1, x2, y2));
            g2d.draw(new Line2D.Double(x2, y2, x3, y3));
            g2d.draw(new Line2D.Double(x3, y3, x4, y4));
            g2d.draw(new Line2D.Double(x4, y4, x1, y1));

            g2d.dispose();

        } else if (flag == 0) {

            double xx = ((x1 - 150) * Math.cos(angle) + (y1 - 130) * Math.sin(angle)) + 150;
            double yy = ((y1 - 130) * Math.cos(angle) - (x1 - 150) * Math.sin(angle)) + 130;

            x1 = xx;
            y1 = yy;

            xx = ((x2 - 150) * Math.cos(angle) + (y2 - 130) * Math.sin(angle)) + 150;
            yy = ((y2 - 130) * Math.cos(angle) - (x2 - 150) * Math.sin(angle)) + 130;

            x2 = xx;
            y2 = yy;

            xx = ((x3 - 150) * Math.cos(angle) + (y3 - 130) * Math.sin(angle)) + 150;
            yy = ((y3 - 130) * Math.cos(angle) - (x3 - 150) * Math.sin(angle)) + 130;

            x3 = xx;
            y3 = yy;

            xx = ((x4 - 150) * Math.cos(angle) + (y4 - 130) * Math.sin(angle)) + 150;
            yy = ((y4 - 130) * Math.cos(angle) - (x4 - 150) * Math.sin(angle)) + 130;

            x4 = xx;
            y4 = yy;

            g2d.draw(new Line2D.Double(x1, y1, x2, y2));
            g2d.draw(new Line2D.Double(x2, y2, x3, y3));
            g2d.draw(new Line2D.Double(x3, y3, x4, y4));
            g2d.draw(new Line2D.Double(x4, y4, x1, y1));

            g2d.dispose();
        }
    }
}

public class Sqaure {

    static Timer t;

    public Sqaure() {
        JFrame f = new JFrame();

        JPanel panel = new SquarePanel();
        JPanel p = new JPanel(new BorderLayout());
        JPanel p1 = new JPanel(new GridLayout(3, 1));

        f.setLocationRelativeTo(null);
        f.add(panel);
        f.setResizable(false);

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

        f.add(p1, BorderLayout.WEST);
        f.setSize(550, 300);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);

        Action c = new Action();
        halt.addActionListener(c);
        clockwise.addActionListener(c);
        anticlockwise.addActionListener(c);

        t = new Timer(10, (ActionEvent a) -> {
            panel.repaint();
        });
    }

    private static class Action extends SquarePanel implements ActionListener {

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
        new Sqaure();
    }

}
