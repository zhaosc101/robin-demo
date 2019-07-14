/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/13 15:31
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.elasticsearch;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/13 15:31
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/7/13 15:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ESApplication.class)
public class AppTest {

    @Autowired
    private TransportClient transportClient;

    @Test
    public void test() {
        CreateIndexRequest request = new CreateIndexRequest("test");
        ActionFuture<CreateIndexResponse> result = transportClient.admin().indices()
                .create(request);

        System.out.println(result);
    }
}
