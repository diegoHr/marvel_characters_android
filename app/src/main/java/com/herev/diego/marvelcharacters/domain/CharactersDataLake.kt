package com.herev.diego.marvelcharacters.domain

import com.herev.diego.marvelcharacters.domain.model.Character
import com.herev.diego.marvelcharacters.domain.repositories.CharactersApiRepository
import com.herev.diego.marvelcharacters.domain.repositories.RepositoryException
import javax.inject.Inject

class CharactersDataLake @Inject constructor (private val apiRepository: CharactersApiRepository) {

    private val pageSize = 20

    private val characters = mutableListOf<Character>()
    private var _isListCompleted = false

    val isListCompleted : Boolean get() = _isListCompleted


    @Throws(RepositoryException::class)
    suspend fun getPage(page : Int) : List<Character> {
        val indexInitPage = pageSize * page

        if(isNeededDownloadMorePages(indexInitPage)){
            downloadPage()
        }
        return characters.subList(indexInitPage, indexInitPage+pageSize)
    }

    private fun  isNeededDownloadMorePages (indexInitPage : Int) : Boolean {
        return characters.size - pageSize <= indexInitPage && !_isListCompleted
    }

    @Throws(RepositoryException::class)
    suspend fun downloadPage () {
        val charactersResponse = apiRepository.getCharactersPage(characters.size, pageSize)
        charactersResponse.data?.let {
            _isListCompleted = (it.count ?: 0) < (it.limit ?: 0)
            characters.addAll(it.results)
        }
    }

}