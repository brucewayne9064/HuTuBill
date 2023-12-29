package service;

import java.util.Collections;
import java.util.List;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Category;
import entity.Record;

public class CategoryService {

    CategoryDAO categoryDao = new CategoryDAO();
    RecordDAO recordDao = new RecordDAO();

    public List<Category> list() {
        List<Category> cs = categoryDao.list();  //查询出所有的category
        for (Category c : cs) {   //对于每一个category
            List<Record> rs =recordDao.list(c.id);  //根据category对应的cid，查出这个category的所有记录
            c.recordNumber=rs.size();  // 统计每个category的条目数
        }
        Collections.sort(cs,(c1,c2)->c2.recordNumber-c1.recordNumber);  //对结果进行排序，从大到小

        return cs;
    }

    public void add(String name) {
        Category c = new Category();
        c.setName(name);
        categoryDao.add(c);
    }


    //根据id更新分类的名称
    public void update(int id, String name) {
        Category c = new Category();
        c.setName(name);
        c.setId(id);
        categoryDao.update(c);
    }

    //删除分类条目
    public void delete(int id) {
        categoryDao.delete(id);
    }

}