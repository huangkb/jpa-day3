package com.hkb.dao;

import com.hkb.entity.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * JpaRepository<操作的实体类类型,实体类中主键属性的类型>
 * *封装了基本CRUD
 * JpaSpecificationExecutor<操作的实体类类型>
 * *封装了复杂查询
 */
public interface LinkManDao extends JpaRepository<LinkMan, Long>, JpaSpecificationExecutor<LinkMan> {

}

