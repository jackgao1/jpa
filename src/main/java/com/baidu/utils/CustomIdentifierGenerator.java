package com.baidu.utils;


import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomIdentifierGenerator implements IdentifierGenerator {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static String lastDate = "";
    private static Integer addNum = 1;

    @Override
    public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {
        String result = "";
        String date = sdf.format(new Date());
        if (lastDate.equals(date)) {
            ++addNum;
        } else {
            lastDate = date;
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMinimumIntegerDigits(4);
        result = date + nf.format(addNum);
        return result;
    }
}
