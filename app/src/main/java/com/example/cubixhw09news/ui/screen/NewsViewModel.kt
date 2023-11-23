package com.example.cubixhw09news.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cubixhw09news.data.NewsResult
import com.example.cubixhw09news.network.NewsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed interface NewsUiState {
    object Init : NewsUiState
    object Loading : NewsUiState
    data class Success(val news: NewsResult) : NewsUiState
    data class Error(val errorMsg: String) : NewsUiState
}

@HiltViewModel
class NewsViewModel @Inject constructor(
    val newsAPI: NewsApi
) : ViewModel() {

    var newsUiState: NewsUiState by mutableStateOf(NewsUiState.Init)


    fun getNews() {
        newsUiState = NewsUiState.Loading
        viewModelScope.launch {
            newsUiState = try {
                val result = newsAPI.getNews("hu",
                    "3337a896861a4234bf1ca9f2bab6f4ba")
                NewsUiState.Success(result)
            } catch (e: Exception) {
                NewsUiState.Error(e.message!!)
            }
        }
    }

}