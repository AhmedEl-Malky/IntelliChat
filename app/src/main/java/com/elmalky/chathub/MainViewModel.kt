package com.elmalky.chathub

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmalky.chathub.Models.Repositories.GeminiRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val geminiRepository = GeminiRepository()
    val userChat = MutableLiveData<String>()
    val botChat = MutableLiveData<String>()


    fun getResponse(prompt: String) {
        viewModelScope.launch {
            botChat.postValue(geminiRepository.makeRequest(prompt))
        }
    }
}