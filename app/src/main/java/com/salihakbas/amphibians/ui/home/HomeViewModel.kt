package com.salihakbas.amphibians.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.salihakbas.amphibians.Application
import com.salihakbas.amphibians.data.model.Amphibians
import com.salihakbas.amphibians.data.repository.AmphibiansRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface UiState {
    data class Success(val data: List<Amphibians>) : UiState
    object Error : UiState
    object Loading : UiState
}

class HomeViewModel(private val amphibiansRepository: AmphibiansRepository) : ViewModel() {
    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set


    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            uiState = UiState.Loading
            uiState = try {
                UiState.Success(
                    amphibiansRepository.getAmphibiansData()
                )
            } catch (e: IOException) {
                UiState.Error
            } catch (e: HttpException) {
                UiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as Application)
                val amphibiansRepository = application.container.amphibiansRepository
                HomeViewModel(amphibiansRepository = amphibiansRepository)
            }
        }
    }
}