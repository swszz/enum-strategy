package com.github.swszz.enumstrategy

import com.fasterxml.jackson.annotation.JsonValue
import java.lang.reflect.ParameterizedType
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

abstract class EnumString<E : Enum<E>, T : EnumString<E, T>>(
    @get:JsonValue
    override val value: String,
) : DelegatedValue<String>(value), Comparable<EnumString<E, T>> {

    @Suppress("UNCHECKED_CAST")
    private val klass: KClass<E> by lazy {
        val clazz = this::class.java
        ENUM_KLASS_CACHE.getOrPut(clazz) {
            val superclass = clazz.genericSuperclass as ParameterizedType
            val type = superclass.actualTypeArguments[0] as Class<*>
            type.kotlin as KClass<out Enum<*>>
        } as KClass<E>
    }


    fun toEnumOrNull(): E? = EnumFinder.findByNameOrNull(klass, value)

    fun toEnumOrElse(defaultValueSupplier: () -> E): E =
        toEnumOrNull() ?: defaultValueSupplier()

    fun toEnumOrThrow(
        exceptionSupplier: () -> RuntimeException = {
            IllegalStateException("\"$value\" 에 해당하는 ${klass.simpleName}을 찾을 수 없습니다.")
        }
    ): E = toEnumOrNull() ?: throw exceptionSupplier()

    override fun compareTo(other: EnumString<E, T>): Int {
        return value.compareTo(other.value)
    }

    companion object {
        private val ENUM_KLASS_CACHE = ConcurrentHashMap<Class<*>, KClass<out Enum<*>>>()

    }
}
