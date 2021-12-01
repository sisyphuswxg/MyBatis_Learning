package com.sisyphuswxg.book2.mapper;

import com.sisyphuswxg.book2.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    /**
     * 通过id查询用户
     *
     * @param id
     * @return
     */
    SysUser selectById(Long id);

    /**
     * 查询所有用户
     *
     * @return
     */
    List<SysUser> selectAll();

    /**
     * 根据用户id获取角色信息（只返回角色信息）
     *
     * @param userId
     * @return
     */
    List<SysRole> selectRolesByUserId(Long userId);

    /**
     * 根据用户id获取角色信息（返回角色信息+用户信息）
     *
     * @param userId
     * @return
     */
    List<SysRole> selectRolesByUserIdPro(Long userId);

    /**
     * 新增一个用户
     *
     * @param sysUser
     * @return
     */
    int insertOneUser(SysUser sysUser);

    /**
     * 新增一个用户并返回主键id值 - 方法一
     *
     * @param sysUser
     * @return
     */
    int insertOneUser2(SysUser sysUser);

    /**
     * 新增一个用户并返回主键id值 - 方法二
     *
     * @param sysUser
     * @return
     */
    int insertOneUser3(SysUser sysUser);

    /**
     * 根据主键id更新
     *
     * @param sysUser
     * @return
     */
    int updateById(SysUser sysUser);

    /**
     * 根据主键id删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 多个参数：不使用@Param
     *
     * @param userId
     * @param enabled
     * @return
     */
    List<SysRole> selectRolesByUserIdAndRoleEnabled01(Long userId, Integer enabled);

    /**
     * 多个参数：不使用@Param
     *
     * @param userId
     * @param enabled
     * @return
     */
    List<SysRole> selectRolesByUserIdAndRoleEnabled02(@Param("userId") Long userId,
                                                      @Param("enabled") Integer enabled);

    /**
     * 动态SQL： Select语句中的if标签
     *
     * @param user
     * @return
     */
    List<SysUser> selectByUser(SysUser user);


    /**
     * 动态SQL：Update语句中的if标签
     *
     * @param user
     * @return
     */
    int updateByIdSelective(SysUser user);


    /**
     * 动态SQL：Insert语句中的if标签
     *
     * @param user
     * @return
     */
    int insertSelective(SysUser user);

    /**
     * 动态SQL：choose标签
     *
     * @param user
     * @return
     */
    SysUser selectByIdOrUserName(SysUser user);

    /**
     * 动态SQL：where标签
     *
     * @param user
     * @return
     */
    List<SysUser> selectByUser2(SysUser user);

    /**
     * 动态SQL：set标签
     *
     * @param user
     * @return
     */
    int updateByIdSelective2(SysUser user);

    /**
     * foreach实现in集合
     *
     * @param idList
     * @return
     */
    List<SysUser> selectByIdList(List<Long> idList);

    /**
     * foreach实现批量插入
     *
     * @param userList
     * @return
     */
    int insertList(List<SysUser> userList);

    /**
     * foreach实现动态UPDATE
     *
     * @param map
     */
    void updateByMap(Map<String, Object> map);
}
