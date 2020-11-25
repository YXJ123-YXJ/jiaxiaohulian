package com.yxj.jiaxiaohulian.util;

import java.util.Random;

public class SaltUtils {

    //生成随机盐的静态方法
    public static String getSalt(int n){ //随机生成n位随机盐
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char achar = chars[new Random().nextInt(chars.length)];
            sb.append(achar);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getSalt(5));
    }
}
