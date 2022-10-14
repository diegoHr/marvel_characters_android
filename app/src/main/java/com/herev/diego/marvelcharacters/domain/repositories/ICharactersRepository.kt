package com.herev.diego.marvelcharacters.domain.repositories

import com.herev.diego.marvelcharacters.domain.model.CharacterDataWrapper

interface ICharactersRepository {
    @Throws(RepositoryException::class)
    suspend fun getCharactersPage (lastElementIndex : Int, pageSize : Int) : CharacterDataWrapper
}