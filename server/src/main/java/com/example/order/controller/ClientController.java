package com.example.order.controller;

import com.example.product.client.ProductClient;
import com.example.product.common.DecreaseStockInput;
import com.example.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * 客户端采用轮询方式调用商品服务端, 启动8081端口, 访问地址: http://localhost:8081/getProductMsg
 */
@RestController
@Slf4j
public class ClientController {

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;

    /**
     * restTemplate调用
     * @return
     */
    @GetMapping("/getProductMsg")
    public String getProductMsg() {

        //1.第一种方式(直接使用RestTemplate, url写死)
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:8080/msg", String.class);

        //2.第二种方式(利用LoadBalancerClient通过应用名获取url,然后再使用restTemplate)
//        RestTemplate restTemplate = new RestTemplate();
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/msg";
//        String response = restTemplate.getForObject(url, String.class);

        //3.第三种方式(利用@LoadBalanced, 可在restTemplate里使用应用的名字, 默认采用轮询的方式)
        String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);
        log.info("response={}", response);

        //4.自定义负载均衡策略

        return response;
    }

    /**
     * Feign使用
     * @return
     */
    @GetMapping("/getProductMsg2")
    public String getProductMsg2() {
        String response = productClient.productMsg();
        log.info("response={}", response);
        return response;
    }

    /**
     * 测试获取商品列表
     * @return
     */
    @GetMapping("getProductList")
    public String getProductList() {
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(Arrays.asList("123456"));

        log.info("response={}", productInfoList);

        return "ok";
    }

    /**
     * 测试扣库存
     * @return
     */
    @GetMapping("decreaseStock")
    public String decreaseStock() {
        productClient.decreaseStock(Arrays.asList(new DecreaseStockInput("123456", 2)));
        return "ok";
    }
}
