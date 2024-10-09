package com.yt.test;

import com.yt.furn.bean.Furn;
import com.yt.furn.dao.FurnMapper;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MBGTest {

    @Test
    public void mbgTest() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        // 修改配置文件 需要将文件放在项目下
        File configFile = new File("mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    // 测试mbg的curd方法的使用
    @Test
    public void insertSelective(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 获取到mapper
        FurnMapper furnMapper = ioc.getBean(FurnMapper.class);
        Furn furn = new Furn(null, "北欧风格小桌子", "熊猫家居~", new BigDecimal(180), 666, 7,
                "'assets/images/product-image/1.jpg");
        System.out.println(furnMapper.insertSelective(furn));
    }

    @Test
    public void deleteByPrimaryKey(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 获取到mapper
        FurnMapper furnMapper = ioc.getBean(FurnMapper.class);
        System.out.println(furnMapper.deleteByPrimaryKey(6));
    }

    @Test
    public void updateByPrimaryKeySelective(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 获取到mapper
        FurnMapper furnMapper = ioc.getBean(FurnMapper.class);
        Furn furn = new Furn();
        furn.setId(5);
        furn.setName("yt家居~");
        System.out.println(furnMapper.updateByPrimaryKeySelective(furn));
    }

    @Test
    public void selectByPrimaryKey(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        FurnMapper furnMapper = ioc.getBean(FurnMapper.class);
        System.out.println(furnMapper.selectByPrimaryKey(5));
    }
}
