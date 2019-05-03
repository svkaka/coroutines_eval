package com.ovrbach.coroutinesapplied.data

import com.ovrbach.coroutinesapplied.util.logd
import kotlin.random.Random

class Remote : Source {

    override val data: MutableList<String> = mutableListOf("R1", "R2", "R3", "R4", "R5")

    override suspend fun loadData(): List<String> {
        logd("Loading remote started")
        val wait = Random.nextLong(0, 500)
        Thread.sleep(wait)
        logd("Loading took $wait")
        logd("Loading remote finished: ${data.size}, $data")
        return data
    }

    override suspend fun insertData(item: String, wait: Long): Boolean {
        logd("Inserting $item to remote started")
        Thread.sleep(wait)
        data.add(item)
        logd("Inserting $item to remote finished")
        return true
    }

}