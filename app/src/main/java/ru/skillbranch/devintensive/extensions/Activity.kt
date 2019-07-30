package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.view.View
import android.graphics.Rect
import ru.skillbranch.devintensive.utils.Utils.convertDpToPx


/**
 * Функция скрывает Software Keyboard
 */
fun Activity.hideKeyboard(){
    val focus = this.currentFocus
    focus?.let {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.let {
            it.hideSoftInputFromWindow(focus.windowToken, 0)
        }
    }
}

/**
 *
 */

fun Activity.getRootView(): View {
    return findViewById<View>(android.R.id.content)
}

/**
 * Функция проверяет открыта ли Software Keyboard
 */
fun Activity.isKeyboardOpen(): Boolean{
    val rootView = getRootView()
    val visibleBounds = Rect()
    rootView.getWindowVisibleDisplayFrame(visibleBounds)
    val heightDiff = rootView.height - visibleBounds.height()
    val marginOfError = convertDpToPx(this,50)

    return heightDiff > marginOfError
}

/**
 * Функция проверяет закрыта ли Software Keyboard
 */

fun Activity.isKeyboardClosed(): Boolean {
    return this.isKeyboardOpen().not()
}