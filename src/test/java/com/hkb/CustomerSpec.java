package com.hkb;

import com.hkb.dao.CustomerDao;
import com.hkb.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)//声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器配置信息
public class CustomerSpec {
    //动态代理（生成基于接口的实现类对象）
    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFind() {
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1.获取比较的属性
                Path<Object> custName = root.get("custName");
                //2.构造查询条件
                Predicate predicate = cb.equal(custName, "huangkb010");
                return predicate;
            }
        };
        Customer one = customerDao.findOne(spec);
        System.out.println(one);
    }

    @Test
    public void testFind2() {
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1.获取比较的属性
                Path<Object> custName = root.get("custName");
                Path<Object> custPhone = root.get("custPhone");
                //2.构造查询条件
                Predicate p1 = cb.equal(custName, "huangkb010");
                Predicate p2 = cb.equal(custPhone, "15527619163");
                //3.条件组合
                Predicate predicate = cb.and(p1, p2);
                return predicate;
            }
        };
        Customer one = customerDao.findOne(spec);
        System.out.println(one);
    }

    @Test
    public void testFind3() {
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1.获取比较的属性
                Path<Object> custName = root.get("custName");
                //2.构造查询条件
                Predicate predicate = cb.like(custName.as(String.class), "huangkb01%");
                return predicate;
            }
        };

        //List<Customer> all = customerDao.findAll(spec);
        Sort sort = new Sort(Sort.Direction.DESC, "custId");
        List<Customer> all = customerDao.findAll(spec,sort);
        all.forEach(System.out::println);
    }

    @Test
    public void testFind4() {
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1.获取比较的属性
                Path<Object> custName = root.get("custName");
                //2.构造查询条件
                Predicate predicate = cb.like(custName.as(String.class), "huangkb0%");
                return predicate;
            }
        };

        Pageable pageable = new PageRequest(0,2);

        Page<Customer> page = customerDao.findAll(spec,pageable);
        System.out.println(page.getContent());
        System.out.println(page.getTotalElements());//总条数
        System.out.println(page.getTotalPages());//总页数
    }



}
