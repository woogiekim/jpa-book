package jpabook.jpashop.fixture

import jpabook.jpashop.delivery.domain.Delivery
import jpabook.jpashop.item.domain.*
import jpabook.jpashop.jpa.Address
import jpabook.jpashop.member.domain.Member
import jpabook.jpashop.order.domain.Order
import jpabook.jpashop.order.domain.OrderItem

class FixtureFactory {
    companion object {
        fun createMember(): Member {
            return Member("김태욱", Address("인천시", "참외전로", "1-400")).apply { id = 1 }
        }

        fun createOrder(member: Member): Order {
            return Order(member).apply { id = 1 }
        }

        fun createOrderItem(item: Item, order: Order, count: Int): OrderItem {
            return OrderItem(item, order, count).apply { id = 1 }
        }

        fun createDelivery(order: Order): Delivery {
            return Delivery(order, Address("인천시", "참외전로", "1-400")).apply { id = 1 }
        }

        fun createCategory(): Category {
            return Category("IT").apply { id = 1 }
        }

        fun createAlbum(): Album {
            return Album.create("임창정 1집", 15_000, 100, "임창정", "30주년 특별 한정판").apply { id = 1 }
        }

        fun createMovie(): Movie {
            return Movie.create("스파이더맨 홈커밍", 10_000, 1_000_000, "존 와츠", "톰 홀랜드").apply { id = 2 }
        }

        fun createBook(): Book {
            return Book.create("자바 ORM 표준 JPA 프로그래밍", 35_000, 10_000_000, "김영한", "ISBN 000-000-0000").apply { id = 1 }
        }
    }
}
