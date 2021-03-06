package com.ismyresume.shopping.storeomall.controller;

import com.ismyresume.shopping.storeomall.constant.ItemSellStatus;
import com.ismyresume.shopping.storeomall.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/thymeleaf")
public class ThymeleafExController {

    @GetMapping(value = "/ex01")
    public String thymeleafExample01(Model model) {
        model.addAttribute("data", "타임리프 예제1");

        return "thymeleafEx/thymeleafEx01";
    }


    @GetMapping(value = "/ex02")
    public String thymeleafExample02(Model model) {
        ItemDto itemDto = new ItemDto();
        itemDto.setItemName("테스트 상품");
        itemDto.setItemDetail("테스트 상품 설명");
        itemDto.setItemSellStatus(ItemSellStatus.SELL);
        itemDto.setPrice(10000);
        itemDto.setRegistrationDate(LocalDateTime.now());

        model.addAttribute("itemDto", itemDto);

        return "thymeleafEx/thymeleafEx02";
    }


}
