package com.example.order.util;

import java.util.Random;

public class KeyUtil {

    /**
     * 生成唯一的主键，测试用
     * 格式: 时间+随机数
     * @return
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();

        /** 生成六位随机数 */
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
