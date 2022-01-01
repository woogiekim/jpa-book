package jpabook.jpashop.item.domain

import jpabook.jpashop.fixture.FixtureFactory
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class AlbumTest {
    private val album = FixtureFactory.createAlbum()

    @Test
    fun `앨범 생성`() {
        assertAll(
            {
                Assertions.assertThat(album.id).isNotNull
                Assertions.assertThat(album.name).isNotNull
                Assertions.assertThat(album.price).isNotNull
                Assertions.assertThat(album.stockQuantity).isNotNull
                Assertions.assertThat(album.artist).isNotNull
                Assertions.assertThat(album.etc).isNotNull
            },
            {
                Assertions.assertThat(album).isEqualTo(FixtureFactory.createAlbum())
            }
        )
    }
}
