package util;

import javax.swing.*;
import java.awt.Component;
import java.awt.Dimension;

import gui.panel.WorkingPanel;

public class CenterPanel extends JPanel {
    private double rate;//拉伸比例
    private JComponent c; //显示的组件
    private boolean stretch; //是否拉伸

    public CenterPanel(double rate,boolean stretch){
        this.setLayout(null);
        this.rate = rate;
        this.stretch = stretch;
    }

    public CenterPanel(double rate) {
        this(rate,true);
    }

    public void show(JComponent p){
        this.c = p;
        Component[] cs = getComponents();
        for(Component c : cs){
            remove(c);
        }
        add(this.c);

        if (p instanceof WorkingPanel)  //那几个panel继承了workingPanel，也就是需要重写updateData的那几个panel
            ((WorkingPanel) p).updateData();

        this.updateUI();
    }

    public void repaint(){
        if (null != c) {
            Dimension containerSize = this.getSize();
            Dimension componentSize= c.getPreferredSize();

            if(stretch)
                c.setSize((int) (containerSize.width * rate), (int) (containerSize.height * rate));
            else
                c.setSize(componentSize);

            c.setLocation(containerSize.width / 2 - c.getSize().width / 2, containerSize.height / 2 - c.getSize().height / 2);
        }
        super.repaint();
    }



    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(200, 200);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(0.85,false);
        f.setContentPane(cp);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton b = new JButton("按键");
        cp.show(b);
    }

}
