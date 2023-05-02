package com.time.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BlogSpringbootApplicationTests {

    @Test
    void contextLoads() {

        List<String> list = new ArrayList<>();
        list.add("user");
        list.add("admin");
        System.out.println(list);
    }
}
