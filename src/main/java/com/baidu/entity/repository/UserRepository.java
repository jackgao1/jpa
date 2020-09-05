package com.baidu.entity.repository;

import com.baidu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by qiang on 2018/1/22.
 */

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    @Transactional
    @Modifying
    @Query(value = "update User set name=?1 where id=?2", nativeQuery = true)
    User updateUser(String name, String id);

    @Transactional
    @Modifying
    @Query(value = "update User sc set sc.height = :height where sc.id in :id", nativeQuery = true)
    User deleteByIds(@Param(value = "id") List<String> id, @Param(value = "height") String height);

    @Query(value = "select * from User b where b.name like %:name% and b.id in :id", nativeQuery = true)
    List<User> findByNameMatch(@Param("name") String name,@Param(value = "id") List<String> id);

    @Query(value = "select t.*,tt.*,ttt.* from tb_content t,tb_content_category tt,tb_item_param ttt", nativeQuery = true)
    List<Map<String,Object>> allTable();

}
