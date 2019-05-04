package com.ovrbach.coroutinesapplied.data

import com.ovrbach.coroutinesapplied.util.logd
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch

class Repository(
        private val local: Local = Local(),
        private val remote: Remote = Remote()
) {

    suspend fun loadData(scope: CoroutineScope): ReceiveChannel<List<String>> {
        val channel = BroadcastChannel<List<String>>(2)

        return scope.produce(capacity = 2) {
            launch {
                val localData = local.loadData()
                send(localData)
            }
            launch {
                val remoteData = remote.loadData()
                send(remoteData)
            }
            invokeOnClose {
                logd("Closing channel")
            }
        }
    }

    suspend fun insertData(item: String, wait: Long): Boolean {

        return true

    }
}


