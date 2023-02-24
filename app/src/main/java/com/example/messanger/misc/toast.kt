package com.example.messanger.misc

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.messanger.R

fun showShortToast(context: Context, string: String) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
}

fun showSuccessToast(context: Context) = showShortToast(context, context.getString(R.string.success))

fun showErrorToast(context: Context) = showShortToast(context, context.getString(R.string.error))

fun showWrongInputToast(context: Context) = showShortToast(context, context.getString(R.string.wrong_input))