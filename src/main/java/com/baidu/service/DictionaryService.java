package com.baidu.service;

import com.baidu.entity.Dictionary;
import com.baidu.entity.DictionaryItem;
import com.baidu.entity.repository.DictionaryItemRepository;
import com.baidu.entity.repository.DictionaryRepository;
import com.baidu.utils.TreeBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Service
public class DictionaryService {
    @Autowired
    private DictionaryRepository dictionaryRepository;
    @Autowired
    private DictionaryItemRepository dictionaryItemRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Page<Dictionary> search(String searchString, Pageable pageable) {
        return this.dictionaryRepository.findAll(new Specification<Dictionary>() {
            @Override
            public Predicate toPredicate(Root<Dictionary> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                if (StringUtils.isNotEmpty(searchString)) {
                    return cb.or(cb.like(root.get("name"), "%" + searchString + "%"), cb.like(root.get("code"), "%" + searchString + "%"));
                }
                return null;
            }
        }, pageable);
    }

    public Page<DictionaryItem> findSysTableField(String dictionaryId, String searchString, Pageable pageable) {
        Page result = dictionaryItemRepository.findAll(new Specification<DictionaryItem>() {
            @Override
            public Predicate toPredicate(Root<DictionaryItem> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Predicate predicate = cb.equal(root.get("dictionaryId"), dictionaryId);
                if (StringUtils.isNotEmpty(searchString)) {
                    return cb.and(cb.equal(root.get("dictionaryId"), dictionaryId), cb.or(cb.like(root.get("name"), "%" + searchString + "%"), cb.like(root.get("code"), "%" + searchString + "%")));
                }
                return cb.equal(root.get("dictionaryId"), dictionaryId);
            }
        }, pageable);
        return result;
    }

    public Dictionary save(Dictionary dictionary) throws Exception {
        if (dictionary.getCode() == null || "".equals(dictionary.getCode())) {
            throw new Exception("字典代码不允许为空");
        }
        if (dictionary.getName() == null || "".equals(dictionary.getName())) {
            throw new Exception("字典名称不允许为空");
        }
        if (dictionaryRepository.existsByCode(dictionary.getCode())) {
            throw new Exception("字典代码已经存在");
        }
        dictionaryRepository.save(dictionary);
        return dictionary;
    }

    public List<? extends TreeBuilder.Node> dictionaryTreeQuote(String entityName, String dicName, String idProperty, String pidProperty, String displayProperty, String dictionaryFilterScript) throws Exception {
        if (StringUtils.isEmpty(entityName)) {
            //同时兼容entityName 和 dicName 两个属性，作用相同，以entityName为主，都是指向字典唯一标识
            if (StringUtils.isEmpty(dicName)) {
                throw new Exception("entityName 和 dicName 不能同时为空");
            }
            entityName = dicName;
        }
        //展示驼峰转换，实际上应该实现查询sys_entity和sys_table的关联的属性字段
        final String entityName2 = com.baidu.utils.StringUtils.HumpToline(entityName);
        final String idProperty2 = com.baidu.utils.StringUtils.HumpToline(idProperty);
        final String pidProperty2 = com.baidu.utils.StringUtils.HumpToline(pidProperty);
        final String displayProperty2 = com.baidu.utils.StringUtils.HumpToline(displayProperty);
        final String dictionaryFilterScript2 = StringUtils.isEmpty(dictionaryFilterScript) ? "" : (" where " + com.baidu.utils.StringUtils.HumpToline(dictionaryFilterScript));
        List<String> displayPropertys = Arrays.asList(displayProperty2.split(","));
        System.out.println("SELECT " + idProperty2 + ", " + (StringUtils.isEmpty(pidProperty2) ? "" : (pidProperty2 + ", ")) + displayProperty2 + " FROM " + entityName2 + dictionaryFilterScript2);


        List<TreeNode> list = jdbcTemplate.query(
                "SELECT " + idProperty2 + ", " + (StringUtils.isEmpty(pidProperty2) ? "" : (pidProperty2 + ", ")) + displayProperty2 + " FROM " + entityName2 + dictionaryFilterScript2 + " a", new Object[]{},
                (rs, rowNum) -> {

                    TreeNode treeNode = new TreeNode(rs.getString(idProperty2), StringUtils.isEmpty(pidProperty2) ? null : rs.getString(pidProperty2), rs.getString(idProperty2), rs.getString(displayPropertys.get(0)));
                    displayPropertys.forEach(s -> {
                        try {
                            treeNode.put(s, rs.getString(s));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });
                    return treeNode;
                });
        TreeBuilder treeBuilder = new TreeBuilder();
        return treeBuilder.buildListToTree(list);
    }

    class TreeNode extends TreeBuilder.Node {
        private String value;
        private String label;

        public TreeNode(String id, String parentId, String value, String label) {
            super(id, parentId);
            this.value = value;
            this.label = label;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}
