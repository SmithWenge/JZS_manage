package com.jzs.arc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeviceNumUtils {
    public static String creatDeviceNumber(int placeNum,String track,int regionNum,String seat) {
        StringBuilder deviceNumber = new StringBuilder("D");
        deviceNumber.append(Integer.toString(placeNum));
        String regEx="[^0-9]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(track);
        String trackTwo = matcher.replaceAll("").trim();
        if(trackTwo.length() == 1){
            deviceNumber.append("0");
        }
        deviceNumber.append(trackTwo);
        if(regionNum == 1 ){
            deviceNumber.append("A");
        }else if (regionNum == 2) {
            deviceNumber.append("B");
        }else if (regionNum == 3) {
            deviceNumber.append("C");
        }else {
            return null;
        }
        deviceNumber.append(seat);
        return deviceNumber.toString();
    }
}
