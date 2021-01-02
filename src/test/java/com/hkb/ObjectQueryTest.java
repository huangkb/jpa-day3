package com.hkb;

import com.hkb.dao.CustomerDao;
import com.hkb.dao.LinkManDao;
import com.hkb.entity.Customer;
import com.hkb.entity.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)//声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器配置信息
public class ObjectQueryTest {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    LinkManDao linkManDao;

    /**
     * 对象导航查询:一对多-->>默认使用延迟加载
     */
    @Test
    @Transactional
    public void testFind(){
        Customer customer = customerDao.findOne(15L);
        Set<LinkMan> linkMans = customer.getLinkMans();
        linkMans.forEach(System.out::println);
    }

    /**
     * 对象导航查询:多对一-->>默认使用立即加载
     */
    @Test
    @Transactional
    public void testFind2(){
        LinkMan linkMan = linkManDao.findOne(16L);
        Customer customer = linkMan.getCustomer();
        System.out.println(customer);
    }
}
