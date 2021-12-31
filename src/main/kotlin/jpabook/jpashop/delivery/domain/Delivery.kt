package jpabook.jpashop.delivery.domain

import jpabook.jpashop.jpa.Address
import jpabook.jpashop.jpa.BaseEntity
import jpabook.jpashop.order.domain.Order
import javax.persistence.*

@Entity
class Delivery(
    @OneToOne
    var order: Order,

    @Embedded
    var address: Address,

    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus
) : BaseEntity()
