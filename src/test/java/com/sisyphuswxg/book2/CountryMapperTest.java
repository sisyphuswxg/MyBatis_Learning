package com.sisyphuswxg.book2;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.sisyphuswxg.book2.datasource.DataConnection;
import com.sisyphuswxg.book2.model.Country;


import java.io.IOException;
import java.util.List;


public class CountryMapperTest {
    public DataConnection dataConn = new DataConnection();

    @Test
    public void TestSelectAllCountries() throws IOException {
        SqlSession sqlSession = dataConn.getSqlSession();
        List<Country> countries = sqlSession.selectList("country.selectAll");
        for (Country country: countries) {
            System.out.println("---------------------------");
            System.out.println(country.getId() + " " + country.getCountryname() +
                    " " + country.getCountrycode());
        }
        sqlSession.close();
    }

}
