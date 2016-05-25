package OOM_FINAL;

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


    public static void main(String[] args) {
        new Circle();
    }


    public Circle() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                JFrame f = new JFrame();


                JPanel panel = new TestPane();
                JPanel p = new JPanel(new BorderLayout());
                JPanel p1 = new JPanel(new GridLayout(3, 1));


                f.setLocationRelativeTo(null);
                f.add(panel);
                JFrame.setDefaultLookAndFeelDecorated(true);
                JButton halt = new JButton("Halt Rotation");
                JButton clockwise = new JButton("Rotate Clockwise");
                JButton anticlockwise = new JButton("Rotate AntiClockwise");


                clockwise.setBackground(new Color(0, 113, 197));
                clockwise.setForeground(new Color(255, 255, 255));
                clockwise.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));


                anticlockwise.setBackground(new Color(0, 113, 197));
                anticlockwise.setForeground(new Color(255, 255, 255));
                anticlockwise.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));


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
                f.setSize(550, 330);
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setVisible(true);


                Action c = new Action();
                halt.addActionListener(c);
                clockwise.addActionListener(c);
                anticlockwise.addActionListener(c);
                /*JFrame frame = new JFrame("Testing");
                 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                 frame.add(new TestPane());
                 JPanel p = new JPanel(new FlowLayout());
                 JButton begin = new JButton("Begin Rotation");
                 JButton halt = new JButton("Halt Rotation");
                 Action h = new Action();
                 begin.addActionListener(h);
                 halt.addActionListener(h);
                 p.add(begin);
                 p.add(halt);
                 p.setBackground(Color.WHITE);
                 frame.add(p, BorderLayout.SOUTH);
                 frame.pack();
                 frame.setLocationRelativeTo(null);
                 frame.setSize(400,400);
                 frame.setVisible(true);*/
            }
        });
    }


    private static class Action implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            if ("Rotate Clockwise".equals(e.getActionCommand())) {
                flag = 1;
                t.start();
            } else if ("Halt Rotation".equals(e.getActionCommand())) {
                t.stop();
            } else if ("Rotate AntiClockwise".equals(e.getActionCommand())) {
                flag = -1;
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
            //t.start();
            setBackground(new Color(255, 255, 255));
        }


        @Override
        public Dimension getPreferredSize() {
            return new Dimension(300, 300);
        }


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();


            int diameter = Math.min(getWidth(), getHeight());
            int x = (getWidth() - diameter) / 2;
            int y = (getHeight() - diameter) / 2;


            g2d.setColor(new Color(255, 255, 255));
            g2d.drawOval(x, y, diameter, diameter);


            g2d.setColor(new Color(102, 0, 102));
            float innerDiameter = 40;


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
    /*public static void main(String[] args){
     RotateWheel r = new RotateWheel();
     }*/


}