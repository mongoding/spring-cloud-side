package com.topweshare;

import com.topweshare.properties.TeacherProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private TeacherProperties teacherProperties;


    @Test
    public void getHello() throws Exception {
        Assert.assertEquals(teacherProperties.getName(), "程序猿DD");
    }

}