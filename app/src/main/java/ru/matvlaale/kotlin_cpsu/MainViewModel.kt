package ru.matvlaale.kotlin_cpsu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var model: IModel = TimeGetter()
    private val liveData = MutableLiveData<String>()

    fun btnShowDateTime(){
        val data = model.getTime()
        liveData.postValue(data)
    }

    fun getLiveData(): LiveData<String> = liveData
}