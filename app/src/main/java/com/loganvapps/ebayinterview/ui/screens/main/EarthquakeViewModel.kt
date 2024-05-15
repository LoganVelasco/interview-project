package com.loganvapps.ebayinterview.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loganvapps.ebayinterview.data.models.Earthquake
import com.loganvapps.ebayinterview.data.models.Earthquakes
import com.loganvapps.ebayinterview.data.repos.EarthquakeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EarthquakeViewModel(private val repository: EarthquakeRepository = EarthquakeRepository(), private val dispatcher: CoroutineDispatcher = Dispatchers.IO): ViewModel() {

    private val _state: MutableStateFlow<EarthquakeState> = MutableStateFlow(EarthquakeState.Loading)
    val state: StateFlow<EarthquakeState> = _state

    init {
        getEarthquakes()
    }

    fun getEarthquakes() {
        _state.value = EarthquakeState.Loading
        viewModelScope.launch(dispatcher){
            val response = repository.getEarthquakes()

            if(response != null) {
                _state.value = EarthquakeState.Success(response)
            } else {
                _state.value = EarthquakeState.Error("Error")
            }
        }
    }

}

sealed class EarthquakeState {
    data object Loading : EarthquakeState()
    data class Success(val data: Earthquakes) : EarthquakeState()
    data class Error(val message: String) : EarthquakeState()
}
