package com.hkb;

import com.hkb.dao.CustomerDao;
import com.hkb.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)//声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器配置信息
public class CustomerDaoTest {

    //动态代理（生成基于接口的实现类对象）
    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindOne() {
        Customer one = customerDao.findOne(2L);
        System.out.println(one);
    }

    /**
     * 保存：id属性为空或者id对应数据不存在
     * 更新：id不为空切数据存在
     */
    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setCustId(22L);
        customer.setCustName("huangkb22");
        customer.setCustPhone("120");
        Customer save = customerDao.save(customer);
        System.out.println(save);
        System.out.println("===============");
        System.out.println(customerDao.findOne(21L));
    }

    @Test
    public void testDelete() {
        customerDao.delete(21L);
        System.out.println("===============");
        System.out.println(customerDao.findOne(21L));
    }

    @Test
    public void testFindAll() {
        List<Customer> all = customerDao.findAll();
        all.forEach(System.out::println);
    }
    @Test
    public void testCount() {
        long count = customerDao.count();
        System.out.println(count);
    }
    @Test
    public void testExists() {
        boolean exists = customerDao.exists(5L);
        System.out.println(exists);
    }

    /**
     * findOne():
     *      em.find
     * getOne():
     *      em.getReference
     */
    @Test
    @Transactional
    public void testGetone() {
        Customer one = customerDao.getOne(5L);
        System.out.println(one);
    }



}
