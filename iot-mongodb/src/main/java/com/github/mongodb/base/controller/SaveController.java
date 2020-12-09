package com.github.mongodb.base.controller;

import com.github.mongodb.base.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author jie
 */
public interface SaveController<E> extends BaseController<E> {
    /**
     * 新增
     *
     * @return 实体
     */
    @ApiOperation(value = "新增")
    @PostMapping
    default R<E> save(@RequestBody @Validated E saveDTO) {
        E save = getservice().save(saveDTO);
        return R.success(save);
    }
}
