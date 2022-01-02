package jpabook.jpashop.delivery.event

import jpabook.jpashop.delivery.domain.Delivery
import jpabook.jpashop.delivery.domain.DeliveryRepository
import jpabook.jpashop.order.domain.OrderToDeliveryEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class DeliveryEventListener(
    private val deliveryRepository: DeliveryRepository
) {
    @EventListener(OrderToDeliveryEvent::class)
    fun onOrderSuccess(event: OrderToDeliveryEvent) {
        deliveryRepository.save(Delivery(event.order, event.address))
    }
}
