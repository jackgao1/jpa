package com.baidu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils{

    /**
     * 驼峰转换为下划线格式
     */
    public static String HumpToline(String hump) {
        if (hump == null || "".equals(hump)) {
            return "";
        }
        return hump.substring(0,1).toLowerCase()+ hump.substring(1).replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    /**
     * 下划线转换为驼峰
     */
    public static String LineToHump(String line) {
        if (line == null || "".equals(line)) {
            return "";
        }
        Matcher matcher = Pattern.compile("(_[a-z]{1})").matcher(line);
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String replacement = matcher.group(1);
            matcher.appendReplacement(result, replacement.replace("_", "").toUpperCase());
        }
        matcher.appendTail(result);
        return result.toString();
    }

    public static String LineToHumpForClassName(String className){
        if(className==null||"".equals(className)){
            return "";
        }
        if (className.length()==1){
            return className.toUpperCase();
        }
        return className.substring(0,1 ).toUpperCase()+LineToHump(className.substring(1));
    };

    public static String selectNoEmpty(String ...strs){
        String indexStr=(strs[0]==null)?"":strs[0];
        for (int i = 1; i < strs.length; i++) {
            if(indexStr==null||"".equals(indexStr)){
                indexStr=strs[i];
            }
        }

        return indexStr;
    }
}
