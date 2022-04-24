package service.impl;

import domain.User;
import org.junit.Test;

public class IUserviceImplTest {

    @Test
    public void queryUserAccount() {
        User user = new User();
        user.setUserPhone("15179793797");
        System.out.println(new IUserviceImpl().queryUserAccount(user));
    }
}