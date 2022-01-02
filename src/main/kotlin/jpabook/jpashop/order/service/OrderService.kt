package jpabook.jpashop.order.service

import jpabook.jpashop.item.domain.ItemRepository
import jpabook.jpashop.member.domain.MemberRepository
import jpabook.jpashop.order.domain.Order
import jpabook.jpashop.order.domain.OrderItem
import jpabook.jpashop.order.domain.OrderItemRepository
import jpabook.jpashop.order.domain.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface OrderService {
    fun order(request: OrderItemRequest)
    fun getOrder(orderId: Long): Order
    fun cancel(orderId: Long)
}

@Service
@Transactional
class DefaultOrderService(
    private val memberRepository: MemberRepository,
    private val itemRepository: ItemRepository,
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository
) : OrderService {
    override fun order(request: OrderItemRequest) {
        val member = memberRepository.getById(request.memberId)
        val order = orderRepository.save(Order(member))

        val itemRequests: Map<Long, Int> = ItemRequest.toMap(request.items)
        val items = itemRepository.findAllById(itemRequests.keys)

        val orderItems = items.map {
            val count = itemRequests[it.id]!!

            it.sell(count)

            OrderItem(it, order, count)
        }

        orderItemRepository.saveAll(orderItems)
    }

    override fun getOrder(orderId: Long): Order {
        TODO("Not yet implemented")
    }

    override fun cancel(orderId: Long) {
        TODO("Not yet implemented")
    }
}
