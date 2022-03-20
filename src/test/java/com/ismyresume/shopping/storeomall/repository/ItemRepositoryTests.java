package com.ismyresume.shopping.storeomall.repository;

import com.ismyresume.shopping.storeomall.Times;
import com.ismyresume.shopping.storeomall.constant.ItemSellStatus;
import com.ismyresume.shopping.storeomall.entity.Item;
import com.ismyresume.shopping.storeomall.entity.QItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class ItemRepositoryTests {
    static LocalDateTime currentTime = Times.currentTime();
    @Autowired
    ItemRepository itemRepository;


    @PersistenceContext
    EntityManager em;

    @AfterEach
    public void after() {
        itemRepository.deleteAll();

    }

    @Test
    @DisplayName("상품 생성 후 데이터베이스 삽입")
    void createItemIsInsertionRepository() {
        String testItemName = "테스트상품";
        Item item = createItem(testItemName, 0);
        Item savedItem = itemRepository.save(item);

        assertThat(savedItem.getItemName()).isEqualTo("테스트상품" + 0);
    }

    private Item createItem(String testItemName, int count) {
        Item item = new Item();


        item.setItemName(testItemName + count);
        item.setPrice(10000 + count);
        item.setItemDetail("테스트 상품 설명" + count);

        if (count % 2 == 0) {
            item.setItemSellStatus(ItemSellStatus.SELL);
        } else {
            item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
        }

        item.setStockNumber(100);
        item.setModificationDate(currentTime);
        item.setRegistrationDate(currentTime);
        return item;
    }

    private void createItemList(String testItemName, int count) {

        for (int i = 1; i <= count; i++) {
            Item item = createItem(testItemName, i);
            itemRepository.save(item);
        }
    }


    @Test
    @DisplayName("데이터베이스에서 상품이름으로 조회하기")
    void findByItemName() {
        String testItemName = "테스트상품";
        int count = 10;
        createItemList(testItemName, count);
        List<Item> itemList = itemRepository.findByItemName(testItemName + "1");
        assertThat(itemList.get(0).getItemName()).isEqualTo(testItemName + "1");
    }

    @Test
    @DisplayName("상품이름 또는 설명으로 조회하기")
    void findByItemNameOrDetail() {

        String testItemName = "테스트상품";
        String testItemDetail = "테스트 상품 설명5";
        int count = 10;
        createItemList(testItemName, count);

        List<Item> itemList = itemRepository.findByItemNameOrItemDetail(testItemName + "1", testItemDetail);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(itemList.get(0).getItemName()).isEqualTo(testItemName + "1");
        softAssertions.assertThat(itemList.get(1).getItemDetail()).isEqualTo(testItemDetail);
        softAssertions.assertAll();

    }

    @Test
    @DisplayName("Price 값 중에서 LessThan 조건에 맞는 값에 해당하는 목록 반환")
    void findByPriceLessThan() {
        String testItemName = "테스트상품";
        int count = 10;

        createItemList(testItemName, count);

        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        Collections.shuffle(itemList);
        assertThat(itemList.get(0).getPrice()).isLessThan(10005);
    }

    @Test
    @DisplayName("Price 값 중에서 LessThan 조건에 맞는 값 내림차순 반환")
    void findByPriceLessThanOrderByPriceDesc() {
        String testItemName = "테스트상품";
        int count = 10;

        createItemList(testItemName, count);

        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);

        assertThat(itemList.get(0).getPrice()).isEqualTo(10004);
    }


    @Test
    @DisplayName("Price 값 중에서 LessThan 조건에 맞는 값 오름차순 반환")
    void findByPriceLessThanOrderByPriceAsc() {
        String testItemName = "테스트상품";
        int count = 10;
        createItemList(testItemName, count);
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceAsc(10005);
        assertThat(itemList.get(0).getPrice()).isEqualTo(10001);
    }

    @Test
    @DisplayName("@Query 사용하여 상품 상세 설명 검색을 통한 상품 조회")
    void findByItemNameWithQueryAnnotation() {
        String testItemName = "테스트상품";
        String testItemDetail = "테스트 상품 설명";
        int count = 10;
        createItemList(testItemName, count);

        List<Item> itemList = itemRepository.findByItemDetail(testItemDetail);

        assertThat(itemList.get(0).getItemDetail()).isEqualTo("테스트 상품 설명10");
    }


    @Test

    @DisplayName("Query DSL 사용하여 아이템 이름으로 조회. Find by ItemName Using Querydsl. ")
    void Find_By_ItemName_With_Querydsl() {

        String testItemName = "테스트상품";
        int count = 10;
        createItemList(testItemName, count);

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QItem qItem = QItem.item;

        JPAQuery<Item> query = queryFactory.selectFrom(qItem).where(qItem.itemSellStatus.eq(ItemSellStatus.SOLD_OUT))
                                           .where(qItem.itemDetail.like("%" + "테스트 상품 설명9" + "%"))
                                           .orderBy(qItem.price.desc());


        List<Item> itemList = query.fetch();

        assertThat(itemList.get(0).getItemDetail()).isEqualTo("테스트 상품 설명9");
    }

    @Test

    @DisplayName("Query Dsl Predicate Executor 사용하여 아이템 이름으로 조회. Find by ItemName Using Querydsl Predicate Executor. ")
    void findByItemNameWithQuerydslPredicate() {

        String testItemName = "테스트상품";
        int count = 10;
        createItemList(testItemName, count);


        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QItem qItem = QItem.item;

        String itemDetail = "테스트 상품 설명";

        int price = 10003;

        String itemSellStat = "SELL";

        booleanBuilder.and(qItem.itemDetail.like("%" + itemDetail + "%"));
        booleanBuilder.and(qItem.price.gt(price));

        if (StringUtils.equals(itemSellStat, ItemSellStatus.SELL)) {
            booleanBuilder.and(qItem.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        Pageable pageable = PageRequest.of(0, 5);

        Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder, pageable);

        System.out.println("totla elements : " + itemPagingResult.getTotalElements());

        List<Item> resultItemList = itemPagingResult.getContent();

        for (Item resultItem : resultItemList) {
            System.out.println(resultItem.toString());
        }


        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(resultItemList.get(0).getItemSellStatus()).isEqualTo(ItemSellStatus.SELL);
        softAssertions.assertThat(resultItemList.get(0).getItemDetail()).isEqualTo(itemDetail + "4");
        softAssertions.assertAll();
    }
}
