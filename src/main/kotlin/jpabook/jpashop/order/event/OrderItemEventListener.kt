package jpabook.jpashop.order.event

import jpabook.jpashop.item.domain.ItemRepository
import jpabook.jpashop.order.domain.OrderItem
import jpabook.jpashop.order.domain.OrderItemRepository
import jpabook.jpashop.order.domain.OrderToOrderItemEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class OrderItemEventListener(
    private val itemRepository: ItemRepository,
    private val orderItemRepository: OrderItemRepository
) {
    @EventListener(OrderToOrderItemEvent::class)
    fun onSuccessOrder(event: OrderToOrderItemEvent) {
        val itemRequests = event.itemRequests
        val items = itemRepository.findAllById(itemRequests.keys)

        val orderItems = items.map { item ->
            val count = itemRequests[item.id]!!

            item.sell(count)

            OrderItem(item, event.order, count)
        }

        orderItemRepository.saveAll(orderItems)
    }
}
