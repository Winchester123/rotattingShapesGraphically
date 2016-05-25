package OOM_FINAL;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.geom.RoundRectangle2D;

public class OpeningMainWindow extends JFrame implements ActionListener{

    JPanel p = new JPanel(new GridLayout(3, 1, 5, 5));
    JPanel p1 = new JPanel(new GridLayout(1, 1, 5, 5));
    JPanel p2 = new JPanel(new GridLayout(1, 1, 5, 5));
    JPanel p5 = new JPanel(new GridLayout(3, 3));

    public OpeningMainWindow() {
        setSize(600, 550);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(true); //transparent
        setBackground(new Color(10, 20, 30, 40)); //transparent
        setOpacity(0.9f);
        
        p.setBackground(new Color(248, 248, 255));
        p2.setBackground(new Color(248, 248, 255));
        p5.setBackground(new Color(248, 248, 255));
        
        setUndecorated(true);
        setVisible(true);
        setOpacity(1);

 // Normal rounded rectangle
        setShape(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),20,40));
 
        JLabel top = new JLabel("Choose a Dimension.");
        top.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        
        p5.add(new JLabel(""));
        p5.add(new JLabel(""));
        p5.add(new JLabel(""));
        p5.add(new JLabel(""));
        p5.add(top);
        p5.add(new JLabel(""));
        p5.add(new JLabel(""));
        p5.add(new JLabel(""));
       
        
        JButton b1 = new JButton("2D");
        JButton b2 = new JButton("3D");
        
        b1.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        b1.setBackground(new Color(51, 105, 232));
        b1.setForeground(new Color(229, 229, 229));
        
        b2.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        b2.setBackground(new Color(213, 15, 37));
        b2.setForeground(new Color(229, 229, 229));
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        p1.add(b1);
        p2.add(b2);

        p.add(p5);
        p.add(p1, BorderLayout.CENTER);
        p.add(p2, BorderLayout.SOUTH);
        
        add(p);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand() == "2D"){
            Choice2D c = new Choice2D();
        }
        else if(ae.getActionCommand() == "3D"){
            Choice3D c = new Choice3D();
        }
    }
    public static void main(String[] args) {
        OpeningMainWindow openingMainWindow= new OpeningMainWindow();
    }
}
