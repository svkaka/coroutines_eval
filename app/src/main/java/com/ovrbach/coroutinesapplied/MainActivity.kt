package com.ovrbach.coroutinesapplied

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ovrbach.coroutinesapplied.data.Repository
import com.ovrbach.coroutinesapplied.util.logd
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    private lateinit var repository: Repository
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = Repository()

        textView = findViewById(R.id.textView)
        findViewById<View>(R.id.reload).setOnClickListener {
            loadData()
        }
    }


    fun loadData() {
        textView.text = "LOADING"
        launch {
            repository.loadData(this).consumeEach { loaded ->
                withContext(Dispatchers.Main) {
                    logd("Presenting: ${loaded.size}, $loaded")
                    textView.text = loaded.joinToString { "$it\n" }
                }
            }
        }
    }


}
