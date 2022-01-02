package jpabook.jpashop.jpa

import org.springframework.data.domain.AfterDomainEventPublication
import org.springframework.data.domain.DomainEvents
import java.util.*
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseAggregateRoot<A : BaseAggregateRoot<A>> : BaseEntity() {
    @org.springframework.data.annotation.Transient
    @Transient
    private val domainEvents: MutableList<Any> = ArrayList()

    @DomainEvents
    protected fun domainEvents(): Collection<Any>? {
        return Collections.unmodifiableList(domainEvents)
    }

    @AfterDomainEventPublication
    protected fun clearDomainEvents() {
        domainEvents.clear()
    }

    protected fun <T : Any> registerEvent(event: T): T {
        return event.also {
            domainEvents.add(it)
        }
    }

    protected fun andEvent(event: Any): A {
        registerEvent(event)

        @Suppress("UNCHECKED_CAST")
        return this as A
    }
}
