package jpabook.jpashop.order.service

import jpabook.jpashop.member.domain.MemberRepository
import jpabook.jpashop.order.domain.Order
import jpabook.jpashop.order.domain.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface OrderService {
    fun orderItem(request: OrderItemRequest)
    fun getOrder(orderId: Long): Order
    fun cancel(orderId: Long)
}

@Service
@Transactional
class DefaultOrderService(
    private val memberRepository: MemberRepository,
    private val orderRepository: OrderRepository,
) : OrderService {
    override fun orderItem(request: OrderItemRequest) {
        val member = memberRepository.getById(request.memberId)
        val order = orderRepository.save(Order(member))

        order.orderItem(request)
    }

    override fun getOrder(orderId: Long): Order {
        TODO("Not yet implemented")
    }

    override fun cancel(orderId: Long) {
        TODO("Not yet implemented")
    }
}
