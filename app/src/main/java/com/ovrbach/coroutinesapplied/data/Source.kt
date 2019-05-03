package com.ovrbach.coroutinesapplied.data

interface Source {

    val data: MutableList<String>

    suspend fun loadData(): List<String>

    suspend fun insertData(item: String, wait: Long): Boolean
}