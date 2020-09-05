package com.baidu.entity.repository;

import com.baidu.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DictionaryRepository extends JpaRepository<Dictionary, String>, JpaSpecificationExecutor<Dictionary> {

    Boolean existsByCode(String code);
}
