package com.herev.diego.marvelcharacters.domain.repositories

import com.herev.diego.marvelcharacters.R
import com.herev.diego.marvelcharacters.androidConectors.stringResources.IStringResourceProvider
import com.herev.diego.marvelcharacters.domain.model.CharacterDataWrapper
import com.herev.diego.marvelcharacters.logger.Logger
import com.herev.diego.marvelcharacters.network.MarvelApiService
import com.herev.diego.marvelcharacters.network.NetResponseProcessor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharactersApiRepository @Inject constructor(private val apiService: MarvelApiService,
                                                  private val netResponseProcessor: NetResponseProcessor,
                                                  private val strResProvider : IStringResourceProvider) : ICharactersRepository {

    @Throws(RepositoryException::class)
    override suspend fun getCharactersPage (lastElementIndex : Int, pageSize : Int) : CharacterDataWrapper {
        try {
            return downloadCharactersPage(lastElementIndex, pageSize)
        }catch (apiError : NetResponseProcessor.ApiResponseErrorException){
            Logger.e(LOG_TAG, apiError)
            throw RepositoryException(apiError.message ?: strResProvider.getString(R.string.error_network_default))
        }catch (netError : NetResponseProcessor.NetErrorException) {
            Logger.e(LOG_TAG, netError)
            throw RepositoryException(strResProvider.getString(R.string.error_network_default))
        }
    }

    @Throws(NetResponseProcessor.ApiResponseErrorException::class, NetResponseProcessor.NetErrorException::class)
    private suspend fun downloadCharactersPage (lastElementIndex: Int, pageSize: Int) : CharacterDataWrapper = withContext(Dispatchers.IO){
        netResponseProcessor.checkResponse(
            apiService.getCharacters(lastElementIndex, pageSize)
        )
    }


    companion object{
        const val LOG_TAG = "CharactersApiRepository"
    }

}