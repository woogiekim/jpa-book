package jpabook.jpashop.jpa

import javax.persistence.Embeddable

@Embeddable
class Address(
    var city: String,
    var street: String,
    var zipcode: String
)
