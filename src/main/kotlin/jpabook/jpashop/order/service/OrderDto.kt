package jpabook.jpashop.order.service

import jpabook.jpashop.jpa.Address

data class OrderItemRequest(
    val memberId: Long,
    val items: List<ItemRequest>,
    val address: Address
)

data class ItemRequest(
    val itemId: Long,
    val count: Int
) {
    companion object {
        fun toMap(items: List<ItemRequest>): Map<Long, Int> {
            return with(items) {
                associate { it.itemId to it.count }
            }
        }
    }
}
