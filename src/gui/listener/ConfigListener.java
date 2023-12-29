package gui.listener;

import gui.panel.ConfigPanel;
import service.ConfigService;
import util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ConfigListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ConfigPanel p = ConfigPanel.instance;
        if(!GUIUtil.checkNumber(p.tfBudget,"本月预算"))
            return;
        String mysqlpath = p.tfMysqlPath.getText();
        if(0 != mysqlpath.length()){
            File commendfile = new File(mysqlpath, "bin/mysql");
            //     /usr/local/mysql-8.2.0-macos13-arm64/bin/mysql
            if(!commendfile.exists()){
                JOptionPane.showMessageDialog(p, "Mysql路径不正确");
                p.tfMysqlPath.grabFocus();
                return;
            }
        }
        ConfigService cs = new ConfigService();
        cs.update(ConfigService.budget,p.tfBudget.getText());
        cs.update(ConfigService.mysqlPath,p.tfMysqlPath.getText());

        JOptionPane.showMessageDialog(p, "设置修改成功");

    }
}
