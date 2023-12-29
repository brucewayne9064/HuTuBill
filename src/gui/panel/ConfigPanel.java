package gui.panel;

import gui.listener.ConfigListener;
import service.ConfigService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends WorkingPanel {
    public static ConfigPanel instance = new ConfigPanel();

    int gap = 40;

    JLabel lBudget = new JLabel("本月预算(￥)");
    public JTextField tfBudget = new JTextField("0");

    JLabel lMysql = new JLabel("Mysql安装目录");
    public JTextField tfMysqlPath = new JTextField("");

    JButton bSubmit = new JButton("更新");


    private ConfigPanel(){
        this.setLayout(new BorderLayout());
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);
        GUIUtil.setColor(ColorUtil.grayColor, lBudget,lMysql);
        this.add(north(), BorderLayout.NORTH);
        this.add(center(), BorderLayout.CENTER);

        addListener();
    }

    @Override
    public void updateData() {
        String budget = new ConfigService().get(ConfigService.budget);
        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
        tfBudget.setText(budget);
        tfMysqlPath.setText(mysqlPath);
        tfBudget.grabFocus();

    }

    public void addListener(){
        ConfigListener configListener = new ConfigListener();
        bSubmit.addActionListener(configListener);
    }

    private JPanel north() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4, 1,gap,gap));
        p.add(lBudget);
        p.add(tfBudget);
        p.add(lMysql);
        p.add(tfMysqlPath);
        return p;
    }

    private JPanel center(){
        JPanel p = new JPanel();
        p.add(bSubmit);
        return p;
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ConfigPanel.instance);
    }

}
