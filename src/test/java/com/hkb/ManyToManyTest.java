package com.hkb;

import com.hkb.dao.RoleDao;
import com.hkb.dao.UserDao;
import com.hkb.entity.Role;
import com.hkb.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)//声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器配置信息
public class ManyToManyTest {

    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testAdd() {
        User user = new User();
        user.setUserName("huangkb");
        user.setUserAge(20);

        Role role = new Role();
        role.setRoleName("老大");

        user.getRoles().add(role);

        userDao.save(user);
        roleDao.save(role);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testCasCadeAdd() {
        User user = new User();
        user.setUserName("huangkb");
        user.setUserAge(20);

        Role role = new Role();
        role.setRoleName("老大");

        user.getRoles().add(role);
        role.getUsers().add(user);
        userDao.save(user);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testCasCadeRemove() {
        User one = userDao.findOne(3L);
        userDao.delete(one);
    }


}
