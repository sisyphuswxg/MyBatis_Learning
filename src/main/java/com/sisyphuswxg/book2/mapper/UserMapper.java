package com.sisyphuswxg.book2.mapper;

import com.sisyphuswxg.book2.model.*;

import java.util.List;

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

}
