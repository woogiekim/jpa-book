package jpabook.jpashop.delivery.domain

import jpabook.jpashop.fixture.FixtureFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class DeliveryTest {
    private val member = FixtureFactory.createMember()
    private val order = FixtureFactory.createOrder(member)

    @Test
    fun `배달 생성`() {
        val delivery = FixtureFactory.createDelivery(order)

        assertAll(
            {
                assertThat(delivery.id).isNotNull
                assertThat(delivery.order).isNotNull
                assertThat(delivery.address).isNotNull
                assertThat(delivery.status).isNotNull
            },
            {
                assertThat(delivery).isEqualTo(FixtureFactory.createDelivery(order))
            }
        )
    }
}
