package com.wang.spring.dao.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.wang.spring.dao.interceptor.MyFirstInterceptor;
import com.wang.spring.dao.interceptor.MySecondInterceptor;
import com.wang.spring.dao.properties.PropertiesConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author wangwei
 */
@Configuration
public class MybatisConfiguration {

    @Bean
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setName("test_name");
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl(PropertiesConfig.getProperties().getProperty("my.mysql.url"));
        druidDataSource.setUsername("root");
        druidDataSource.setMaxActive(200);
        druidDataSource.setMinIdle(200);
        druidDataSource.setMaxWait(5000);

        return druidDataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Autowired DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(dataSource);
        Resource[] resource = new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml");

        sqlSessionFactoryBean.setMapperLocations(resource);
        MyFirstInterceptor myFirstInterceptor = new MyFirstInterceptor();
        MySecondInterceptor mySecondInterceptor = new MySecondInterceptor();
        sqlSessionFactoryBean.setPlugins(myFirstInterceptor, mySecondInterceptor);

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.wang.spring.dao.mapper");
        scannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");

        return scannerConfigurer;
    }

}
