package com.example.mercadolibre.common

sealed class Either<out L, out R> {

    data class Left<out L>(val l: L) : Either<L, Nothing>()

    data class Right<out R>(val r: R) : Either<Nothing, R>()

    fun <T> fold(left: (L) -> T, right: (R) -> T): T {
        return when (this) {
            is Left -> left(l)
            is Right -> right(r)
        }
    }
    fun handle(left: (L) -> Unit, right: (R) -> Unit) {
        when (this) {
            is Left -> left(l)
            is Right -> right(r)
        }
    }

}
