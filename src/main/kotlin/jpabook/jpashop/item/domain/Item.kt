package jpabook.jpashop.item.domain

import jpabook.jpashop.jpa.BaseEntity
import javax.persistence.DiscriminatorColumn
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.Inheritance
import javax.persistence.InheritanceType

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
abstract class Item(
    var name: String? = null,
    var price: Int? = null,
    var stockQuantity: Int? = null
) : BaseEntity()

@Entity
@DiscriminatorValue("A")
class Album(
    var artist: String,
    var etc: String? = null
) : Item()

@Entity
@DiscriminatorValue("M")
class Movie(
    var director: String,
    var actor: String
) : Item()

@Entity
@DiscriminatorValue("B")
class Book(
    var author: String,
    var isbn: String
) : Item()
