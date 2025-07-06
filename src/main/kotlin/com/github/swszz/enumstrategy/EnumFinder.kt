package com.github.swszz.enumstrategy

import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

object EnumFinder {

    @Suppress("UNCHECKED_CAST")
    fun <E : Enum<E>> findByNameOrNull(klass: KClass<E>, name: String): E? {
        if (name.isBlank()) return null
        val enumEntries = ENUM_CACHE.computeIfAbsent(klass) { kClazz ->
            kClazz.java.enumConstants.associateBy { it.name.uppercase() }
        }
        return enumEntries[name.uppercase()] as E?
    }

    private val ENUM_CACHE = ConcurrentHashMap<KClass<out Enum<*>>, Map<String, Enum<*>>>()
}
