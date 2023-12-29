package util;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GUIUtil {
    private static String imageFolder = "img";

    //校验输入框内容是否为空
    public static boolean checkEmpty(JTextField tf, String input) {
        String text = tf.getText().trim();
        if(text.isEmpty()){
            JOptionPane.showMessageDialog(null, input + "不能为空");
            tf.grabFocus();
            return false;  //为空
        }
        return true; //不为空
    }

    //校验是否是数字
    public static boolean checkNumber(JTextField tf, String input){
        if(!checkEmpty(tf,input)) return false;
        String text = tf.getText().trim();
        try{
            Integer.parseInt(text);
            return true;  //是数字
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, input + "需要是整数");
            tf.grabFocus();
            return false; //不是数字
        }
    }

    //校验是否是0
    public static boolean checkZero(JTextField tf, String input){
        if(checkNumber(tf,input)){
            String text = tf.getText().trim();
            if(0 == Integer.parseInt(text)){
                JOptionPane.showMessageDialog(null, input + "不能为0");
                tf.grabFocus();
                return false; //为0
            }
        }
        return true; // 不为0
    }

    //给多个组件设置前景色
    public static void setColor(Color color, JComponent ... cs){
        for(JComponent c : cs){
            c.setForeground(color);
        }
    }

    public static void setImageIcon(JButton b, String fileName, String tip){
        ImageIcon i = new ImageIcon(new File(imageFolder, fileName).getAbsolutePath());
        b.setIcon(i);
        b.setPreferredSize(new Dimension(61, 81));
        b.setToolTipText(tip);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setText(tip);
    }

    public static void useLNF(){
        try{
            javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static int getInt(JTextField tf){
        return Integer.parseInt(tf.getText().trim());
    }

    public static void showPanel(JPanel p,double stretch){
        // GUIUtil.useLNF();
        JFrame f = new JFrame();
        f.setSize(500,500);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(stretch);
        f.setContentPane(cp);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cp.show(p);


    }

    public static void showPanel(JPanel p){
        showPanel(p, 0.85);
    }

    public static void main(String[] args) {
        GUIUtil.useLNF();
        JPanel p = new JPanel();
        p.add(new JButton("按钮1"));
        p.add(new JButton("按钮2"));
        GUIUtil.showPanel(p);
    }


}
