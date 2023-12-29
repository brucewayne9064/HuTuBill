package gui.panel;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

import entity.Category;
import gui.listener.RecordListener;
import gui.model.CategoryComboBoxModel;
import org.jdesktop.swingx.JXDatePicker;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

public class RecordPanel extends WorkingPanel {

    public JLabel lSpend = new JLabel("花费(￥)");
    public JLabel lCategory = new JLabel("分类");
    public JLabel lComment = new JLabel("备注");
    public JLabel lDate = new JLabel("日期");

    public JTextField tfSpend = new JTextField("0");

    public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
    public JComboBox<Category> cbCategory = new JComboBox<>(cbModel);
    public JTextField tfComment = new JTextField();
    public JXDatePicker datepick = new JXDatePicker(new Date());

    public JButton bSubmit = new JButton("记一笔");

    public static RecordPanel instance = new RecordPanel();

    public int gap = 40;

    private RecordPanel(){
        this.setLayout(new BorderLayout());
        GUIUtil.setColor(ColorUtil.grayColor, lSpend,lCategory,lComment,lDate);
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);
        this.add(north(), BorderLayout.NORTH);
        this.add(center(), BorderLayout.SOUTH);

        addListener();
    }

    private JPanel north(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4,2,gap,gap));

        p.add(lSpend);
        p.add(tfSpend);
        p.add(lCategory);
        p.add(cbCategory);
        p.add(lComment);
        p.add(tfComment);
        p.add(lDate);
        p.add(datepick);

        return p;
    }

    private JPanel center(){
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(bSubmit);
        return p;
    }


    public static void main(String[] args) {
        GUIUtil.showPanel(RecordPanel.instance);
    }

    @Override
    public void updateData() {
        cbModel.cs = new CategoryService().list();
        cbCategory.updateUI();
        resetInput();
        tfSpend.grabFocus();
    }

    private void resetInput() {
        tfSpend.setText("0");
        tfComment.setText("");
        if(0!=cbModel.cs.size())
            cbCategory.setSelectedIndex(0);
        datepick.setDate(new Date());
    }

    @Override
    public void addListener() {
        RecordListener listener = new RecordListener();
        bSubmit.addActionListener(listener);
    }

    public Category getSelectedCategory() {
        return (Category) cbCategory.getSelectedItem();
    }
}
