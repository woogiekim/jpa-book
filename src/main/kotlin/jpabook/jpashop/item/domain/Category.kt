package jpabook.jpashop.item.domain

import jpabook.jpashop.jpa.BaseEntity
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.OneToOne

@Entity
class Category(
    var name: String,

    @ManyToMany
    var items: MutableList<Item> = mutableListOf(),

    @OneToOne
    var parent: Category? = null
) : BaseEntity()
