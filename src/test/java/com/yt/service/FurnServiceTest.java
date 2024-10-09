package com.yt.service;

import com.yt.furn.bean.Furn;
import com.yt.furn.service.FurnService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class FurnServiceTest {
    private ApplicationContext ioc;
    private FurnService furnService;

    @Before
    public void init(){
        ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 这里必须是接口类型，FurnService.class 涉及到底层的动态代理机制
        furnService = ioc.getBean(FurnService.class);
    }

    @Test
    public void test01(){
        Furn furn = new Furn(null, "小桌子", "熊猫家居~", new BigDecimal(180), 666, 7,
                "'assets/images/product-image/1.jpg");
        furnService.save(furn);
    }

    @Test
    public void findAll(){
        List<Furn> all = furnService.findAll();
        for (Furn furn : all) {
            System.out.println(furn);
        }
    }

    @Test
    public void update(){
        Furn furn = new Furn();
        furn.setId(11);
        furn.setName("yt1");
        furnService.update(furn);
    }

    @Test
    public void delete(){
        furnService.delete(12);
    }

    @Test
    public void findByCondition(){
        List<Furn> furns = furnService.findByCondition("");
        for (Furn furn : furns) {
            System.out.println(furn);
        }
    }
}
