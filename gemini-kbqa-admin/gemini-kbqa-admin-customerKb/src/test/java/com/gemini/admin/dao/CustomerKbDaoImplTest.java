package com.gemini.admin.dao;

import com.gemini.admin.entity.KbCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: XXY
 * @Date: 2020/12/17 9:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerKbDaoImplTest {
    @Autowired
    private CustomerKbDaoImpl customerKbDao;

    @Test
    public void testQueryCategorySuccess(){
        List<KbCategory> kbCategories = customerKbDao.selectKbCategoryList(0);
    }
}
