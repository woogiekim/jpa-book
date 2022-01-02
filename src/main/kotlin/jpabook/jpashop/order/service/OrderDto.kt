package jpabook.jpashop.order.service

data class OrderItemRequest(
    val memberId: Long,
    val items: List<ItemRequest>
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
