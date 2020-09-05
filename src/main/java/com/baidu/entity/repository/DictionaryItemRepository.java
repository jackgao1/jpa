package com.baidu.entity.repository;

import com.baidu.entity.DictionaryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DictionaryItemRepository extends JpaRepository<DictionaryItem, String>, JpaSpecificationExecutor<DictionaryItem> {
}
