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

package com.hybcode.phone.ui.phone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hybcode.phone.MainActivity
import com.hybcode.phone.R
import com.hybcode.phone.databinding.FragmentPhoneBinding

class PhoneFragment : Fragment() {

    private var _binding: FragmentPhoneBinding? = null
    private lateinit var callingActivity: MainActivity
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhoneBinding.inflate(inflater, container, false)
        callingActivity = activity as MainActivity
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backspace.setOnClickListener {
            removeDigit()
        }
        binding.one.setOnClickListener {
                    addDigit("1")
                }
        binding.two.setOnClickListener {
                    addDigit("2")
                }
        binding.three.setOnClickListener {
                    addDigit("3")
                }
        binding.four.setOnClickListener {
                    addDigit("4")
                }
        binding.five.setOnClickListener {
                    addDigit("5")
                }
        binding.six.setOnClickListener {
                    addDigit("6")
                }
        binding.seven.setOnClickListener {
                    addDigit("7")
                }
        binding.eight.setOnClickListener {
                    addDigit("8")
                }
        binding.nine.setOnClickListener {
                    addDigit("9")
                }
        binding.star.setOnClickListener {
                    addDigit("*")
                }
        binding.zero.setOnClickListener {
                    addDigit("0")
                }
        binding.hash.setOnClickListener {
                    addDigit("#")
                }
        binding.callButton.setOnClickListener {
                    val number = binding.phoneNumber.text
                    if (number.isNotBlank()) callingActivity.callNumber(number.toString())
                    else Toast.makeText(callingActivity, getString(R.string.no_number), Toast.LENGTH_SHORT).show()
                }
    }

    private fun addDigit(digit: String) {
        val previousNumber = binding.phoneNumber.text.toString()
        val newNumber = previousNumber + digit
        binding.phoneNumber.text = newNumber
    }

    private fun removeDigit(){
        val previousNumber = binding.phoneNumber.text.toString()
        if (previousNumber.isEmpty()) return
        val newNumber = previousNumber.take(previousNumber.length - 1)
        binding.phoneNumber.text = newNumber
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}