package com.hybcode.phone.ui.sms

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.hybcode.phone.MainActivity
import com.hybcode.phone.databinding.SendSmsBinding

class SendSMS(private val number: String?) : DialogFragment() {

    private var _binding: SendSmsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val callingActivity = activity as MainActivity
        val inflater = callingActivity.layoutInflater
        _binding = SendSmsBinding.inflate(inflater)
        val builder = AlertDialog.Builder(callingActivity)
            .setView(binding.root)

        if (number != null) {
            val editable: Editable = SpannableStringBuilder(number)
            binding.number.text = editable
        }

        binding.cancelBtn.setOnClickListener {
                    dismiss()
                }

        binding.sendBtn.setOnClickListener {
                    if (callingActivity.sendSMS(binding.number.text.toString(), binding.body.text.toString())) dismiss()
                }
        return builder.create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}