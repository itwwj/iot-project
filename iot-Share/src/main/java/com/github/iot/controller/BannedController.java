package com.github.iot.controller;

import com.github.iot.api.BannedApi;
import com.github.iot.entity.rest.request.Sbanned;
import com.github.iot.entity.rest.response.Gbanned;
import com.github.iot.entity.rest.response.R;
import com.github.iot.entity.rest.response.Result;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

/**
 * @author jie
 */
@Data
@RestController
public class BannedController {

    private final BannedApi api;

    @GetMapping("/getBanned")
    public Gbanned getBanned(int page,int size) {
        return api.getBanneds(page, size);
    }

    @PostMapping("/setBanned")
    public R setBanned(@RequestBody Sbanned sbanned) {
        return api.setBanned(sbanned);
    }

    @DeleteMapping("/delBanned/{as}/{who}")
    public Result delBanned(@PathVariable("as") String as, @PathVariable("who") String who) {
        return api.delBanned(as, who);
    }
}
