package co.yml.ychatgpt.android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading get() = _isLoading

    fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

}