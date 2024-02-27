package com.elmalky.chathub.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elmalky.chathub.R
import com.elmalky.chathub.databinding.ItemBotChatBinding
import com.elmalky.chathub.databinding.ItemUserChatBinding

class ChatRecyclerView(var chatItems: MutableList<String>) :
    RecyclerView.Adapter<ChatRecyclerView.BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when (viewType) {
            BOT_CHAT_ITEM -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_bot_chat, parent, false)
                return BotChatHolder(view)
            }

            USER_CHAT_ITEM -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_user_chat, parent, false)
                return UserChatHolder(view)
            }

            else -> return super.createViewHolder(parent, viewType)
        }
    }

    override fun getItemCount() = chatItems.size
    fun setItem(newItems: MutableList<String>) {
        chatItems = newItems
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentText = chatItems[position]
        when (holder) {
            is UserChatHolder -> holder.userChatBinder.userText = currentText
            is BotChatHolder -> holder.botChatBinder.botText = currentText
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) USER_CHAT_ITEM else BOT_CHAT_ITEM
    }

    open class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {}
    class UserChatHolder(view: View) : BaseViewHolder(view) {
        val userChatBinder = ItemUserChatBinding.bind(view)
    }

    class BotChatHolder(view: View) : BaseViewHolder(view) {
        val botChatBinder = ItemBotChatBinding.bind(view)
    }

    companion object {
        val USER_CHAT_ITEM = 1
        val BOT_CHAT_ITEM = 2
    }
}