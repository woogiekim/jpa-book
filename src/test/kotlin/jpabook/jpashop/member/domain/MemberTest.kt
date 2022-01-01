package jpabook.jpashop.member.domain

import jpabook.jpashop.fixture.FixtureFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class MemberTest {
    @Test
    internal fun `사용자 생성`() {
        val member = FixtureFactory.createMember()

        assertAll(
            {
                assertThat(member.id).isNotNull
                assertThat(member.name).isNotNull
                assertThat(member.address).isNotNull
            },
            {
                assertThat(member).isEqualTo(FixtureFactory.createMember())
            }
        )
    }
}
