package com.axel.healthjoychallenge.data.mapper

interface BaseMapper<W, M> {

    fun mapperFromWsToModel(item: W): M

    fun mapperFromListWsToModel(itemList: List<W>) : List<M>

    fun mapperFromModelToWs(item: M): W

    fun mapperFromListModelToWs(itemList: List<M>): List<W>

}

