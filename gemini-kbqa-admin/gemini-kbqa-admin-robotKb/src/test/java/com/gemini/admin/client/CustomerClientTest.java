package com.gemini.admin.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: XXY
 * @Date: 2021/3/4 7:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerClientTest {

    @Autowired
    private CustomerKbClient customerKbClient;

    @Test
    public void test1(){
        customerKbClient.getWantAskQuestions();
    }
}
