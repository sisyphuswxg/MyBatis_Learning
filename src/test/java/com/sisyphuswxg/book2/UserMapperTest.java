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

    /**
     * updateById: 根据主键(id)更新
     */
    @Test
    public void testUpdateById(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectById(1L);
            //当前 userName 为 admin
            Assert.assertEquals("admin", user.getUserName());
            //修改用户名
            user.setUserName("admin_test");
            //修改邮箱
            user.setUserEmail("admin@gmail.com");
            //更新数据，特别注意，这里的返回值 result 是执行的 SQL 影响的行数
            int result = userMapper.updateById(user);
            //只更新 1 条数据
            Assert.assertEquals(1, result);
            //根据当前 id 查询修改后的数据
            user = userMapper.selectById(1L);
            //修改后的名字 admin_test
            Assert.assertEquals("admin_test", user.getUserName());
            Assert.assertEquals("admin@gmail.com", user.getUserEmail());
        } finally {
            //为了不影响数据库中的数据导致其他测试失败，这里选择回滚
            //由于默认的 sqlSessionFactory.openSession() 是不自动提交的，
            //因此不手动执行 commit 也不会提交到数据库
            sqlSession.rollback();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * deleteById: 根据主键(id)删除
     */
    @Test
    public void testDeleteById(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //从数据库查询 1 个 user 对象，根据 id = 1 查询
            SysUser user1 = userMapper.selectById(1L);
            //现在还能查询出 user 对象
            Assert.assertNotNull(user1);
            //调用方法删除
            Assert.assertEquals(1, userMapper.deleteById(1L));
            //再次查询，这时应该没有值，为 null
            Assert.assertNull(userMapper.selectById(1L));
        } finally {
            //为了不影响数据库中的数据导致其他测试失败，这里选择回滚
            //由于默认的 sqlSessionFactory.openSession() 是不自动提交的，
            //因此不手动执行 commit 也不会提交到数据库
            sqlSession.rollback();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 多个参数时： 不使用@Param注解
     */
    @Test
    public void testSelectRolesByUserIdAndRoleEnabled01(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用 selectRolesByUserIdAndRoleEnabled 方法查询用户的角色
            List<SysRole> roleList = userMapper.selectRolesByUserIdAndRoleEnabled01(1L, 1);
            //结果不为空
            Assert.assertNotNull(roleList);
            //角色数量大于 0 个
            Assert.assertTrue(roleList.size() > 0);
            for (SysRole role: roleList) {
                System.out.println("role info: " + role);
            }
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 多个参数时： 使用@Param注解（更推荐使用这种方法）
     */
    @Test
    public void testSelectRolesByUserIdAndRoleEnabled02(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用 selectRolesByUserIdAndRoleEnabled 方法查询用户的角色
            List<SysRole> roleList = userMapper.selectRolesByUserIdAndRoleEnabled02(1L, 1);
            //结果不为空
            Assert.assertNotNull(roleList);
            //角色数量大于 0 个
            Assert.assertTrue(roleList.size() > 0);
            for (SysRole role: roleList) {
                System.out.println("role info: " + role);
            }
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 动态SQL语句： Select语句中的if标签
     *  功能：实现用户高级查询，根据输入的条件去检索用户信息。
     *    - 当只输入用户名时，根据用户名进行模糊查询；
     *    - 当只输入邮箱时，根据邮箱进行模糊查询；
     *    - 同时输入用户名和邮箱，则根据两个条件去查询；
     */
    @Test
    public void testSelectByUser(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            //只查询用户名时
            SysUser query = new SysUser();
            query.setUserName("ad");
            List<SysUser> userList = userMapper.selectByUser(query);
            Assert.assertTrue(userList.size() > 0);
            System.out.println("---- 只查询用户名 ---- ");
            for (SysUser user: userList) {
                System.out.println("user info: " + user);
            }

            //只查询用户邮箱时
            query = new SysUser();
            query.setUserEmail("test@mybatis.tk");
            userList = userMapper.selectByUser(query);
            Assert.assertTrue(userList.size() > 0);
            System.out.println("---- 只查询用户邮箱 ---- ");
            for (SysUser user: userList) {
                System.out.println("user info: " + user);
            }

            //当同时查询用户名和邮箱时
            query = new SysUser();
            query.setUserName("admin");
            query.setUserEmail("admin");
            userList = userMapper.selectByUser(query);
            Assert.assertTrue(userList.size() > 0);
            System.out.println("---- 查询用户名和邮箱 ---- ");
            for (SysUser user: userList) {
                System.out.println("user info: " + user);
            }
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * SQL动态语句：Update语句中的if标签
     *  功能：更新的时候只更新有变化的字段，不能将原有值但没发生变化的字段更新为空或null.
     *
     *  注意：MyBatis中一般会将选择性更新的方法名以Selective作为后缀
     */
    @Test
    public void testUpdateByIdSelective(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //从数据库查询 1 个 user 对象
            SysUser user = new SysUser();
            //更新 id = 1 的用户
            user.setId(1L);
            //修改邮箱
            user.setUserEmail("helloworld@mybatis.tk");
            //将新建的对象插入数据库中，特别注意，这里的返回值 result 是执行的 SQL 影响的行数
            int result = userMapper.updateByIdSelective(user);
            //只更新 1 条数据
            Assert.assertEquals(1, result);
            //根据当前 id 查询修改后的数据
            user = userMapper.selectById(1L);
            //修改后的名字保持不变，但是邮箱变成了新的
            Assert.assertEquals("admin", user.getUserName());
            Assert.assertEquals("helloworld@mybatis.tk", user.getUserEmail());
            System.out.println("user info: " + user);
        } finally {
            //为了不影响数据库中的数据导致其他测试失败，这里选择回滚
            sqlSession.rollback();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * SQL动态语句：Insert语句中的if标签
     *  功能：插入的时候，如果某一列的参数值不为空，则使用传入的值；
     *       如果传入参数为空，则使用默认值，而不是使用传入的空值
     */
    @Test
    public void testInsertSelective(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            int result = userMapper.insertSelective(user);
            Assert.assertEquals(1, result);
            // 获取新插入的数据id
            user = userMapper.selectById(user.getId());
            System.out.println("user id: " + user.getId());
            System.out.println("user email: " + user.getUserEmail());
            Assert.assertEquals("test@mybatis.com", user.getUserEmail());
            Assert.assertNotNull(user.getId());

        } finally {
            sqlSession.commit();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 动态SQL： choose标签
     *   功能：当参数id有值的时候优先使用id查询，当id没有值的时候就去判断用户名是否有值，有值就使用用户名查询；
     *        如果用户名也没有值，就使SQL查询无结果。
     */
    @Test
    public void testSelectByIdOrUserName(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser query = new SysUser();
            query.setId(1L);
            query.setUserName("admin");

            //只查询用户名时
            SysUser user = userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            System.out.println("user info: " + user);

            //当没有 id 时
            query.setId(null);
            user = userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            System.out.println("user info: " + user);

            //当 id 和 name 都为空时
            query.setUserName(null);
            user = userMapper.selectByIdOrUserName(query);
            Assert.assertNull(user);
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 动态SQL语句： where标签
     *  功能：实现用户高级查询，根据输入的条件去检索用户信息。
     *    - 当只输入用户名时，根据用户名进行模糊查询；
     *    - 当只输入邮箱时，根据邮箱进行模糊查询；
     *    - 同时输入用户名和邮箱，则根据两个条件去查询；
     *  使用where标签来优化上面的if标签语句
     */
    @Test
    public void testSelectByUser2(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            //只查询用户名时
            SysUser query = new SysUser();
            query.setUserName("ad");
            List<SysUser> userList = userMapper.selectByUser2(query);
            Assert.assertTrue(userList.size() > 0);
            System.out.println("---- 只查询用户名 ---- ");
            for (SysUser user: userList) {
                System.out.println("user info: " + user);
            }

            //只查询用户邮箱时
            query = new SysUser();
            query.setUserEmail("test@mybatis.tk");
            userList = userMapper.selectByUser2(query);
            Assert.assertTrue(userList.size() > 0);
            System.out.println("---- 只查询用户邮箱 ---- ");
            for (SysUser user: userList) {
                System.out.println("user info: " + user);
            }

            //当同时查询用户名和邮箱时
            query = new SysUser();
            query.setUserName("admin");
            query.setUserEmail("admin");
            userList = userMapper.selectByUser2(query);
            Assert.assertTrue(userList.size() > 0);
            System.out.println("---- 查询用户名和邮箱 ---- ");
            for (SysUser user: userList) {
                System.out.println("user info: " + user);
            }
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }



}