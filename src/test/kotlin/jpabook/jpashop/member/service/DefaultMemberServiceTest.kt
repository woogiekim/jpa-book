package jpabook.jpashop.member.service

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import jpabook.jpashop.exception.ErrorCode.ALREADY_EXISTS_MEMBER
import jpabook.jpashop.exception.ErrorCode.NOT_EXISTS_MEMBER
import jpabook.jpashop.extensions.assertThatJpaBookException
import jpabook.jpashop.extensions.errorCode
import jpabook.jpashop.fixture.FixtureFactory
import jpabook.jpashop.member.domain.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.repository.findByIdOrNull

@ExtendWith(MockKExtension::class)
class DefaultMemberServiceTest {
    @MockK
    lateinit var memberRepository: MemberRepository

    lateinit var memberService: MemberService

    private val member = FixtureFactory.createMember()

    @BeforeEach
    fun setUp() {
        memberService = DefaultMemberService(memberRepository)
    }

    @Test
    fun `회원 등록`() {
        every { memberRepository.findByName(member.name) } returns null
        every { memberRepository.save(member) } returns member

        val actualMember = memberService.addMember(member)

        assertThat(actualMember).isEqualTo(member)

        verify(exactly = 1) { memberRepository.save(member) }
    }

    @Test
    fun `이미 등록 된 회원이면 예외 발생`() {
        every { memberRepository.findByName(member.name) } returns member

        assertThatJpaBookException { memberService.addMember(member) }.errorCode(ALREADY_EXISTS_MEMBER)

        verify(exactly = 1) { memberRepository.findByName(member.name) }
    }

    @Test
    fun `회원 조회`() {
        every { memberRepository.findByIdOrNull(member.id!!) } returns member

        val actualMember = memberService.getMember(member.id!!)

        assertThat(actualMember).isEqualTo(member)

        verify(exactly = 1) { memberRepository.findByIdOrNull(member.id!!) }
    }

    @Test
    fun `없는 회원을 조회하려고 하면 예외 발생`() {
        every { memberRepository.findByIdOrNull(member.id!!) } returns null

        assertThatJpaBookException { memberService.getMember(member.id!!) }.errorCode(NOT_EXISTS_MEMBER)

        verify(exactly = 1) { memberRepository.findByIdOrNull(member.id!!) }
    }
}
