package com.ovrbach.coroutinesapplied.data

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope

class Repository(
        private val local: Local = Local(),
        private val remote: Remote = Remote()
) {

    suspend fun loadData(): ReceiveChannel<List<String>> {
        val channel = Channel<List<String>>(2)

        return coroutineScope {

            val source = produce(capacity = 2) {
                val local = local.loadData()
                send(local)

                val remote = remote.loadData()
                send(remote)

            }
            source
        }

    }

    suspend fun insertData(item: String, wait: Long): Boolean {

        return true
    }


}