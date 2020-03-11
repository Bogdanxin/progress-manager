package com.softlab.progressmanager.core.mapper;

import com.softlab.progressmanager.core.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gwx
 * @version 1.0
 * @program UserMapper
 * @description 对user的数据库操作
 * @date 2020/3/11 14:45
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 删除指定id的用户
     * @param userId
     * @return
     */
    public int deleteUserById(int userId);

    /**
     * 添加一个用户，用于注册
     * @param user
     * @return
     */
    public int insertUser(User user);

    /**
     * 修改指定id的用户信息，不建议使用这个，修改不清晰
     * @param userId
     * @param user
     * @return
     */
    public int updateUserById(@Param("userId") int userId,
                              @Param("user") User user);

    /**
     * 查找指定id的用户信息
     * @param userId
     * @return
     */
    public User selectUserById(int userId);

    /**
     * 根据不同条件查找用户信息
     * @param user
     * @return
     */
    public List<User> selectUsersByCondition(User user);

    /**
     * 将用户的token置空，用于退出登录
     * @param userId
     * @return
     */
    public int updateTokenNullById(int userId);

    /**
     * 查找指定teacherId和password的用户，用于登录
     * @param password
     * @param teacherId
     * @return
     */
    public User selectUserByPasswordAndId(@Param("password") String password,
                                          @Param("teacherId") int teacherId);

    /**
     * 根据token查找用户，一般情况都是只有一个，
     * 不过要以防万一，就用到list
     * @param token
     * @return
     */
    public User selectUserByToken(String token);
}
