package com.mx.kavak.android.data.sources.remote

import com.mx.kavak.android.domain.dto.response.InhabitantsResponse
import com.mx.kavak.android.domain.models.ResultWrapper

interface InhabitantRemoteDataSource {

    suspend fun readInhabits(): ResultWrapper<InhabitantsResponse?>

}