package com.github.mongodb.base.controller;

import com.github.mongodb.base.R;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author jie
 */
public interface QueryController<Entity > extends BaseController<Entity> {
    /**
     * 查询
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", dataType = "long", paramType = "query"),
    })
    @ApiOperation(value = "查询", notes = "查询")
    @GetMapping("/{id}")
    default R<Entity> get(@PathVariable String id) {
        return R.success(getservice().findById(id));
    }
}
