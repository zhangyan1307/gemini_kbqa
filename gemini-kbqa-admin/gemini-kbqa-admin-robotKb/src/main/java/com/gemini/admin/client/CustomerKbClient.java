package com.gemini.admin.client;

import com.gemini.admin.facade.CustomerKbFacade;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * @Author: XXY
 * @Date: 2020/12/14 22:41
 */
@Component
public class CustomerKbClient {
    @Reference
    private CustomerKbFacade customerKbFacade;


}
