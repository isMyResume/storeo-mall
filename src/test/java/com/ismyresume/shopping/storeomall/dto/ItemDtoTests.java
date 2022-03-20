package com.ismyresume.shopping.storeomall.dto;

import com.ismyresume.shopping.storeomall.Times;
import com.ismyresume.shopping.storeomall.constant.ItemSellStatus;
import com.ismyresume.shopping.storeomall.entity.Item;
import com.ismyresume.shopping.storeomall.repository.ItemRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class ItemDtoTests {

    static LocalDateTime currentTime = Times.currentTime();

    @Test
    @DisplayName("  Item 에서 ItemDto 로 Mapping. mapping Item To ItemDto.")
    void mappingItemToItemDto() {
        Item item = new Item();
        item.setId(1L);
        item.setItemName("테스트 상품");
        item.setItemDetail("테스트 상품 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setPrice(10000);
        item.setRegistrationDate(currentTime);
        item.setModificationDate(currentTime);

        ModelMapper modelMapper = new ModelMapper();
        ItemDto itemDtoExpected = modelMapper.map(item, ItemDto.class);


        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(item.getItemName()).isEqualTo(itemDtoExpected.getItemName());
        softAssertions.assertThat(item.getItemDetail()).isEqualTo(itemDtoExpected.getItemDetail());
        softAssertions.assertThat(item.getItemSellStatus()).isEqualTo(itemDtoExpected.getItemSellStatus());
        softAssertions.assertThat(item.getPrice()).isEqualTo(itemDtoExpected.getPrice());
        softAssertions.assertThat(item.getRegistrationDate()).isEqualTo(itemDtoExpected.getRegistrationDate());
        softAssertions.assertThat(item.getModificationDate()).isEqualTo(itemDtoExpected.getModificationDate());
        softAssertions.assertAll();

    }

    @Test
    @DisplayName(" ItemDto 에서 Item 으로 Mapping. mapping ItemDto To Item.")
    void mappingItemDtoToItem() {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(1L);
        itemDto.setItemName("테스트 상품");
        itemDto.setItemDetail("테스트 상품 설명");
        itemDto.setItemSellStatus(ItemSellStatus.SELL);
        itemDto.setPrice(10000);
        itemDto.setRegistrationDate(currentTime);
        itemDto.setModificationDate(currentTime);

        ModelMapper modelMapper = new ModelMapper();
        Item itemExpected = modelMapper.map(itemDto, Item.class);


        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(itemDto.getItemName()).isEqualTo(itemExpected.getItemName());
        softAssertions.assertThat(itemDto.getItemDetail()).isEqualTo(itemExpected.getItemDetail());
        softAssertions.assertThat(itemDto.getItemSellStatus()).isEqualTo(itemExpected.getItemSellStatus());
        softAssertions.assertThat(itemDto.getPrice()).isEqualTo(itemExpected.getPrice());
        softAssertions.assertThat(itemDto.getRegistrationDate()).isEqualTo(itemExpected.getRegistrationDate());
        softAssertions.assertThat(itemDto.getModificationDate()).isEqualTo(itemExpected.getModificationDate());
        softAssertions.assertAll();

    }
}
