package com.ismyresume.shopping.storeomall;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldTests {


    @Test
    @DisplayName("helloWorld")
    void helloWorld() {
        String helloWorld = "helloWorld";
        HelloWorldClass helloWorldClass = new HelloWorldClass();
        String result = helloWorldClass.helloWorld();

        assertThat(result).isEqualTo(helloWorld);

    }
    

}
