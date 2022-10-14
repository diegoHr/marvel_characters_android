package com.herev.diego.marvelcharacters.domain

import com.herev.diego.marvelcharacters.domain.repositories.ICharactersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AttributionDataInterceptor @Inject constructor(private val apiRepository: ICharactersRepository) {

    private val _attribution = MutableStateFlow("")
    val attribution : StateFlow<String> get() = _attribution



    suspend fun getAttribution () {
        val charactersWrapper = apiRepository.getCharactersPage(0, 1)
        charactersWrapper.attributionHTML?.let {
            _attribution.emit(it)
        }

    }
}