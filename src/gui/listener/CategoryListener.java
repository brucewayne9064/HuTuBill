package gui.listener;

import entity.Category;
import gui.panel.CategoryPanel;
import service.CategoryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        CategoryPanel p = CategoryPanel.instance;
        if(b == p.bnew){
            String name = JOptionPane.showInputDialog(null);
            if (0 == name.length()) {
                JOptionPane.showMessageDialog(p, "分类名称不能为空");
                return;
            }

            new CategoryService().add(name);
        }
        if(b == p.bedit){
            Category c = p.getSelectedCategory();
            String name = JOptionPane.showInputDialog("修改分类名称", c.name);
            if (0 == name.length()) {
                JOptionPane.showMessageDialog(p, "分类名称不能为空");
                return;
            }
            new CategoryService().update(c.id, name);
        }
        if(b == p.bdelete){
            Category c = p.getSelectedCategory();
            if (0 != c.recordNumber) {
                JOptionPane.showMessageDialog(p, "本分类下有消费记录存在，不能删除");
                return;
            }
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "确认要删除？"))
                return;

            new CategoryService().delete(c.id);
        }

        p.updateData(); //这个是每点击一次按钮，面板内容就更新，和切换状态栏的更新不一样
    }
}
