package jpabook.jpashop.extensions

import jpabook.jpashop.exception.ErrorCode
import jpabook.jpashop.exception.JpaBookException
import org.assertj.core.api.AbstractThrowableAssert
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.assertj.core.api.ThrowableAssert

fun assertThatJpaBookException(shouldRaiseThrowable: ThrowableAssert.ThrowingCallable): AbstractThrowableAssert<*, out Throwable> {
    return assertThatThrownBy(shouldRaiseThrowable).isExactlyInstanceOf(JpaBookException::class.java)
}

fun AbstractThrowableAssert<*, out Throwable>.errorCode(errorCode: ErrorCode) {
    extracting("errorCode").isEqualTo(errorCode)
}
