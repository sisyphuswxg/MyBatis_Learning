package com.sisyphuswxg.book2;

import com.sisyphuswxg.book2.mapper.UserMapper;
import com.sisyphuswxg.book2.model.SysRole;
import com.sisyphuswxg.book2.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class UserMapperTest extends BaseMapperTest {

    /**
     * selectById: 通过id查找指定用户
     */
    @Test
    public void testSelectById() {
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用 selectById 方法，查询 id = 1 的用户
            SysUser user = userMapper.selectById(1l);
            System.out.println("user info : " + user);
            //user 不为空
            Assert.assertNotNull(user);
            //userName = admin
            Assert.assertEquals("admin", user.getUserName());
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * selectAll: 查找所有用户
     */
    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = userMapper.selectAll();
            Assert.assertNotNull(userList);
            Assert.assertTrue(userList.size() > 0);
            for (SysUser user : userList) {
                System.out.println("user info : " + user);
            }
        } finally {
            sqlSession.close();
        }
    }

    /**
     * selectRolesByUserId: 通过用户id查找角色信息（只返回角色信息）
     */
    @Test
    public void testSelectRolesByUserId() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> roleList = userMapper.selectRolesByUserId(1l);
            Assert.assertNotNull(roleList);
            Assert.assertTrue(roleList.size() > 0);
            for (SysRole role : roleList) {
                System.out.println("role info : " + role);
            }
        } finally {
            sqlSession.close();
        }
    }

    /**
     * selectRolesByUserIdPro: 通过用户id查找角色信息（返回角色信息 + 用户信息，用户姓名和用户邮箱）
     */
    @Test
    public void testSelectRolesByUserIdPro() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> roleList = userMapper.selectRolesByUserIdPro(1l);
            Assert.assertNotNull(roleList);
            Assert.assertTrue(roleList.size() > 0);
            for (SysRole role : roleList) {
                System.out.println("role info : " + role);
            }
        } finally {
            sqlSession.close();
        }
    }

    /**
     * insertOneUser: 插入一条用户记录
     */
    @Test
    public void testInsertOneUser(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("wangxuguang");
            user.setUserPassword("wangxuguang");
            user.setUserEmail("sisyphuswxg@gmail.com");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            //注意，这里的返回值 result 是执行的 SQL 影响的行数
            int result = userMapper.insertOneUser(user);
            //只插入 1 条数据
            Assert.assertEquals(1, result);
            //id 为 null，我们没有给 id 赋值，并且没有配置回写 id 的值
            Assert.assertNull(user.getId());
            System.out.println("user id: " + user.getId());
            //由于默认的 sqlSessionFactory.openSession() 是不自动提交的
            //不手动执行 commit 也不会提交到数据库
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * insertOneUser2: 插入一条用户记录并返回新增记录的id - 方法一
     */
    @Test
    public void testInsertOneUser2(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("sisyphuswxg");
            user.setUserPassword("sisyphuswxg");
            user.setUserEmail("yiyi@gmail.com");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            // 返回的是：更新的记录数，这里是1
            int result = userMapper.insertOneUser2(user);
            System.out.println("result: " + result);
            // 获取id
            System.out.println("id: " + user.getId());
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * insertOneUser3: 插入一条用户记录并返回新增记录的id - 方法二
     */
    @Test
    public void testInsertOneUser3(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("wangyiyi");
            user.setUserPassword("helloworld");
            user.setUserEmail("yiyi@gmail.com");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            // 返回的是：更新的记录数，这里是1
            int result = userMapper.insertOneUser3(user);
            System.out.println("result: " + result);
            // 获取id
            System.out.println("id: " + user.getId());
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

}