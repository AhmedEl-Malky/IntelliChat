package com.elmalky.chathub.UI

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.elmalky.chathub.Adapters.ChatRecyclerView
import com.elmalky.chathub.MainViewModel
import com.elmalky.chathub.R
import com.elmalky.chathub.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    lateinit var binder: ActivityMainBinding
    lateinit var tts: TextToSpeech
    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)
        tts = TextToSpeech(this, this)
        binder.vm = viewModel
        binder.lifecycleOwner = this
        val adapter = ChatRecyclerView(mutableListOf(), viewModel)
        binder.chatRecyclerView.adapter = adapter
        binder.menuBtn.setOnClickListener {
            binder.drawerLayout.openDrawer(binder.navigationDrawer)
        }
//        viewModel.botChat.observe(this) {
//            Log.i("Tag", it)
//        }
//        viewModel.items.observe(this){
//            Log.i("Tag",it.toString())
//        }

    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS)
            tts.language = Locale.UK
    }

    fun speakOut(view: View) {
//        for (voice:Voice in tts.voices){
//                if(voice.name == "en-gb-x-rjs#male_2-local") {
//                    tts.voice = voice
//                    break
//                }
//        }
        viewModel.botChat.observe(this) {
            tts.speak(it, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

//    private fun setUpDrawer() {
//        binder.drawerLayout.addDrawerListener()
//    }
}