package jpabook.jpashop.item.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ItemTest {
    private val item = object : Item("아이템", 10_000, 100_000) {}

    @Test
    fun `상품 생성`() {
        assertThat(item).isEqualTo(object : Item("아이템", 10_000, 100_000) {})
    }

    @Test
    internal fun `상품 수정`() {
        val changeItem = object : Item("아이템", 10_000, 100_000) {}

        item.change(changeItem)

        assertThat(item)
            .extracting(Item::name, Item::price, Item::stockQuantity)
            .containsExactly(changeItem.name, changeItem.price, changeItem.stockQuantity)
    }
}
