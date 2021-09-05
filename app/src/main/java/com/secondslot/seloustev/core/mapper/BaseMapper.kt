package com.secondslot.seloustev.core.mapper

interface BaseMapper<in A, out B> {

    fun map(type: A?): B
}