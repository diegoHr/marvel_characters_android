package com.herev.diego.marvelcharacters.domain.model

import com.herev.diego.marvelcharacters.network.ApiResponse

class CharacterDataWrapper (code : Int?, status : String?,
                                 copyright : String?, attributionText : String?,
                                 attributionHTML : String?, val data : CharacterDataContainer?,
                                 etag : String?)
    : ApiResponse(code, status, copyright, attributionText,attributionHTML, etag)