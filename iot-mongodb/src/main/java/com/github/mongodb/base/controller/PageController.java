package com.github.mongodb.base.controller;

import com.github.mongodb.base.R;
import com.github.mongodb.base.service.BaseDataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;


/**
 * @author jie
 */
interface PageController<Entity > extends BaseController<Entity> {

    /**
     * 分页查询
     *
     * @return
     */
    @ApiOperation(value = "分页列表查询")
    @GetMapping(value = "/page")
    default R<Page<Entity>> page(int page,int size) throws Exception {
        BaseDataService<Entity> getservice = getservice();

        Page<Entity> page1 = getservice.findPage(page, size);
        return R.success(page1);
    }
    /**
     * 条件分页查询
     *
     * @return
     */
    @ApiOperation(value = "分页列表查询")
    @PostMapping(value = "/page")
    default R<Page<Entity>> exmaplePage(@RequestBody Map<String,String> map,int page,int size) throws Exception {
        BaseDataService<Entity> getservice = getservice();
        Page<Entity> byExample = getservice.findByExample(map, page, size);
        return R.success(byExample);
    }

}
