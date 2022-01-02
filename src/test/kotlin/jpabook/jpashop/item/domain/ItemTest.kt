package jpabook.jpashop.item.domain

import jpabook.jpashop.exception.ErrorCode
import jpabook.jpashop.extensions.assertThatJpaBookException
import jpabook.jpashop.extensions.errorCode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ItemTest {
    private val item = object : Item("아이템", 10_000, 100_000) {}

    @Test
    fun `상품 생성`() {
        assertThat(item).isEqualTo(object : Item("아이템", 10_000, 100_000) {})
    }

    @Test
    fun `상품 수정`() {
        val changeItem = object : Item("아이템", 10_000, 100_000) {}

        item.change(changeItem)

        assertThat(item)
            .extracting(Item::name, Item::price, Item::stockQuantity)
            .containsExactly(changeItem.name, changeItem.price, changeItem.stockQuantity)
    }

    @Test
    fun `상품 판매`() {
        val stockQuantity = item.stockQuantity!!

        item.sell(1)

        assertThat(item.stockQuantity).isEqualTo(stockQuantity.minus(1))
    }

    @Test
    fun `재고보다 많이 상품 판매를 하려고 하면 예외 발생`() {
        val stockQuantity = item.stockQuantity!!

        assertThatJpaBookException { item.sell(stockQuantity + 1) }.errorCode(ErrorCode.OUT_OF_STOCK)
    }
}
