package OOM_FINAL;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

class EllipsePanel extends JPanel {

    static Timer t, g;

    private static final double ELLIPSE_W = 100;
    private static final double ELLIPSE_H = 200;
    private static final int PREF_W = 300;
    private static final int PREF_H = 250;
    private static final Stroke STROKE = new BasicStroke(5f);
    private static final Color ELLIPSE_COLOR = new Color(0, 113, 197);
    private static final Color BACKGROUND = new Color(230, 230, 230);
    private static final double ELLIPSE_X = PREF_W / 2 - ELLIPSE_W / 2;
    private static final double ELLIPSE_Y = PREF_H / 2 - ELLIPSE_H / 2;
    private static final int TIMER_DELAY = 10;
    private static final double DELTA_THETA = Math.toRadians(0.8f);//
    static int flag = 0;

    private Ellipse2D ellipse2D;
    private AffineTransform transform = new AffineTransform();
    private double theta = 0;

    public EllipsePanel() {
        ellipse2D = new Ellipse2D.Double(ELLIPSE_X, ELLIPSE_Y, ELLIPSE_W, ELLIPSE_H);
        setBackground(BACKGROUND);
        new Timer(TIMER_DELAY, new TimerListener()).start();
    }

    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(STROKE);
        g2.setColor(ELLIPSE_COLOR);
        g2.setTransform(transform);
        g2.draw(ellipse2D);
        g2.dispose();
    }

    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            theta += DELTA_THETA;
            if (flag == 1) {
                transform = AffineTransform.getRotateInstance(theta, ELLIPSE_X + ELLIPSE_W / 2, ELLIPSE_Y + ELLIPSE_H / 2);
            } else if (flag == -1) {
                transform = AffineTransform.getRotateInstance(-theta, ELLIPSE_X + ELLIPSE_W / 2, ELLIPSE_Y + ELLIPSE_H / 2);
            } else if (flag == 2) {
                t.stop();
            }
            repaint();
        }
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("RotateEllipse");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Adding South Panel containing Buttons
        JPanel p = new JPanel(new GridLayout(3, 1));

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

        p.add(anticlockwise);
        p.add(clockwise);
        p.add(halt);

        EllipsePanel panel = new EllipsePanel();

        Action c = new Action();
        clockwise.addActionListener(c);
        anticlockwise.addActionListener(c);
        halt.addActionListener(c);

        t = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                panel.repaint();
            }
        });

        p.setBackground(Color.WHITE);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(p, BorderLayout.WEST);

        frame.pack();
        frame.setSize(550, 300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static class Action extends EllipsePanel implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if ("Rotate Clockwise".equals(e.getActionCommand())) {
                flag = 1;
            } else if ("Rotate AntiClockwise".equals(e.getActionCommand())) {
                flag = -1;
            } else if ("Halt Rotation".equals(e.getActionCommand())) {
                flag = 2;
            }
        }
    }
}

@SuppressWarnings("serial")
public class Ellipse {
    /*public static void main(String[] args) {
     SwingUtilities.invokeLater(new Runnable() {
     public void run() {
     EllipsePanel.createAndShowGUI();
     }
     });
     }*/
}
