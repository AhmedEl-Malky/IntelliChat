package com.elmalky.chathub.Adapters

import android.content.Context
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter(value = ["recyclerItems"])
fun setRecyclerItems(view: RecyclerView, items: MutableList<String>?) {
    if (items != null)
        (view.adapter as ChatRecyclerView).setItem(items)
    else
        (view.adapter as ChatRecyclerView).setItem(emptyList<String>().toMutableList())
}

@BindingAdapter(value = ["hideInputMethod"])
fun hideKeyboard(view: View, txt: String?) {
    if (txt == null) {
        (view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(view.windowToken, 0)
    }
}

private var i = 0

@BindingAdapter(value = ["textAnimation"])
fun animateText(view: View, text: String) {
    view as TextView
    if (i <= text.length) {
        val newText = text.substring(0, i)
        view.text = newText
        Handler().postDelayed({
            i++
            animateText(view, text)
        }, 20)
    }
}

@BindingAdapter(value = ["stateLoading"])
fun showLoadingAnimation(view: View, text: String?) {
    if (text != null) {
        if (text == " ")
            view.visibility = View.VISIBLE
        else
            view.visibility = View.INVISIBLE
    }
}