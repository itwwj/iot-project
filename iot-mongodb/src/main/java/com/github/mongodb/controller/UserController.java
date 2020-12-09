package com.github.mongodb.controller;

import com.github.mongodb.base.controller.SuperDataController;
import com.github.mongodb.model.User;
import com.github.mongodb.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;


/**
 * @author jie
 */
@Api(value = "用户Controller")
@RestController
@RequestMapping("user")
public class UserController extends SuperDataController<User,UserService,String> {


}
