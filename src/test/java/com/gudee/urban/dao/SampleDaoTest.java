package com.gudee.urban.dao;

import com.gudee.urban.config.MariaDataConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({MariaDataConfig.class})
class SampleDaoTest {

    @Autowired
    private SampleDao sampleDao;

    @Test
    @DisplayName("DB 연결 테스트")
    void connectTest() {
        String expected = "OK";
        String actual = this.sampleDao.connectTest();

        assertEquals(expected, actual);
    }
}
