package jpabook.jpashop.member.domain

import jpabook.jpashop.jpa.Address
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemberTest {
    @Test
    internal fun `사용자 생성`() {
        val member = Member("김태욱", Address("인천시", "참외전로", "1-400")).apply { id = 1 }

        assertThat(member).isEqualTo(Member("김태욱", Address("인천시", "참외전로", "1-400")).apply { id = 1 })
    }
}
