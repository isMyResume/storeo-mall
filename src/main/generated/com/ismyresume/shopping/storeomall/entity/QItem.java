package com.ismyresume.shopping.storeomall.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = 1623439642L;

    public static final QItem item = new QItem("item");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath itemDetail = createString("itemDetail");

    public final StringPath itemName = createString("itemName");

    public final EnumPath<com.ismyresume.shopping.storeomall.constant.ItemSellStatus> itemSellStatus = createEnum("itemSellStatus", com.ismyresume.shopping.storeomall.constant.ItemSellStatus.class);

    public final DateTimePath<java.time.LocalDateTime> modificationDate = createDateTime("modificationDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> registrationDate = createDateTime("registrationDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> stockNumber = createNumber("stockNumber", Integer.class);

    public QItem(String variable) {
        super(Item.class, forVariable(variable));
    }

    public QItem(Path<? extends Item> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItem(PathMetadata metadata) {
        super(Item.class, metadata);
    }

}

