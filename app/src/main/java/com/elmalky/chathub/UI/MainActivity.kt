package com.elmalky.chathub.UI

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
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
        setThemeMode(storageSp.getBoolean(Constants.Names.THEME_MODE, false))
        binder.navigationDrawer.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.start_new_chat -> {
                    viewModel.startNewChat()
                    true
                }

                R.id.Garvis_voice -> {
                    editorSp.putBoolean(Constants.Names.TTS_Voice, true).apply()
                    Toast.makeText(this, "Garvis is speaking now", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.Skye_voice -> {
                    editorSp.putBoolean(Constants.Names.TTS_Voice, false).apply()
                    Toast.makeText(this, "Skye is speaking now", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.dark_mode -> {
                    editorSp.putBoolean(Constants.Names.THEME_MODE, true).apply()
                    setThemeMode(storageSp.getBoolean(Constants.Names.THEME_MODE, false))
                    true
                }

                R.id.light_mode -> {
                    editorSp.putBoolean(Constants.Names.THEME_MODE, false).apply()
                    setThemeMode(storageSp.getBoolean(Constants.Names.THEME_MODE, false))
                    true
                }

                else -> {
                    true
                }
            }
        }
    }

    private fun setThemeMode(themeMode: Boolean) {
        if (themeMode)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS)
            tts.language = Locale.UK
    }

    fun decidTTSVoice(voiceTTS: Boolean) {
        for (voice in tts.voices) {
            //male
            if (voiceTTS && voice.name.contains("en-us-x-tpd-network")) {
                tts.voice = voice

            }//female
            else if (!voiceTTS && voice.name.contains("en-us-x-tpf-network")) {
                tts.voice = voice
            }
        }
    }

    fun speakOut(view: View) {
        tts_voice = storageSp.getBoolean(Constants.Names.TTS_Voice, false)
        decidTTSVoice(tts_voice)
        tts.speak(view.tag.toString(), TextToSpeech.QUEUE_FLUSH, null)
    }

}