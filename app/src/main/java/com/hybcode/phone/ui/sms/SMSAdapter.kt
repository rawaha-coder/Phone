package com.hybcode.phone.ui.sms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hybcode.phone.MainActivity
import com.hybcode.phone.R
import com.hybcode.phone.SMS

class SMSAdapter(private val activity: MainActivity) : RecyclerView.Adapter<SMSAdapter.SMSViewHolder>() {

    var texts = listOf<SMS>()

    inner class SMSViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        internal var mSender = itemView.findViewById<View>(R.id.sender) as TextView
        internal var mBody = itemView.findViewById<View>(R.id.body) as TextView
        init {
            itemView.isClickable = true
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener{
                activity.showSMSPopup(it, texts[adapterPosition])
                return@setOnLongClickListener true
            }
        }
        override fun onClick(view: View) = activity.openDialog(ViewSMS(texts[adapterPosition]))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SMSAdapter.SMSViewHolder {
        return SMSViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.sms_entry, parent, false))
    }

    override fun onBindViewHolder(holder: SMSViewHolder, position: Int) {
        val current = texts[position]
        holder.mSender.text = current.sender
        holder.mBody.text = current.body
    }

    override fun getItemCount() = texts.size

}