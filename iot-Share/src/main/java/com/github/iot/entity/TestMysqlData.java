package com.github.iot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jie
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("test_data")
public class TestMysqlData {
    @TableId("id")
    private Long id;
    private String deviceName;
}
