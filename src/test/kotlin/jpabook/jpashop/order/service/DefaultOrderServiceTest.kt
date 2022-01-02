package jpabook.jpashop.order.service

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import jpabook.jpashop.fixture.FixtureFactory
import jpabook.jpashop.item.domain.Book
import jpabook.jpashop.jpa.Address
import jpabook.jpashop.member.domain.Member
import jpabook.jpashop.member.domain.MemberRepository
import jpabook.jpashop.order.domain.Order
import jpabook.jpashop.order.domain.OrderRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class DefaultOrderServiceTest {
    @MockK
    lateinit var memberRepository: MemberRepository

    @MockK
    lateinit var orderRepository: OrderRepository

    lateinit var orderService: OrderService

    lateinit var member: Member
    lateinit var book: Book

    @BeforeEach
    fun setUp() {
        orderService = DefaultOrderService(memberRepository, orderRepository)

        member = FixtureFactory.createMember()
        book = FixtureFactory.createBook()
    }

    @Test
    fun `상품 주문`() {
        val count = 3
        val request = OrderItemRequest(member.id!!, listOf(ItemRequest(book.id!!, count)), Address("인천시", "참외전로", "1-400"))
        val order = Order(member)

        every { memberRepository.getById(request.memberId) } returns member
        every { orderRepository.save(order) } returns order

        orderService.orderItem(request)

        verify(exactly = 1) {
            memberRepository.getById(request.memberId)
            orderRepository.save(order)
        }
    }

    @Test
    fun getOrder() {
    }

    @Test
    fun cancel() {
    }
}
