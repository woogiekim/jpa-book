package jpabook.jpashop.item.domain

import jpabook.jpashop.fixture.FixtureFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CategoryTest {
    @Test
    fun `카테고리 생성`() {
        val category = FixtureFactory.createCategory()

        assertThat(category.id).isNotNull
        assertThat(category).isEqualTo(FixtureFactory.createCategory())
    }
}
