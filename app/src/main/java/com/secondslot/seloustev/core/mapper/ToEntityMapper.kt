package com.secondslot.seloustev.core.mapper

interface ToEntityMapper<in A, out B> {

    fun map(type: A?, category: String, startIndex: Int): B
}