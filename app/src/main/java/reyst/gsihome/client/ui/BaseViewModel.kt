package reyst.gsihome.client.ui

import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel: ViewModel() {

    private val job = Job()
    val mainScope = CoroutineScope(Dispatchers.Main + job)


    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}