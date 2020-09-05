package com.baidu.entity.repository;

import com.baidu.entity.RkJbxx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RkJbxxRepository extends JpaRepository<RkJbxx, String>, JpaSpecificationExecutor<RkJbxx> { }
