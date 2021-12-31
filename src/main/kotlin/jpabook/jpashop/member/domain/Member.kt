package jpabook.jpashop.member.domain

import jpabook.jpashop.jpa.Address
import jpabook.jpashop.jpa.BaseEntity
import javax.persistence.Embedded
import javax.persistence.Entity

@Entity
class Member(
    var name: String,

    @Embedded
    var address: Address,
) : BaseEntity()
