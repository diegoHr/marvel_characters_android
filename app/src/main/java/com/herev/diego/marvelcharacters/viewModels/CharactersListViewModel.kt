package com.herev.diego.marvelcharacters.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herev.diego.marvelcharacters.domain.AttributionDataInterceptor
import com.herev.diego.marvelcharacters.domain.CharactersDataLake
import com.herev.diego.marvelcharacters.domain.model.Character
import com.herev.diego.marvelcharacters.domain.repositories.RepositoryException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(private val dataLake : CharactersDataLake,
                                                  private val attributionInterceptor : AttributionDataInterceptor) : ViewModel() {

    private val _loadingMoreCharacters = MutableSharedFlow<Boolean>()

    val loadingMoreCharacters : SharedFlow<Boolean> get() = _loadingMoreCharacters
    val isListCompleted : Boolean get() = dataLake.isListCompleted
    var page = 0
    var characters = mutableListOf<Character>()

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage : SharedFlow<String> get() = _errorMessage

    val attributionData : StateFlow<String> get() = attributionInterceptor.attribution


    fun getAttributionData () {
        viewModelScope.launch {
            try {
                attributionInterceptor.getAttribution()
            }catch (repositoryException : RepositoryException){
                repositoryException.message?.let {
                    _errorMessage.emit(it)

                }
            }
        }
    }

    fun getMoreCharacters () {
        viewModelScope.launch {
            _getMoreCharacters()
        }
    }

    suspend fun _getMoreCharacters () {
        try {
            characters.addAll(dataLake.getPage(page))
            page++
            _loadingMoreCharacters.emit(true)
        }catch (repositoryException : RepositoryException){
            repositoryException.message?.let {
                _errorMessage.emit(it)
            }
        }
    }




}