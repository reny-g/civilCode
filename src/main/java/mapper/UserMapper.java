package mapper;

import domain.User;

/**
 * @author 吴仁杨
 */
public interface UserMapper {
    /**
     * 往数据表插入用户，返回影响行数
     * @param user user_phone和user_password只能有一个有值，另一个必须为null
     * @return 0：失败， 1：成功
     */
    int insertUser(User user);

    /**
     * 在数据表中根据提供的用户名或email和密码查询用户
     * @param user 返回中的user中有user_qq, user_weChat, user_nickname, user_phone, user_email
     * @return null：不存在匹配的用户，user不为null：查询成功
     */
    User queryUser(User user);

    /**
     * 根据user内容修改用户信息
     * @param user user内容（应在controller中控制要修改的信息）
     * @return 影响行数，1成功， 0失败
     */
    int updateUser(User user);

    /**
     * 根据传入对象查看是否存在对应的手机号或邮箱（对象中二者只有一个为null）
     * @param user 传入的用户对象
     * @return 是否存在
     */
    Boolean queryUserAccount(User user);
}
