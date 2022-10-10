package com.hybcode.phone.ui.sms

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.hybcode.phone.MainActivity
import com.hybcode.phone.SMS
import com.hybcode.phone.databinding.ViewSmsBinding

class ViewSMS(private val sms: SMS) : DialogFragment() {

    private var _binding: ViewSmsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val callingActivity = activity as MainActivity
        val inflater = callingActivity.layoutInflater
        _binding = ViewSmsBinding.inflate(inflater)
        val builder = AlertDialog.Builder(callingActivity)
            .setView(binding.root)
        binding.sender.text = sms.sender
        binding.body.text = sms.body
        binding.cancelBtn.setOnClickListener {
            dismiss()
        }
        binding.replyBtn.setOnClickListener {
                    callingActivity.openDialog(SendSMS(sms.sender))
                    dismiss()
                }
        return builder.create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}