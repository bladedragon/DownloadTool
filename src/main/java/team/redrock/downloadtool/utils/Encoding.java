package team.redrock.downloadtool.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;

public class Encoding {
    public static String getMD5(String str) {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(str.getBytes());

            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            System.out.println("MD5加密出现错误");
            return "";
        }
    }
//
//    public static void main(String[] args) throws UnsupportedEncodingException {
//
//        String data = "https://wx.idsbllp.cn/game/%E5%90%8E%E7%AB%AF%E6%9C%9F%E6%9C%AB%E8%80%83%E6%A0%B8.pdf\b^Z0]";
//        System.out.println(data.getBytes("UTF-8"));
//
//    }
}
