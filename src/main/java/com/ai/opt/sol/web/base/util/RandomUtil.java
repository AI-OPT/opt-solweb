package com.ai.opt.sol.web.base.util;

import java.util.Random;

public class RandomUtil {

    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取待测试方法参数的SESSION KEY
     * 
     * @return
     * @author zhangchao
     */
    public static String getParameterSessionKey() {
        String pKey = "P" + getRandomString(25);
        return pKey;
    }

    /**
     * 获取待测试方法的SESSION KEY
     * @return
     * @author zhangchao
     */
    public static String getMethodSessionKey() {
        String mKey = "M" + getRandomString(25);
        return mKey;
    }
}
