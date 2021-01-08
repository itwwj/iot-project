package com.gitee.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author jie
 */
public class JDKTest {
    public static void main(String[] args) {
        User a = new User("axiba",20);
        updata(a);
        System.out.println(a);
    }

    private static void updata(User a) {
      a.setName("000");
      a.setAge(10);
    }
}

@Data
@ToString
@AllArgsConstructor
class User {
    private String name;
    private int age;
}