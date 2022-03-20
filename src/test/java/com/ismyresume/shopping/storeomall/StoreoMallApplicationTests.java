package com.ismyresume.shopping.storeomall;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "dev")
class StoreoMallApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void contextLoads() {
    }



    @Test
    @DisplayName("helloWorldTestHTTP")
    void helloWorldTest() throws Exception {

        String helloWorld = "helloWorld";

        mvc.perform(get("/"))
           .andExpect(status().isOk())
           .andExpect(content().string(helloWorld));
    }

}
