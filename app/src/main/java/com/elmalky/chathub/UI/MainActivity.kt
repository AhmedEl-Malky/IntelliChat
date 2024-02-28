package com.elmalky.chathub.UI

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.elmalky.chathub.Adapters.ChatRecyclerView
import com.elmalky.chathub.MainViewModel
import com.elmalky.chathub.R
import com.elmalky.chathub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binder: ActivityMainBinding
    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)
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

//    private fun setUpDrawer() {
//        binder.drawerLayout.addDrawerListener()
//    }
}