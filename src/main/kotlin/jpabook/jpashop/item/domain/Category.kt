package jpabook.jpashop.item.domain

import jpabook.jpashop.jpa.BaseEntity
import javax.persistence.Entity
import javax.persistence.OneToOne

@Entity
class Category(
    var name: String,

    @OneToOne
    var parent: Category? = null
) : BaseEntity()
