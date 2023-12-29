package gui.panel;

import entity.Category;
import gui.listener.CategoryListener;
import gui.model.CategoryTableModel;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class CategoryPanel extends WorkingPanel{

    public static CategoryPanel instance = new CategoryPanel();

    public JButton bnew = new JButton("新增");
    public JButton bedit = new JButton("编辑");
    public JButton bdelete = new JButton("删除");

    public CategoryTableModel ctm = new CategoryTableModel();
    public JTable t = new JTable(ctm);

    private CategoryPanel(){
        GUIUtil.setColor(ColorUtil.blueColor, bnew, bdelete, bedit);
        this.setLayout(new BorderLayout());
        this.add(center(t), BorderLayout.CENTER);
        this.add(south(), BorderLayout.SOUTH);

        addListener();

    }

    public JPanel south(){
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(bnew);
        p.add(bedit);
        p.add(bdelete);

        return p;
    }

    public JScrollPane center(JTable t){
        JScrollPane p = new JScrollPane(t);
        return p;
    }

    public Category getSelectedCategory() {
        int index = t.getSelectedRow();
        return ctm.cs.get(index);
    }

    public void updateData() {
        ctm.cs = new CategoryService().list();
        t.updateUI();
        t.getSelectionModel().setSelectionInterval(0, 0);

        if(0==ctm.cs.size()){
            bedit.setEnabled(false);
            bdelete.setEnabled(false);
        }
        else{
            bedit.setEnabled(true);
            bdelete.setEnabled(true);
        }
    }

    @Override
    public void addListener() {
        CategoryListener categoryListener = new CategoryListener();
        bnew.addActionListener(categoryListener);
        bedit.addActionListener(categoryListener);
        bdelete.addActionListener(categoryListener);

    }

    public static void main(String[] args) {
        GUIUtil.showPanel(CategoryPanel.instance);
    }
}
