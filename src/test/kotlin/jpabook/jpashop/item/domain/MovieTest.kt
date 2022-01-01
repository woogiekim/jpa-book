package jpabook.jpashop.item.domain

import jpabook.jpashop.fixture.FixtureFactory
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class MovieTest {
    private val movie = FixtureFactory.createMovie()

    @Test
    fun `영화 생성`() {
        assertAll(
            {
                Assertions.assertThat(movie.id).isNotNull
                Assertions.assertThat(movie.name).isNotNull
                Assertions.assertThat(movie.price).isNotNull
                Assertions.assertThat(movie.stockQuantity).isNotNull
                Assertions.assertThat(movie.director).isNotNull
                Assertions.assertThat(movie.actor).isNotNull
            },
            {
                Assertions.assertThat(movie).isEqualTo(FixtureFactory.createMovie())
            }
        )
    }
}
