package com.baidu.utils;

import com.baidu.entity.AddressResolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: liangbo
 * @date: 2020/8/19 13:54
 */
public class AddressResolutionUtil {

    /**
     * 解析地址
     *
     * @param address
     * @return
     * @author lin
     */
    public static AddressResolution addressResolution(String address) {
        String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m = Pattern.compile(regex).matcher(address);
        String province = null, city = null, county = null, town = null, village = null;
        List<Map<String, String>> table = new ArrayList<Map<String, String>>();
        Map<String, String> row = null;
        AddressResolution addressResolution = new AddressResolution();

        while (m.find()) {
            province = m.group("province");
            addressResolution.setProvince(province == null ? "" : province.trim());
            city = m.group("city");
            addressResolution.setCity(city == null ? "" : city.trim());
            county = m.group("county");
            addressResolution.setCounty(county == null ? "" : county.trim());
            town = m.group("town");
            addressResolution.setTown(town == null ? "" : town.trim());
            village = m.group("village");
            addressResolution.setVillage(village == null ? "" : village.trim());
        }
        return addressResolution;
    }

}
