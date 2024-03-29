package com.elmalky.chathub

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmalky.chathub.Models.Repositories.GeminiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val geminiRepository = GeminiRepository()
    val userChat = MutableLiveData<String>()
    val botChat = MutableLiveData(" ")
    val items = MutableLiveData<MutableList<String>>()
    var botText = "       "

    //    Hello , chathub i need help , can you help me as i need from you to write a code about how to access item at specific index in map in kotlin language
    suspend fun getResponse(prompt: String) {
        botText = geminiRepository.makeRequest(prompt)
        botChat.postValue(botText)
    }

    fun startNewChat() {
        geminiRepository.clearItems()
        items.postValue(geminiRepository._chatItems)
    }

    fun addChatItemsToItemsList(userText: String?) {
        if (userText != null) {
            botText = "       "
            geminiRepository.addChatItem(userText)
            items.postValue(geminiRepository._chatItems)
            geminiRepository.addChatItem(botText)
            items.postValue(geminiRepository._chatItems)
            botChat.postValue("       ")
            userChat.postValue(null)
            viewModelScope.launch(Dispatchers.IO) {
                val job = launch {
                    getResponse(userText)
                }
                job.join()
                geminiRepository.modifyBotChat(botText)
                botChat.postValue(botText)
                items.postValue(geminiRepository._chatItems)
            }

        }
    }

}