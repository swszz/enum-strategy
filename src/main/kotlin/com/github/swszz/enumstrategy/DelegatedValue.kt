package com.github.swszz.enumstrategy

open class DelegatedValue<T>(open val value: T) {
    override fun toString(): String = value.toString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DelegatedValue<*>) return false
        return value == other.value
    }

    override fun hashCode(): Int = value?.hashCode() ?: 0
}