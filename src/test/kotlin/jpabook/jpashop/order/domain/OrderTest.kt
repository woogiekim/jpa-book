package jpabook.jpashop.order.domain

import jpabook.jpashop.fixture.FixtureFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class OrderTest {
    private val member = FixtureFactory.createMember()

    @Test
    fun `주문 생성`() {
        val order = FixtureFactory.createOrder(member)

        assertAll(
            {
                assertThat(order.id).isNotNull
                assertThat(order.member).isNotNull
                assertThat(order.orderDate).isNotNull
                assertThat(order.status).isNotNull
            },
            {
                assertThat(order).isEqualTo(FixtureFactory.createOrder(member))
            }
        )
    }
}
