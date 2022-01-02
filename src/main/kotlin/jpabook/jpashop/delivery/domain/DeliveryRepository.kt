package jpabook.jpashop.delivery.domain

import org.springframework.data.jpa.repository.JpaRepository

interface DeliveryRepository : JpaRepository<Delivery, Long>
