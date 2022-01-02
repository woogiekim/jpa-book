package jpabook.jpashop.order.domain

import jpabook.jpashop.item.domain.Item
import jpabook.jpashop.jpa.BaseEntity
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class OrderItem(
    @ManyToOne
    val item: Item,

    @ManyToOne
    val order: Order,

    val count: Int,

    val orderPrice: Long = item.price?.times(count)?.toLong()!!,
) : BaseEntity()
