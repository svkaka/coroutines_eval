package com.ovrbach.coroutinesapplied.data

import com.ovrbach.coroutinesapplied.util.logd
import kotlinx.coroutines.delay
import kotlin.random.Random

class Local : Source {

    override val data: MutableList<String> = mutableListOf("L1", "L2", "L3", "L4", "L5")

    override suspend fun loadData(): List<String> {
        logd("Loading local started")
        val wait = Random.nextLong(500)
        delay(wait)
        logd("Local loading took $wait")
        logd("Loading local finished: ${data.size}, $data")
        return data
    }

    override suspend fun insertData(item: String, wait: Long): Boolean {
        logd("Inserting $item to local started")
        Thread.sleep(wait)
        data.add(item)
        logd("Inserting $item to local finished")
        return true
    }

}