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

package com.hybcode.phone.ui.callLog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hybcode.phone.CommunicationViewModel
import com.hybcode.phone.MainActivity
import com.hybcode.phone.databinding.FragmentCallLogBinding

class CallLogFragment : Fragment() {

    private var _binding: FragmentCallLogBinding? = null
    private lateinit var callingActivity: MainActivity
    private lateinit var callLogAdapter: CallLogAdapter
    private val binding get() = _binding!!
    private val communicationViewModel: CommunicationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCallLogBinding.inflate(inflater, container, false)
        callingActivity = activity as MainActivity
        callLogAdapter = CallLogAdapter(callingActivity)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {super.onViewCreated(view, savedInstanceState)
        binding.root.layoutManager = LinearLayoutManager(activity)
        binding.root.adapter = callLogAdapter
        callingActivity.getCallLogs()
        communicationViewModel.callLog.observe(viewLifecycleOwner) { log ->
            log?.let {
                callLogAdapter.callLog = it.take(10)
                callLogAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}