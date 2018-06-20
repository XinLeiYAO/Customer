package com.example.asus.customer.commons.utils;

/**
 * Created by AAA on 2017/12/19.
 */

public class CheckPasswordUtil {

    public static String[] pwdArr = {
            "000000",
            "123456",
            "111111",
            "222222",
            "333333",
            "444444",
            "555555",
            "666666",
            "777777",
            "888888",
            "999999",
            "234567",
            "345678",
            "456789"
    };

    public static boolean checkPassword(String pwd) {
        boolean flag = false;
        for (int i = 0; i < pwdArr.length; i++) {
            if (pwdArr[i].equals(pwd)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
