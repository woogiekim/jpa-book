@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")
@file:OptIn(ExperimentalContracts::class)

package jpabook.jpashop.extensions

import jpabook.jpashop.exception.ErrorCode
import jpabook.jpashop.exception.JpaBookException
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

inline fun validate(value: Boolean, errorCode: () -> ErrorCode) {
    contract {
        returns() implies value
    }

    if (!value) {
        throw JpaBookException(errorCode())
    }
}

inline fun <T : Any> validateNotNull(value: T?, errorCode: () -> ErrorCode): T {
    contract {
        returns() implies (value != null)
    }

    return value?.let { value } ?: throw JpaBookException(errorCode())
}
