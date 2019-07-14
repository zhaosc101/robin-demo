/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/13 15:24
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.elasticsearch.controller;

import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/13 15:24
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/7/13 15:24
 */
@RestController
@RequestMapping("/es")
public class ESController {

    @Autowired
    private TransportClient transportClient;

    @GetMapping("find")
    public String find(){

        SearchRequestBuilder builder = transportClient.prepareSearch("test");

        return"";
    }
    @GetMapping("create/{index}")
    public String find(@PathVariable("index")String index){

        IndicesExistsResponse inExistsResponse = transportClient.admin().indices().exists(new IndicesExistsRequest(index)).actionGet();

        return"";
    }
}
