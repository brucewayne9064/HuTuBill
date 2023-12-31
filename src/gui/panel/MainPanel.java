package gui.panel;

import gui.listener.ToolBarListener;
import util.CenterPanel;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel{
    public static MainPanel instance = new MainPanel();
    public JButton bSpend = new JButton();
    public JButton bRecord = new JButton();
    public JButton bCategory = new JButton();
    public JButton bReport = new JButton();
    public JButton bConfig = new JButton();
    public JButton bBackup = new JButton();
    public JButton bRecover = new JButton();

    public JToolBar tb = new JToolBar();
    public CenterPanel workingPanel;

    private MainPanel(){

        GUIUtil.setImageIcon(bSpend, "home.png", "消费一览");
        GUIUtil.setImageIcon(bRecord, "record.png", "记一笔");
        GUIUtil.setImageIcon(bCategory, "category2.png", "消费分类");
        GUIUtil.setImageIcon(bReport, "report.png", "月消费报表");
        GUIUtil.setImageIcon(bConfig, "config.png", "设置");
        GUIUtil.setImageIcon(bBackup, "backup.png", "备份");
        GUIUtil.setImageIcon(bRecover, "restore.png", "恢复");

        tb.add(bSpend);
        tb.add(bRecord);
        tb.add(bCategory);
        tb.add(bReport);
        tb.add(bConfig);
        tb.add(bBackup);
        tb.add(bRecover);
        tb.setFloatable(false);  //禁止toolbar移动

        workingPanel = new CenterPanel(0.8);

        this.setLayout(new BorderLayout());
        this.add(tb, BorderLayout.NORTH);
        this.add(workingPanel, BorderLayout.CENTER);

        addListener();

    }

    private void addListener(){
        ToolBarListener toolBarListener = new ToolBarListener();
        bBackup.addActionListener(toolBarListener);
        bCategory.addActionListener(toolBarListener);
        bConfig.addActionListener(toolBarListener);
        bRecord.addActionListener(toolBarListener);
        bReport.addActionListener(toolBarListener);
        bRecover.addActionListener(toolBarListener);
        bSpend.addActionListener(toolBarListener);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(MainPanel.instance,1);
    }
}
