package jpabook.jpashop.order.domain

import jpabook.jpashop.fixture.FixtureFactory
import jpabook.jpashop.item.domain.Book
import jpabook.jpashop.member.domain.Member
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
class OrderItemTest {
    private val count = 3

    private lateinit var member: Member
    private lateinit var order: Order
    private lateinit var book: Book
    private lateinit var orderItem: OrderItem

    @BeforeEach
    fun setUp() {
        member = FixtureFactory.createMember()
        order = FixtureFactory.createOrder(member)
        book = FixtureFactory.createBook()
        orderItem = FixtureFactory.createOrderItem(book, order, count)
    }

    @Test
    fun `주문 아이템 생성`() {
        assertThat(orderItem.orderPrice).isEqualTo(book.price!!.times(count).toLong())
        assertThat(orderItem).isEqualTo(FixtureFactory.createOrderItem(book, order, count))
    }
}
