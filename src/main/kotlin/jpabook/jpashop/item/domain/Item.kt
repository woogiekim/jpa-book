package jpabook.jpashop.item.domain

import jpabook.jpashop.exception.ErrorCode
import jpabook.jpashop.extensions.validateNotNull
import jpabook.jpashop.jpa.BaseEntity
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
abstract class Item(
    var name: String? = null,
    var price: Int? = null,
    var stockQuantity: Int? = null,

    @ManyToMany
    var categories: MutableList<Category> = mutableListOf()
) : BaseEntity() {
    fun change(item: Item) {
        validate()

        this.name = item.name
        this.price = item.price
        this.stockQuantity = item.stockQuantity
    }

    @PrePersist
    fun prePersist() {
        validate()
    }

    private fun validate() {
        validateNotNull(name) { ErrorCode.REQUIRED }
        validateNotNull(price) { ErrorCode.REQUIRED }
        validateNotNull(stockQuantity) { ErrorCode.REQUIRED }
    }
}

@Entity
@DiscriminatorValue("A")
class Album : Item() {
    lateinit var artist: String
    var etc: String? = null

    companion object {
        fun create(name: String, price: Int, stockQuantity: Int, artist: String, etc: String? = null): Album {
            return Album().apply {
                this.name = name
                this.price = price
                this.stockQuantity = stockQuantity
                this.artist = artist
                this.etc = etc
            }
        }
    }
}

@Entity
@DiscriminatorValue("M")
class Movie : Item() {
    lateinit var director: String
    lateinit var actor: String

    companion object {
        fun create(name: String, price: Int, stockQuantity: Int, director: String, actor: String): Movie {
            return Movie().apply {
                this.name = name
                this.price = price
                this.stockQuantity = stockQuantity
                this.director = director
                this.actor = actor
            }
        }
    }
}

@Entity
@DiscriminatorValue("B")
class Book : Item() {
    lateinit var author: String
    lateinit var isbn: String

    fun change(book: Book) {
        super.change(book)

        this.author = book.author
        this.isbn = book.isbn
    }

    companion object {
        fun create(name: String, price: Int, stockQuantity: Int, author: String, isbn: String): Book {
            return Book().apply {
                this.name = name
                this.price = price
                this.stockQuantity = stockQuantity
                this.author = author
                this.isbn = isbn
            }
        }
    }
}
