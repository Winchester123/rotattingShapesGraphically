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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Hardik Dosi
 */
class TrianglePanel extends JPanel {

    static int flag = 0;
    double angle = Math.PI / 500;
    double x1 = 150, y1 = 50;
    double x2 = 100, y2 = 150;
    double x3 = 200, y3 = 150;

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(new Color(230, 230, 230));
        g2d.fillRect(0, 0, getWidth(), getHeight());
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

            g2d.draw(new Line2D.Double(x1, y1, x2, y2));
            g2d.draw(new Line2D.Double(x2, y2, x3, y3));
            g2d.draw(new Line2D.Double(x3, y3, x1, y1));

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

            g2d.draw(new Line2D.Double(x1, y1, x2, y2));
            g2d.draw(new Line2D.Double(x2, y2, x3, y3));
            g2d.draw(new Line2D.Double(x3, y3, x1, y1));

            g2d.dispose();
        }
    }
}

public class Triangle {

    static Timer t;

    public Triangle() {
        JFrame f = new JFrame();

        JPanel panel = new TrianglePanel();
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

        f.add(p, BorderLayout.WEST);
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

    private static class Action extends TrianglePanel implements ActionListener {

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
        new Triangle();
    }
}
