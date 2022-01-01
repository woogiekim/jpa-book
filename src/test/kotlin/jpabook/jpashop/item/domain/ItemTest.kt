package jpabook.jpashop.item.domain

import jpabook.jpashop.fixture.FixtureFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ItemTest {
    @Test
    fun `앨범 생성`() {
        val album = FixtureFactory.createAlbum()

        assertAll(
            {
                assertThat(album.id).isNotNull
                assertThat(album.name).isNotNull
                assertThat(album.price).isNotNull
                assertThat(album.stockQuantity).isNotNull
                assertThat(album.artist).isNotNull
                assertThat(album.etc).isNotNull
            },
            {
                assertThat(album).isEqualTo(FixtureFactory.createAlbum())
            }
        )
    }

    @Test
    fun `영화 생성`() {
        val movie = FixtureFactory.createMovie()

        assertAll(
            {
                assertThat(movie.id).isNotNull
                assertThat(movie.name).isNotNull
                assertThat(movie.price).isNotNull
                assertThat(movie.stockQuantity).isNotNull
                assertThat(movie.director).isNotNull
                assertThat(movie.actor).isNotNull
            },
            {
                assertThat(movie).isEqualTo(FixtureFactory.createMovie())
            }
        )
    }

    @Test
    fun `책 생성`() {
        val book = FixtureFactory.createBook()

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
}
