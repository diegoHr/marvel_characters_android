package com.herev.diego.marvelcharacters.domain

import com.herev.diego.marvelcharacters.domain.repositories.CharactersApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AttributionDataInterceptor @Inject constructor(private val apiRepository: CharactersApiRepository) {

    private val _attribution = MutableStateFlow("")
    val attribution : StateFlow<String> get() = _attribution



    suspend fun getAttribution () {
        val charactersWrapper = apiRepository.getCharactersPage(0, 1)
        charactersWrapper.attributionHTML?.let {
            _attribution.emit(it)
        }

    }
}