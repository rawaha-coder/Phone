/*
 *
 *  * Created by Rawaha Coder on 10/10/22, 3:37 PM
 *  * Copyright Ⓒ 2022 http://hybcode.com/
 *  * Last modified 10/10/22, 3:08 PM
 *  *
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  *
 *  * The above copyright notice and this permission notice shall be included in
 *  * all copies or substantial portions of the Software.
 *  *
 *  * This project and source code may use libraries or frameworks that are
 *  * released under various Open-Source licenses. Use of those libraries and
 *  * frameworks are governed by their own individual licenses.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  * THE SOFTWARE.
 *
 */

package com.hybcode.phone.ui.sms

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hybcode.phone.CommunicationViewModel
import com.hybcode.phone.MainActivity
import com.hybcode.phone.databinding.FragmentSmsBinding

class SMSFragment : Fragment() {

    private var _binding: FragmentSmsBinding? = null
    private val binding get() = _binding!!
    private val communicationViewModel: CommunicationViewModel by activityViewModels()
    private lateinit var callingActivity: MainActivity
    private lateinit var smsAdapter: SMSAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSmsBinding.inflate(inflater, container, false)
        callingActivity = activity as MainActivity
        smsAdapter = SMSAdapter(callingActivity)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.callLog.layoutManager = LinearLayoutManager(activity)
        binding.callLog.adapter = smsAdapter
        callingActivity.getTexts()
        communicationViewModel.texts.observe(viewLifecycleOwner) { texts ->
            texts?.let {
                smsAdapter.texts = it
                smsAdapter.notifyDataSetChanged()
            }
        }
        binding.fab.setOnClickListener {
            callingActivity.openDialog(SendSMS(null))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}