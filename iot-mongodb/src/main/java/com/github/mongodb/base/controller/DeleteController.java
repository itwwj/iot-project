package com.github.mongodb.base.controller;

import com.github.mongodb.base.R;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author jie
 */
public interface DeleteController<Entity ,ID> extends BaseController<Entity> {


    /**
     * 删除方法
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "删除")
    @DeleteMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids[]", value = "主键id", dataType = "array", paramType = "query"),
    })
    default R<Boolean> delete(@RequestParam("ids[]") List<ID> ids) {
        for (ID id : ids) {
            getservice().deleteById(id);
        }
        return R.success();
    }
}
