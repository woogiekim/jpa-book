package jpabook.jpashop.item.service

import jpabook.jpashop.exception.ErrorCode
import jpabook.jpashop.extensions.validateNotNull
import jpabook.jpashop.item.domain.Item
import jpabook.jpashop.item.domain.ItemRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface ItemService {
    fun addItem(item: Item): Item
    fun updateItem(itemId: Long, item: Item): Item
    fun getItem(itemId: Long): Item
    fun getItems(pageable: Pageable): List<Item>
}

@Service
@Transactional
class DefaultItemService(
    private val itemRepository: ItemRepository
) : ItemService {
    override fun addItem(item: Item): Item {
        return itemRepository.save(item)
    }

    override fun updateItem(itemId: Long, item: Item): Item {
        val findItem: Item = getItem(itemId)

        findItem.change(item)

        return findItem
    }

    override fun getItem(itemId: Long): Item {
        return validateNotNull(itemRepository.findByIdOrNull(itemId)) { ErrorCode.NOT_EXISTS_ITEM }
    }

    override fun getItems(pageable: Pageable): List<Item> {
        return itemRepository.findAll(pageable).content
    }
}
