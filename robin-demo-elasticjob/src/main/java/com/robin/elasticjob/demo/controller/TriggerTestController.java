/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/9 19:28
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.elasticjob.demo.controller;

import com.google.common.base.Optional;
import com.robin.elasticjob.demo.demo.TriggerByHandTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/9 19:28
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/7/9 19:28
 */
@RestController
public class TriggerTestController {

    @Autowired TriggerByHandTest triggerByHandTest;


    @RequestMapping(value = "test/{name}")
    public String test(@PathVariable("name")String name) {
        triggerByHandTest.trigger(Optional.of(name),Optional.absent());
        return "success";
    }
}
