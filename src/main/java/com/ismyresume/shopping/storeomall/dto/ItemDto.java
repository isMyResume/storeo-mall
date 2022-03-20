package com.ismyresume.shopping.storeomall.dto;

import com.ismyresume.shopping.storeomall.constant.ItemSellStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ItemDto {

    private Long id;

    private String itemName;

    private int price;

    private String itemDetail;

    private ItemSellStatus itemSellStatus;

    private LocalDateTime registrationDate;

    private LocalDateTime modificationDate;

}
