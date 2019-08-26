package com.robin.demo.common;

public class MosaicUtils {
    {

        public static String mosaicName(String fullName){
        if (StringUtils.isBlank(fullName)) {
            return "";
        }
        String name = StringUtils.right(fullName, 1);
        return StringUtils.leftPad(name, fullName.length(), "*");
    }

        public static String mosaicCard(String str){

        String ragex = "(?<=\\d{6})\\d(?=\\d{4})";
        return str.replaceAll(ragex, "*");
    }

        public static String mosaicPhone(String str){
        String ragex = "(?<=[\\d]{3})\\d(?=[\\d]{4})";
        return str.replaceAll(ragex, "*");
    }

        public static String mosaicId(String str){
        String hideStr = "";
        int len = str.length();
        if (len <= 4) {// 小于3位直接返回
            return str;
        }
        String last4Str = StringUtils.substring(str, len - 4);// 最后4位
        String remainStr = StringUtils.substring(str, 0, len - 4);// 去除最后4位的剩余部分
        int remainLen = remainStr.length();// 剩余部分的长度
        if (remainLen <= 6) {// 剩余部分的长度小于4位，直接把剩余部分用*替换
            return str;
        } else {
            String middleStr = StringUtils.substring(remainStr, 6, remainLen);
            for (int i = 0; i < middleStr.length(); i++) {
                hideStr += "*";
            }
            hideStr = StringUtils.substring(remainStr, 0, 6) + hideStr + last4Str;
        }
        return hideStr;
    }

        public static void main(String[] args) {

        System.out.println(MosaicUtils.mosaicPhone("13357218367"));
        System.out.println(MosaicUtils.mosaicName("哈哈哈"));
        System.out.println(MosaicUtils.mosaicCard("6225887317221303"));
        System.out.println(MosaicUtils.mosaicId("123456fsfsd7891"));
    }

    }
}
