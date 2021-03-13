package com.github.iot.controller;

import com.github.iot.api.NodesApi;
import com.github.iot.api.restentity.node.Node;
import com.github.iot.api.restentity.node.NodeResponseList;
import com.github.iot.entity.HttpAuthCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @author jie
 */
@Slf4j
@RestController
@RequestMapping("/mqtt")
@AllArgsConstructor
public class LoadController {

    private final NodesApi nodesApi;

    @PostMapping("/auth")
    public ResponseEntity auth(String clientid, String username, String password) {
        log.info(clientid + "     " + username + "        " + password);
        if (password.equals("root")){
            return new  ResponseEntity(HttpStatus.OK);
        }else {
            return new  ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/broker")
    public String getBroker() {
        NodeResponseList nodes = nodesApi.findNodes();
        List<Node> data = nodes.getData();
        int size = data.size();
        Random random = new Random();
        int i = random.nextInt(size);
        Node node = data.get(i);
        return node.getNode();
    }


}
