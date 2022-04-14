package com.greedy.data.menu.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@SpringBootTest
class MenuControllerTests {

    private final MappingJackson2JsonView jsonView;

    @Autowired
    MenuControllerTests(MappingJackson2JsonView jsonView) {
        this.jsonView = jsonView;
    }

    @Test
    public void initTest() {


    }
}