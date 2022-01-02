package jpabook.jpashop.order.domain

import jpabook.jpashop.jpa.Address

class OrderToDeliveryEvent(val order: Order, val address: Address)

class OrderToOrderItemEvent(val order: Order, val itemRequests: Map<Long, Int>)
