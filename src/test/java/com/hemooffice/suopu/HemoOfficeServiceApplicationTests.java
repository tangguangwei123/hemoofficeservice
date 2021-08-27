package com.hemooffice.suopu;

import com.hemooffice.suopu.utils.CommonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

@SpringBootTest
class HemoOfficeServiceApplicationTests {

    @Test
    void contextLoads() throws UnsupportedEncodingException {
        System.out.print(CommonUtils.userPassMd5("tgw123456"));
    }
}
