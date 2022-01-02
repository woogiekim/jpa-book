package jpabook.jpashop.order.domain

import jpabook.jpashop.delivery.domain.Delivery
import jpabook.jpashop.jpa.BaseEntity
import jpabook.jpashop.member.domain.Member
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "\"ORDER\"")
class Order(
    @ManyToOne
    var member: Member,

    @OneToOne
    var delivery: Delivery? = null,

    val orderDate: OffsetDateTime? = OffsetDateTime.now(),

    @Enumerated(EnumType.STRING)
    var status: OrderStatus = OrderStatus.ORDER
) : BaseEntity()
