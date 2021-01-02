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

@RunWith(SpringJUnit4ClassRunner.class)//声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器配置信息
public class OneToManyTest {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    LinkManDao linkManDao;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testAdd(){
        Customer customer = new Customer();
        customer.setCustName("百度");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李");
        LinkMan linkMan2 = new LinkMan();
        linkMan.setLkmName("小丁");
        LinkMan linkMan3 = new LinkMan();
        linkMan.setLkmName("小黄");

        //配置关系
        linkMan.setCustomer(customer);//多对一
        linkMan2.setCustomer(customer);//多对一
        linkMan3.setCustomer(customer);//多对一
        customer.getLinkMans().add(linkMan);
        customer.getLinkMans().add(linkMan2);
        customer.getLinkMans().add(linkMan3);


        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    /**
     * 级联操作
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testAdd2(){
        Customer customer = new Customer();
        customer.setCustName("百度");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李");

        //配置关系
        linkMan.setCustomer(customer);//多对一
        customer.getLinkMans().add(linkMan);//一对多 --多一条update

        customerDao.save(customer);
    }

    /**
     * 级联操作
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testRemove(){
        Customer one = customerDao.findOne(1L);
        customerDao.delete(one);
    }

}
