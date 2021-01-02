package com.hkb.dao;

import com.hkb.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * JpaRepository<操作的实体类类型,实体类中主键属性的类型>
 * *封装了基本CRUD
 * JpaSpecificationExecutor<操作的实体类类型>
 * *封装了复杂查询
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    @Query(value = "from Customer where custName = ?")
    Customer findCustomerByName(String custName);

    @Query(value = "from Customer where custId=?2 and custName = ?1")
    Customer findCustomerByNameAndId(String custName, Long id);

    @Query(value = "update Customer set custPhone=? where custId=?")
    @Modifying
    void updateCustomerById(String phone, Long id);

    @Query(value = "select * from cst_customer where cust_name like ?", nativeQuery = true)
    List<Object[]> findBySql(String name);

    /**
     * 方法名称规则查询:
     * findBy + 属性 + 查询方式 + 连接符(and|or) + 属性 + 查询方式;
     */
    Customer findByCustName(String name);

    List<Customer> findByCustNameLike(String name);

    List<Customer> findByCustNameLikeAndCustPhone(String name, String phone);

}

