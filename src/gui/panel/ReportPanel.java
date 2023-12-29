package gui.panel;

import service.ReportService;
import entity.Record;
import util.ChartUtil;
import util.GUIUtil;
import java.util.List;

import javax.swing.*;
import java.awt.*;

public class ReportPanel extends WorkingPanel {
    public static ReportPanel instance = new ReportPanel();

    public JLabel l = new JLabel();

    private ReportPanel(){
        this.setLayout(new BorderLayout());
        List<Record> rs = new ReportService().listThisMonthRecords();
        Image i = ChartUtil.getImage(rs,350,250);
        ImageIcon icon = new ImageIcon(i);
        l.setIcon(icon);
        this.add(l);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ReportPanel.instance);
    }

    @Override
    public void updateData() {
        List<Record> rs = new ReportService().listThisMonthRecords();
        Image i = ChartUtil.getImage(rs, 350, 250);
        ImageIcon icon = new ImageIcon(i);
        l.setIcon(icon);
    }

    @Override
    public void addListener() {

    }
}
