package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard(){
    val inputManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, HIDE_NOT_ALWAYS)
}

fun Activity.getKeyboardHeight():Int{
    val r = Rect()
    val rootview =  window.decorView
    rootview.getWindowVisibleDisplayFrame(r)
    val screenHeight = rootview.rootView.height
    return  screenHeight - (r.bottom + r.top)
}

fun Activity.isKeyboardOpen():Boolean{
    return getKeyboardHeight() > 100
}

fun Activity.isKeyboardClosed():Boolean{
    return getKeyboardHeight() <= 100
}