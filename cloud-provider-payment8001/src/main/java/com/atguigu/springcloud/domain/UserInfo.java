package com.atguigu.springcloud.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data

public class UserInfo {

    @ExcelProperty(value = "用户ID",index = 1)
    private  int id;

    @ExcelProperty(value = "用户姓名")
    private String fname;

    @ExcelProperty(value = "流量姓名")
    private String lname;

    @ExcelProperty(value = "年龄")
    private  int  age;

    @ExcelProperty(value = "性别")
    private int sex;

    private int pageNum;

    private int pageSize;
}
