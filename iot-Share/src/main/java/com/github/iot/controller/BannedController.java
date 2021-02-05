package com.github.iot.controller;

import com.github.iot.api.BannedApi;
import com.github.iot.api.restentity.banned.Sbanned;
import com.github.iot.api.restentity.banned.Gbanned;
import com.github.iot.api.restentity.banned.R;
import com.github.iot.api.restentity.banned.Result;
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
        return api.select(page, size);
    }

    @PostMapping("/setBanned")
    public R setBanned(@RequestBody Sbanned sbanned) {
        return api.instert(sbanned);
    }

    @DeleteMapping("/delBanned/{as}/{who}")
    public Result delBanned(@PathVariable("as") String as, @PathVariable("who") String who) {
        return api.del(as, who);
    }
}
