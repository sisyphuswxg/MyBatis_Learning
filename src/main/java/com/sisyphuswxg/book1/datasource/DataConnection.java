package com.sisyphuswxg.book1.datasource;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


/**
 * @author wangxg
 */
public class DataConnection {

    private String resource = "SqlMapConfig.xml";
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;

    public SqlSession getSqlSession() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }

}