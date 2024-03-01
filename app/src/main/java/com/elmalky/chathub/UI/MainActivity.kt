package com.elmalky.chathub.UI

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.elmalky.chathub.Adapters.ChatRecyclerView
import com.elmalky.chathub.MainViewModel
import com.elmalky.chathub.R
import com.elmalky.chathub.Util.Constants
import com.elmalky.chathub.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    lateinit var binder: ActivityMainBinding
    lateinit var tts: TextToSpeech
    val viewModel: MainViewModel by viewModels()
    lateinit var storageSp: SharedPreferences
    var tts_voice = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)
        tts = TextToSpeech(this, this)
        storageSp = getSharedPreferences(Constants.Names.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editorSp = storageSp.edit()
        binder.vm = viewModel
        binder.lifecycleOwner = this
        val adapter = ChatRecyclerView(mutableListOf(), viewModel)
        binder.chatRecyclerView.adapter = adapter
        binder.menuBtn.setOnClickListener {
            binder.drawerLayout.openDrawer(binder.navigationDrawer)
        }
        tts_voice = storageSp.getBoolean(Constants.Names.TTS_Voice, false)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS)
            tts.language = Locale.UK
    }

    fun decidTTSVoice(voiceTTS: Boolean) {
        for (voice in tts.voices) {
            if (voiceTTS && voice.name.contains("en-us-x-tpd-network")) {
                tts.voice = voice
                Log.i("taggggg", "found")
            } else if (!voiceTTS && voice.name.contains("en-us-x-tpf-network")) {
                tts.voice = voice
                Log.i("taggggg", "found")
            }
        }
    }

    fun speakOut(view: View) {
        decidTTSVoice(tts_voice)
        viewModel.botChat.observe(this) {
            tts.speak(it, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

}