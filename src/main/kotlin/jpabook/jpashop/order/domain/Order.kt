package jpabook.jpashop.order.domain

import jpabook.jpashop.jpa.Address
import jpabook.jpashop.jpa.BaseAggregateRoot
import jpabook.jpashop.member.domain.Member
import jpabook.jpashop.order.service.ItemRequest
import jpabook.jpashop.order.service.OrderItemRequest
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "\"ORDER\"")
class Order(
    @ManyToOne
    var member: Member,

    val orderDate: OffsetDateTime? = OffsetDateTime.now(),

    @Enumerated(EnumType.STRING)
    var status: OrderStatus = OrderStatus.ORDER
) : BaseAggregateRoot<Order>() {
    fun orderItem(request: OrderItemRequest) {
        val itemRequests: Map<Long, Int> = ItemRequest.toMap(request.items)

        publishOrderItems(itemRequests)
        publishDelivery(request.address)
    }

    private fun publishOrderItems(itemRequests: Map<Long, Int>) {
        this.andEvent(OrderToOrderItemEvent(this, itemRequests))
    }

    private fun publishDelivery(address: Address) {
        this.andEvent(OrderToDeliveryEvent(this, address))
    }
}
