package com.example.actioninputspranto.customdialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.actioninputspranto.R

class CustomAlertDialog(
    private val icon:Int = R.drawable.ic_baseline_info_24,
    private val title: String,
    private val body:String,
    private val positiveButtonText : String,
    private val negativeButtonText:String,
    val positiveButtonCallback:() -> Unit
) :DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity()).apply {
            setTitle(title)
            setMessage(body)
            setIcon(icon)
            setPositiveButton(positiveButtonText) { dialogInterface, i ->
                positiveButtonCallback()
            }
            setNegativeButton(negativeButtonText, null)
        }
        return builder.create()
    }
}