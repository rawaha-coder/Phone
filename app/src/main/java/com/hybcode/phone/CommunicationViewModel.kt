package com.hybcode.phone

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommunicationViewModel : ViewModel() {
    var callLog = MutableLiveData<List<CallLogEvent>>()
    var texts = MutableLiveData<List<SMS>>()
}