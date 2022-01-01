package jpabook.jpashop.item.service

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import jpabook.jpashop.exception.ErrorCode
import jpabook.jpashop.extensions.assertThatJpaBookException
import jpabook.jpashop.extensions.errorCode
import jpabook.jpashop.fixture.FixtureFactory
import jpabook.jpashop.item.domain.Book
import jpabook.jpashop.item.domain.Item
import jpabook.jpashop.item.domain.ItemRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull

@ExtendWith(MockKExtension::class)
class DefaultItemServiceTest {

    @MockK
    lateinit var itemRepository: ItemRepository

    lateinit var itemService: ItemService

    lateinit var book: Item

    @BeforeEach
    fun setUp() {
        itemService = DefaultItemService(itemRepository)

        book = FixtureFactory.createBook()
    }

    @Test
    fun `상품 등록`() {
        every { itemRepository.save(book) } returns book

        val actualItem = itemService.addItem(book)

        assertThat(actualItem).isEqualTo(book)

        verify(exactly = 1) { itemRepository.save(book) }
    }

    @Test
    fun `상품 수정`() {
        val changeBook = (book as Book).apply {
            name = "자바 표준 ORM 기술 JPA 지금부터 시작"
            price = 45_000
            stockQuantity = 3_000_000
            author = "김태욱"
            isbn = "ISBN 111-111-1111"
        }

        every { itemRepository.findByIdOrNull(changeBook.id!!) } returns changeBook

        val actualBook = (itemService.updateItem(changeBook.id!!, changeBook) as? Book)!!

        assertThat(actualBook).isNotNull

        verify(exactly = 1) { itemRepository.findByIdOrNull(changeBook.id!!) }
    }

    @Test
    fun `상품 조회`() {
        every { itemRepository.findByIdOrNull(book.id!!) } returns book

        val item = itemService.getItem(book.id!!)

        assertThat(item).isNotNull
        assertThat(item).isEqualTo(book)

        verify(exactly = 1) { itemRepository.findByIdOrNull(book.id!!) }
    }

    @Test
    fun `상품이 없으면 예외 발생`() {
        every { itemRepository.findByIdOrNull(book.id!!) } returns null

        assertThatJpaBookException { itemService.getItem(book.id!!) }.errorCode(ErrorCode.NOT_EXISTS_ITEM)

        verify(exactly = 1) { itemRepository.findByIdOrNull(book.id!!) }
    }

    @Test
    fun `상품 목록 조회`() {
        val book = FixtureFactory.createBook()
        val movie = FixtureFactory.createMovie()
        val album = FixtureFactory.createAlbum()

        val pageable = PageRequest.of(0, 3)

        every { itemRepository.findAll(pageable) } returns PageImpl(listOf(book, movie, album))

        val items = itemService.getItems(pageable)

        assertThat(items).containsExactly(book, movie, album)

        verify(exactly = 1) { itemRepository.findAll(pageable) }
    }
}
