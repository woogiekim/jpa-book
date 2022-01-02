package jpabook.jpashop.eventpulish

import jpabook.jpashop.fixture.FixtureFactory
import jpabook.jpashop.item.domain.Item
import jpabook.jpashop.item.domain.ItemRepository
import jpabook.jpashop.jpa.Address
import jpabook.jpashop.member.domain.Member
import jpabook.jpashop.member.domain.MemberRepository
import jpabook.jpashop.order.domain.OrderToDeliveryEvent
import jpabook.jpashop.order.domain.OrderToOrderItemEvent
import jpabook.jpashop.order.service.ItemRequest
import jpabook.jpashop.order.service.OrderItemRequest
import jpabook.jpashop.order.service.OrderService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.event.ApplicationEvents
import org.springframework.test.context.event.RecordApplicationEvents
import org.springframework.transaction.annotation.Transactional
import kotlin.streams.toList

@Transactional
@SpringBootTest
@RecordApplicationEvents
class OrderEventPublishTest @Autowired constructor(
    private val memberRepository: MemberRepository,
    private val itemRepository: ItemRepository,
    private val orderService: OrderService
) {
    @Autowired
    lateinit var applicationEvents: ApplicationEvents

    private lateinit var member: Member
    private lateinit var items: List<Item>
    private lateinit var itemRequests: List<ItemRequest>
    private lateinit var address: Address

    @BeforeEach
    fun setUp() {
        member = memberRepository.save(FixtureFactory.createMember())

        items = itemRepository.saveAll(listOf(FixtureFactory.createBook(), FixtureFactory.createAlbum()))
        itemRequests = items.map { ItemRequest(it.id!!, 3) }

        address = FixtureFactory.createAddress()
    }

    @Test
    fun `상품 주문을 하면 배달, 상품 아이템 이벤트 발행`() {
        val request = OrderItemRequest(member.id!!, itemRequests, address)

        orderService.orderItem(request)

        assertThat(applicationEvents.stream(OrderToOrderItemEvent::class.java).toList()).isNotEmpty
        assertThat(applicationEvents.stream(OrderToDeliveryEvent::class.java).toList()).isNotEmpty
    }
}
