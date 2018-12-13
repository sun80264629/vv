package com.vv.demo;

import com.vv.demo.entity.JCarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JCarRepository extends JpaRepository<JCarEntity, String>, JpaSpecificationExecutor<JCarEntity> {

}
