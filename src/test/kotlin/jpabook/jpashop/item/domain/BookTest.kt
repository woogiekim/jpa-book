package jpabook.jpashop.item.domain

import jpabook.jpashop.fixture.FixtureFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BookTest {
    private val book = FixtureFactory.createBook()

    @Test
    fun `책 생성`() {
        assertAll(
            {
                assertThat(book.id).isNotNull
                assertThat(book.name).isNotNull
                assertThat(book.price).isNotNull
                assertThat(book.stockQuantity).isNotNull
                assertThat(book.author).isNotNull
                assertThat(book.isbn).isNotNull
            },
            {
                assertThat(book).isEqualTo(FixtureFactory.createBook())
            }
        )
    }

    @Test
    fun `책 수정`() {
        val changeBook = Book.create(
            "자바 표준 ORM 기술 JPA 지금부터 시작",
            45_000,
            3_000_000,
            "김태욱",
            "ISBN 111-111-1111"
        )

        book.change(changeBook)

        assertThat(book)
            .extracting(Book::name, Book::price, Book::stockQuantity, Book::author, Book::isbn)
            .containsExactly(changeBook.name, changeBook.price, changeBook.stockQuantity, changeBook.author, changeBook.isbn)
    }
}
