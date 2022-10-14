package com.herev.diego.marvelcharacters.model

import com.herev.diego.marvelcharacters.AbstractTest
import com.herev.diego.marvelcharacters.R
import com.herev.diego.marvelcharacters.androidConectors.stringResources.IStringResourceProvider
import com.herev.diego.marvelcharacters.domain.CharactersDataLake
import com.herev.diego.marvelcharacters.domain.model.Character
import com.herev.diego.marvelcharacters.domain.repositories.CharactersApiRepository
import com.herev.diego.marvelcharacters.network.ApiFactory
import com.herev.diego.marvelcharacters.network.NetResponseProcessor
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class CharactersDataLakeTest : AbstractTest() {

    val apiService = ApiFactory().createMarvelApiService()
    val stringResProvider : IStringResourceProvider = mockk()
    val charactersRepository  = CharactersApiRepository(apiService, NetResponseProcessor(), stringResProvider)

    val dataLake = CharactersDataLake(charactersRepository)


    @Before
    fun setup () {
        every { stringResProvider.getString(R.string.error_network_default) }.returns("Network error")

    }

    @Test
    fun `check if first page is equals from dataLake and repository` () {
        runBlocking {
            val page1FromRepository :List<Character> = charactersRepository.getCharactersPage(0, 20).data!!.results
            val page1FromDataLake :List<Character> = dataLake.getPage(0)

            assertThat(page1FromDataLake.size, `is`(page1FromRepository.size))
            assertThat(page1FromDataLake[0].id, `is`(page1FromRepository[0].id))
            assertThat(page1FromDataLake[page1FromDataLake.size-1].id, `is`(page1FromRepository[page1FromDataLake.size-1].id))
        }

    }

    @Test
    fun `check if second page is equals from dataLake and repository` () {

        runBlocking {
            dataLake.getPage(0)

            val page2FromRepository = charactersRepository.getCharactersPage(20, 20).data!!.results
            val page2FromDataLake = dataLake.getPage(1)

            assertThat(page2FromDataLake.size, `is`(page2FromRepository.size))
            assertThat(page2FromDataLake[0].id, `is`(page2FromRepository[0].id))
            assertThat(page2FromDataLake[page2FromDataLake.size-1].id, `is`(page2FromRepository[page2FromDataLake.size-1].id))
        }


    }

    @Test
    fun `check that there are no gaps between pages` () {
        runBlocking {
            val validationPage2FromRepository = charactersRepository.getCharactersPage(19, 2).data!!.results
            val page1FromRepository :List<Character> = charactersRepository.getCharactersPage(0, 20).data!!.results
            val page2FromRepository = charactersRepository.getCharactersPage(20, 20).data!!.results
            assertThat(validationPage2FromRepository[0].id, `is`(page1FromRepository[page1FromRepository.size-1].id))
            assertThat(validationPage2FromRepository[1].id, `is`(page2FromRepository[0].id))
        }
    }

}