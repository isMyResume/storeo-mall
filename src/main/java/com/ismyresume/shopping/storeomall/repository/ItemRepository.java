package com.ismyresume.shopping.storeomall.repository;


import com.ismyresume.shopping.storeomall.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {

    List<Item> findByItemName(String itemName);

    List<Item> findByItemNameOrItemDetail(String itemName, String itemDetail);

    List<Item> findByPriceLessThan(int price);

    List<Item> findByPriceLessThanOrderByPriceDesc(int price);

    List<Item> findByPriceLessThanOrderByPriceAsc(int i);

    @Query("select i from  Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

}
