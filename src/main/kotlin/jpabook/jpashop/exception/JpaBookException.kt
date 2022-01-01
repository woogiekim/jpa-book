package jpabook.jpashop.exception

class JpaBookException(val errorCode: ErrorCode = ErrorCode.UNKNOWN) : RuntimeException()

enum class ErrorCode(
    val code: Int,
    val message: String
) {
    UNKNOWN(1, "unknown.error"),
    REQUIRED(2, "required"),

    NOT_EXISTS_MEMBER(100, "not.exists.member"),
    ALREADY_EXISTS_MEMBER(101, "already.exists.member"),

    NOT_EXISTS_ITEM(200, "not.exists.item"),
}
