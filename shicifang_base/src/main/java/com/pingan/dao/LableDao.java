package com.pingan.dao;

import com.pingan.pojo.Lable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LableDao extends JpaRepository<Lable,String>, JpaSpecificationExecutor<Lable> {

}
