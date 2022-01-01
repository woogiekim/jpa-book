package jpabook.jpashop.member.service

import jpabook.jpashop.exception.ErrorCode
import jpabook.jpashop.extensions.validate
import jpabook.jpashop.extensions.validateNotNull
import jpabook.jpashop.member.domain.Member
import jpabook.jpashop.member.domain.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface MemberService {
    fun addMember(member: Member): Member

    fun getMember(memberId: Long): Member
}

@Service
@Transactional
class DefaultMemberService(
    private val memberRepository: MemberRepository
) : MemberService {
    override fun addMember(member: Member): Member {
        validate(memberRepository.findByName(member.name) == null) { ErrorCode.ALREADY_EXISTS_MEMBER }

        return memberRepository.save(member)
    }

    override fun getMember(memberId: Long): Member {
        return validateNotNull(memberRepository.findByIdOrNull(memberId)) { ErrorCode.NOT_EXISTS_MEMBER }
    }
}
