package utils.regex;

import domain.User;

public class AccountCheck {
    private static String EMAIL_REGEX = "^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$";
    /**
     * 长度为11的手机号
     */
    private static String PHONE_REGEX = "^[1]([3-9])[0-9]{9}$";
    /**
     * 至少一个大写字母或一个小写字母和一个数字
     */
    private static String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,12}$";
    public static Boolean checkEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static Boolean checkPhone(String phone) {
        return phone.matches(PHONE_REGEX);
    }

    public static Boolean checkPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

}
