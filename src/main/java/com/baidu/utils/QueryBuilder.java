package com.baidu.utils;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.util.*;

public class QueryBuilder implements Specification {
    private Class entityClass;
    protected Map<String, String> conditionMap;

    public QueryBuilder() {
    }

    public QueryBuilder(Map<String, String> conditionMap) {
        this(null, conditionMap);
    }

    public QueryBuilder(String key, String value) {
        conditionMap = new HashMap<>();
        conditionMap.put(key, value);
    }

    public QueryBuilder(Class entityClass, Map<String, String> conditionMap) {
        this.entityClass = entityClass;
        this.conditionMap = conditionMap;
    }
    @Override
    public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder cb) {
        if (root == null) {
            root = criteriaQuery.from(entityClass);
        }

        if (conditionMap == null || conditionMap.size() == 0) {
            return null;
        }
        Predicate predicate = getPredicateByParams(root, cb, conditionMap);
        criteriaQuery.where(predicate);
        return predicate;
    }
    // 解析查询参数与params
    public Predicate getPredicateByParams(Root root, CriteriaBuilder cb, Map<String, String> condition) {
        Predicate predicate = cb.conjunction();
        Iterator<Map.Entry<String, String>> iter = condition.entrySet().iterator();
        Map.Entry<String, String> entry = null;
        while (iter.hasNext()) {
            entry = iter.next();
            String key = entry.getKey();
            if (key.indexOf("$") > -1) {   //用于处理出现一样key查询查询情况，以$进行区分
                key = key.substring(0, key.indexOf("$"));
            }
            String value = (String) entry.getValue();
            if (value == null || value.trim().equals("")) {
                continue;
            }
            //等于（带通配符）
            if (key.startsWith("eq_")) {
                if (value.indexOf("*") != -1) {
                    value = value.replaceAll("[*]", "%");
                    predicate = cb.and(predicate, cb.like(root.get(key.substring(3)), value));
                } else {
                    predicate = cb.and(predicate, cb.equal(root.get(key.substring(3)), value));
                }
            }
            //不等于
            else if (key.startsWith("noteq_")) {
                predicate = cb.and(predicate, cb.notEqual(root.get(key.substring(6)), value));
            }
            //不等于（带通配符）
            else if (key.startsWith("ne_")) {
                if (value.indexOf("*") != -1) {
                    value = value.replaceAll("[*]", "%");
                    predicate = cb.and(predicate, cb.notLike(root.get(key.substring(3)), value));
                } else {
                    predicate = cb.and(predicate, cb.notEqual(root.get(key.substring(3)), value));
                }
            }
            // 等于或者
            else if (key.startsWith("eqor_")) {
                String[] strs = key.substring(5).split("_");
                String[] values = value.split("_");
                Predicate pred = cb.equal(root.get(strs[0]), values[0]);
                if (values.length == 1) {
                    for (int k = 1; k < strs.length; k++) {
                        pred = cb.or(pred, cb.equal(root.get(strs[k]), value));
                    }
                } else {
                    for (int k = 1; k < strs.length; k++) {
                        pred = cb.or(pred, cb.equal(root.get(strs[k]), values[k]));
                    }
                }
                predicate = cb.and(predicate, pred);
            }
            //in查询
            else if (key.startsWith("in_")) {
                List list = new ArrayList();
                for (Object o : value.split(",")) {
                    list.add(o);
                }
                predicate = cb.and(predicate, root.get(key.substring(3)).in(list));
            }
            // not in 查询
            else if (key.startsWith("ni_")) {
                List list = new ArrayList();
                for (Object o : value.split(",")) {
                    list.add(o);
                }
                predicate = cb.and(predicate, cb.not(root.get(key.substring(3)).in(list)));
            }
            //in or 查询
            else if (key.startsWith("inor_")) {
                String[] strs = key.substring(5).split("_");
                String[] values = value.split("_");
                Predicate pred = root.get(strs[0]).in(values[0].split(","));
                for (int k = 1; k < strs.length; k++) {
                    if (values.length == 1) {
                        pred = cb.or(pred, root.get(strs[k]).in(Arrays.asList(values[0].split(","))));
                    } else {
                        pred = cb.or(pred, root.get(strs[k]).in(Arrays.asList(values[k].split(","))));

                    }
                }
                predicate = cb.and(predicate, pred);
            }
            //like查询
            else if (key.startsWith("lk_")) {
                predicate = cb.and(predicate, cb.like(root.get(key.substring(3)), "%" + value + "%"));
            }
            // like or 查询
            else if (key.startsWith("lkor_")) {
                String[] strs = key.substring(5).split("_");
                String[] values = value.split("_");
                Predicate pred = cb.like(root.get(strs[0]), "%" + values[0] + "%");
                if (values.length == 1) {
                    for (int k = 1; k < strs.length; k++) {
                        pred = cb.or(pred, cb.like(root.get(strs[k]), "%" + value + "%"));
                    }
                } else {
                    for (int k = 1; k < strs.length; k++) {
                        pred = cb.or(pred, cb.like(root.get(strs[k]), "%" + values[k] + "%"));
                    }
                }
                predicate = cb.and(predicate, pred);
            }
            // like in 查询
            else if (key.startsWith("lkin_")) {
                String[] strs = value.split(",");
                Predicate pred = cb.like(root.get(key.substring(5)), "%" + strs[0] + "%");
                for (int k = 1; k < strs.length; k++) {
                    pred = cb.or(pred, cb.like(root.get(key.substring(5)), "%" + strs[k] + "%"));
                }
                predicate = cb.and(predicate, pred);
            }
            // like in or 查询
            else if (key.startsWith("lkinor_")) {
                String[] strs = key.substring(7).split("_");
                String[] valuesArray = value.split("_");
                String[] values = value.split(",");
                Predicate pred = cb.like(root.get(strs[0]), "%" + values[0] + "%");
                if (valuesArray.length == 1) {
                    for (int s = 0; s < values.length; s++) {
                        for (int k = 0; k < strs.length; k++) {
                            if (k == 0 && s == 0) {
                                continue;
                            }
                            pred = cb.or(pred, cb.like(root.get(strs[k]), "%" + values[s] + "%"));
                        }
                    }
                } else {
                    for (int k = 0; k < strs.length; k++) {
                        String[] valuesTemp = valuesArray[k].split("_");
                        for (int s = 0; s < valuesTemp.length; s++) {
                            if (k == 0 && s == 0) {
                                continue;
                            }
                            pred = cb.or(pred, cb.like(root.get(strs[k]), "%" + valuesTemp[s] + "%"));
                        }
                    }
                }


                predicate = cb.and(predicate, pred);
            }
            // start like 查询
            else if (key.startsWith("sl_")) {
                predicate = cb.and(predicate, cb.like(root.get(key.substring(3)), value + "%"));
            }
            // start like or 查询
            else if (key.startsWith("slor_")) {
                String[] strs = key.substring(5).split("_");
                Predicate pred = cb.like(root.get(strs[0]), "" + value + "%");
                for (int k = 1; k < strs.length; k++) {
                    pred = cb.or(pred, cb.like(root.get(strs[k]), "" + value + "%"));
                }
                predicate = cb.and(predicate, pred);
            }
            //start like in 查询
            else if (key.startsWith("slin_")) {
                String[] strs = value.split(",");
                Predicate pred = cb.like(root.get(key.substring(5)), strs[0] + "%");
                for (int k = 1; k < strs.length; k++) {
                    pred = cb.or(pred, cb.like(root.get(key.substring(5)), strs[k] + "%"));
                }
                predicate = cb.and(predicate, pred);
            }
            // start like in or 查询
            else if (key.startsWith("slinor_")) {
                String[] strs = key.substring(7).split("_");
                String[] valuesArray = value.split("_");
                String[] values = value.split(",");
                Predicate pred = cb.like(root.get(strs[0]), "" + values[0] + "%");
                if (valuesArray.length == 1) {
                    for (int s = 0; s < values.length; s++) {
                        for (int k = 0; k < strs.length; k++) {
                            if (k == 0 && s == 0) {
                                continue;
                            }
                            pred = cb.or(pred, cb.like(root.get(strs[k]), "" + values[s] + "%"));
                        }
                    }
                } else {
                    for (int k = 0; k < strs.length; k++) {
                        String[] valuesTemp = valuesArray[k].split("_");
                        for (int s = 0; s < valuesTemp.length; s++) {
                            if (k == 0 && s == 0) {
                                continue;
                            }
                            pred = cb.or(pred, cb.like(root.get(strs[k]), "" + valuesTemp[s] + "%"));
                        }
                    }
                }
                predicate = cb.and(predicate, pred);
            }
            // end like 查询
            else if (key.startsWith("el_")) {
                predicate = cb.and(predicate, cb.like(root.get(key.substring(3)), "%" + value));
            }
            // end like or 查询
            else if (key.startsWith("elor_")) {
                String[] strs = key.substring(5).split("_");
                Predicate pred = cb.like(root.get(strs[0]), "%" + value + "");
                for (int k = 1; k < strs.length; k++) {
                    pred = cb.or(pred, cb.like(root.get(strs[k]), "%" + value + ""));
                }
                predicate = cb.and(predicate, pred);
            }
            //end like in 查询
            else if (key.startsWith("elin_")) {
                String[] strs = value.split(",");
                Predicate pred = cb.like(root.get(key.substring(5)), "%" + strs[0]);
                for (int k = 1; k < strs.length; k++) {
                    pred = cb.or(pred, cb.like(root.get(key.substring(5)), "%" + strs[k]));
                }
                predicate = cb.and(predicate, pred);
            }
            // end like in or 查询
            else if (key.startsWith("elinor_")) {
                String[] strs = key.substring(7).split("_");
                String[] valuesArray = value.split("_");
                String[] values = value.split(",");
                Predicate pred = cb.like(root.get(strs[0]), "%" + values[0] + "");
                if (valuesArray.length == 1) {
                    for (int s = 0; s < values.length; s++) {
                        for (int k = 0; k < strs.length; k++) {
                            if (k == 0 && s == 0) {
                                continue;
                            }
                            pred = cb.or(pred, cb.like(root.get(strs[k]), "%" + values[s] + ""));
                        }
                    }
                } else {
                    for (int k = 0; k < strs.length; k++) {
                        String[] valuesTemp = valuesArray[k].split("_");
                        for (int s = 0; s < valuesTemp.length; s++) {
                            if (k == 0 && s == 0) {
                                continue;
                            }
                            pred = cb.or(pred, cb.like(root.get(strs[k]), "%" + valuesTemp[s] + ""));
                        }
                    }
                }
                predicate = cb.and(predicate, pred);
            }
            //日期大于等于查询
            else if (key.startsWith("gedate_")) {
                try {
                    predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get(key.substring(7)), DateUtils.parseDate(value)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            //日期大于查询
            else if (key.startsWith("gtdate_")) {
                try {
                    predicate = cb.and(predicate, cb.greaterThan(root.get(key.substring(7)), DateUtils.parseDate(value)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            //日期小于等于查询
            else if (key.startsWith("ledate_")) {
                try {
                    predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get(key.substring(7)), DateUtils.parseDate(value)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            //日期小于查询
            else if (key.startsWith("ltdate_")) {
                try {
                    predicate = cb.and(predicate, cb.lessThan(root.get(key.substring(7)), DateUtils.parseDate(value)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            //日期范围查询（包含边界）
            else if (key.startsWith("geledate_")) {
                try {
                    String startTime;
                    String endTime = null;
                    String[] times = value.split(",");
                    System.out.println(times.length);
                    if (times.length == 1) {
                        startTime = times[0];
                    } else {
                        startTime = times[0];
                        endTime = times[1];
                    }
                    if (!"".equals(startTime) && startTime != null) {
                        predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get(key.substring(9)), DateUtils.parseDate(startTime)));
                    }
                    if (!"".equals(endTime) && endTime != null) {
                        predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get(key.substring(9)), DateUtils.parseDate(endTime)));
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            // 大于等于查询
            else if (key.startsWith("ge_")) {
                predicate = cb.and(predicate, cb.ge(root.get(key.substring(3)), Double.valueOf(value)));
            }
            //大于查询
            else if (key.startsWith("gt_")) {
                predicate = cb.and(predicate, cb.gt(root.get(key.substring(3)), Double.valueOf(value)));
            }
            //小于等于查询
            else if (key.startsWith("le_")) {
                predicate = cb.and(predicate, cb.le(root.get(key.substring(3)), Double.valueOf(value)));
            }
            //小于查询
            else if (key.startsWith("lt_")) {
                predicate = cb.and(predicate, cb.lt(root.get(key.substring(3)), Double.valueOf(value)));
            }
            //大于等于且小于等于查询
            else if (key.startsWith("gele_")) {
                String[] values = value.split(",");
                String startValue = null;
                String endValue = null;
                if (values.length == 1) {
                    startValue = values[0];
                } else if (values.length == 2) {
                    startValue = values[0];
                    endValue = values[1];
                }
                if (StringUtils.isNotEmpty(startValue)) {
                    predicate = cb.and(predicate, cb.ge(root.get(key.substring(5)), Double.valueOf(values[0])));
                }
                if (StringUtils.isNotEmpty(endValue)) {
                    predicate = cb.and(predicate, cb.le(root.get(key.substring(5)), Double.valueOf(values[1])));
                }
            }
            //大于且小于查询
            else if (key.startsWith("gtlt_")) {
                String[] values = value.split(",");
                String startValue = null;
                String endValue = null;
                if (values.length == 1) {
                    startValue = values[0];
                } else if (values.length == 2) {
                    startValue = values[0];
                    endValue = values[1];
                }
                if (StringUtils.isNotEmpty(startValue)) {
                    predicate = cb.and(predicate, cb.gt(root.get(key.substring(5)), Double.valueOf(values[0])));
                }
                if (StringUtils.isNotEmpty(endValue)) {
                    predicate = cb.and(predicate, cb.lt(root.get(key.substring(5)), Double.valueOf(values[1])));
                }
            }


            // 字符串大于等于查询
            else if (key.startsWith("gestr_")) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get(key.substring(6)), value));
            }
            // 字符串大于查询
            else if (key.startsWith("gtstr_")) {
                predicate = cb.and(predicate, cb.greaterThan(root.get(key.substring(6)), value));
            }
            // 字符串小于等于查询
            else if (key.startsWith("lestr_")) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get(key.substring(6)), value));
            }
            // 字符串小于查询
            else if (key.startsWith("ltstr_")) {
                predicate = cb.and(predicate, cb.lessThan(root.get(key.substring(6)), value));
            }
            // 字符串大于等于且小于等于查询
            else if (key.startsWith("gelestr_")) {
                String[] values = value.split(",");
                String startValue = null;
                String endValue = null;
                if (values.length == 1) {
                    startValue = values[0];
                } else if (values.length == 2) {
                    startValue = values[0];
                    endValue = values[1];
                }
                if (StringUtils.isNotEmpty(startValue)) {
                    predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get(key.substring(8)), values[0]));
                }
                if (StringUtils.isNotEmpty(endValue)) {
                    predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get(key.substring(8)), values[1]));
                }
            }
            // 字符串大于且小于查询
            else if (key.startsWith("gtltstr_")) {
                String[] values = value.split(",");
                String startValue = null;
                String endValue = null;
                if (values.length == 1) {
                    startValue = values[0];
                } else if (values.length == 2) {
                    startValue = values[0];
                    endValue = values[1];
                }
                if (StringUtils.isNotEmpty(startValue)) {
                    predicate = cb.and(predicate, cb.greaterThan(root.get(key.substring(3)), values[0]));
                }
                if (StringUtils.isNotEmpty(endValue)) {
                    predicate = cb.and(predicate, cb.lessThan(root.get(key.substring(3)), Double.valueOf(values[1])));
                }
            }


            // 空查询
            else if (key.startsWith("null_")) {
                predicate = cb.and(predicate, cb.isNull(root.get(key.substring(5))));
            }
            // 不为空查询
            else if (key.startsWith("notnull_")) {
                predicate = cb.and(predicate, cb.isNotNull(root.get(key.substring(8))));
            }
            //不为空字符查询
            else if (key.startsWith("notempty_")) {
                predicate = cb.and(predicate, cb.isNotEmpty(root.get(key.substring(9))));
            }
        }
        return predicate;
    }
}
