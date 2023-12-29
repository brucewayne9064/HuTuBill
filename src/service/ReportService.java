package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.RecordDAO;
import entity.Record;
import util.DateUtil;

public class ReportService {

    /**
     * 获取某一天的消费金额
     * @param d
     * @param monthRawData
     * @return
     */
    public int getDaySpend(Date d,List<Record> monthRawData){
        int daySpend = 0;
        for (Record record : monthRawData) {
            if(record.date.equals(d))
                daySpend+=record.spend;
        }
        return daySpend;
    }

    /**
     * 获取一个月的消费记录集合
     * @return
     */
    public List<Record> listThisMonthRecords() {
        RecordDAO dao= new RecordDAO();
        List<Record> monthRawData= dao.listThisMonth();  //整个月的消费数据
        List<Record> result= new ArrayList<>();  //返回的结果
        Date monthBegin = DateUtil.monthBegin();  // 本月开始的时间
        int monthTotalDay = DateUtil.thisMonthTotalDay();  //本月的总天数
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < monthTotalDay; i++) {  //对于本月的每一天
            Record r = new Record();
            c.setTime(monthBegin); // 先设置成月初
            c.add(Calendar.DATE, i); // 然后增加i天
            Date eachDayOfThisMonth=c.getTime() ;  //得到本月第i天的date 的 millisecond时间
            int daySpend = getDaySpend(eachDayOfThisMonth,monthRawData);  //每一天的spend
            r.spend=daySpend;  //给r对象的spend属性赋值，然后加入result
            result.add(r);
        }
        return result;

    }

}