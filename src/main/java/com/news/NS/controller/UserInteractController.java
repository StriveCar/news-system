package com.news.NS.controller;

import com.news.NS.service.UserInteractService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags="用户互动")
@Validated
public class UserInteractController {

    @Autowired
    UserInteractService userInteractService;
}
