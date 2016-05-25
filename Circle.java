package OOM_FINAL;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Circle {

    static Timer t;
    static int flag;

    public Circle() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame fr = new JFrame();

                JPanel panel = new TestPane();
                JPanel p1 = new JPanel(new GridLayout(3, 1));

                fr.setResizable(false);
                fr.setLocationRelativeTo(null);

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

                p1.add(anticlockwise);
                p1.add(clockwise);
                p1.add(halt);

                fr.add(p1, BorderLayout.WEST);
                fr.add(panel, BorderLayout.CENTER);

                fr.setSize(550, 330);
                fr.setLocationRelativeTo(null);
                fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                fr.setVisible(true);

                Action c = new Action();
                
                halt.addActionListener(c);
                clockwise.addActionListener(c);
                anticlockwise.addActionListener(c);
            }
        });
    }

    private static class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if ("Rotate Clockwise".equals(e.getActionCommand())) {
                flag = 1;
                t.setInitialDelay(100);
                t.setRepeats(true);
                t.start();
            } else if ("Halt Rotation".equals(e.getActionCommand())) {
                t.stop();
            } else if ("Rotate AntiClockwise".equals(e.getActionCommand())) {
                flag = -1;
                t.setInitialDelay(100);
                t.setRepeats(true);
                t.start();
            }
        }
    }

    public class TestPane extends JPanel {

        private float degrees = 0;

        public TestPane() {

            t = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    degrees += 1.0f;
                    repaint();
                }
            });
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(300, 300);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setColor(new Color(230, 230, 230));
            g2d.fillRect(0, 0, getWidth(), getHeight());

            int diameter = Math.min(getWidth(), getHeight());
            int x = (getWidth() - diameter) / 2;
            int y = (getHeight() - diameter) / 2;

            g2d.setColor(new Color(255, 255, 255));
            g2d.setColor(new Color(0, 113, 197));
            g2d.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            float innerDiameter = 100;

            Point p = getPointOnCircle(degrees, (diameter / 2f) - (innerDiameter / 2));
            g2d.drawOval(x + p.x - (int) (innerDiameter / 2), y + p.y - (int) (innerDiameter / 2), (int) innerDiameter, (int) innerDiameter);

            g2d.dispose();
        }

        protected Point getPointOnCircle(float degress, float radius) {

            int x = Math.round(getWidth() / 2);
            int y = Math.round(getHeight() / 2);
            int xPosy, yPosy;
            double rads = Math.toRadians(degress - 90); // 0 becomes the top

            // Calculate the outter point of the line
            if (flag == 1) {
                xPosy = Math.round((float) (x + Math.cos(rads) * radius));
                yPosy = Math.round((float) (y + Math.sin(rads) * radius));//
            } else {
                xPosy = Math.round((float) (x + Math.cos(rads) * radius));
                yPosy = Math.round((float) (y - Math.sin(rads) * radius));//
            }
            return new Point(xPosy, yPosy);
        }

    }
    public static void main(String[] args) {
        new Circle();
    }
}
