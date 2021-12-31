package jpabook.jpashop.exception

class JpaBookException(val errorCode: ErrorCode = ErrorCode.UNKNOWN) : RuntimeException()

enum class ErrorCode(
    val code: Int,
    val message: String
) {
    UNKNOWN(1, "unknown.error");
}
