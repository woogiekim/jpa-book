package jpabook.jpashop.order.service

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import jpabook.jpashop.fixture.FixtureFactory
import jpabook.jpashop.item.domain.Book
import jpabook.jpashop.item.domain.ItemRepository
import jpabook.jpashop.member.domain.Member
import jpabook.jpashop.member.domain.MemberRepository
import jpabook.jpashop.order.domain.Order
import jpabook.jpashop.order.domain.OrderItem
import jpabook.jpashop.order.domain.OrderItemRepository
import jpabook.jpashop.order.domain.OrderRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class DefaultOrderServiceTest {
    @MockK
    lateinit var memberRepository: MemberRepository

    @MockK
    lateinit var itemRepository: ItemRepository

    @MockK
    lateinit var orderRepository: OrderRepository

    @MockK
    lateinit var orderItemRepository: OrderItemRepository

    lateinit var orderService: OrderService

    lateinit var member: Member
    lateinit var book: Book

    @BeforeEach
    fun setUp() {
        orderService = DefaultOrderService(memberRepository, itemRepository, orderRepository, orderItemRepository)

        member = FixtureFactory.createMember()
        book = FixtureFactory.createBook()
    }

    @Test
    fun `상품 주문`() {
        val count = 3
        val request = OrderItemRequest(member.id!!, listOf(ItemRequest(book.id!!, count)))
        val itemRequests = ItemRequest.toMap(request.items)
        val order = Order(member)
        val orderItems = listOf(OrderItem(book, order, count))

        val stockQuantity = book.stockQuantity!!

        every { memberRepository.getById(request.memberId) } returns member
        every { orderRepository.save(order) } returns order
        every { itemRepository.findAllById(itemRequests.keys) } returns listOf(book)
        every { orderItemRepository.saveAll(orderItems) } returns orderItems

        orderService.order(request)

        assertThat(book.stockQuantity).isEqualTo(stockQuantity.minus(3))

        verify(exactly = 1) {
            memberRepository.getById(request.memberId)
            orderRepository.save(order)
            itemRepository.findAllById(itemRequests.keys)
            orderItemRepository.saveAll(orderItems)
        }
    }

    @Test
    fun getOrder() {
    }

    @Test
    fun cancel() {
    }
}
