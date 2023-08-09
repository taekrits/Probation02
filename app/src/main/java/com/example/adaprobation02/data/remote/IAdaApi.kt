package com.example.adaprobation02.data.remote

import com.example.adaprobation02.data.remote.model.districtResponse.CDistrictResponse
import com.example.adaprobation02.data.remote.model.povinceResponse.CPovinceResponse
import com.example.adaprobation02.data.remote.model.userResponse.CUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IAdaApi {
    @GET("{path}")
    suspend fun getUser(
        @Path("path") path: String,
        @Query("pdDate") date: String? = null
    ): Response<CUserResponse>

    @GET("{path}")
    suspend fun getPovince(
        @Path("path") path: String,
        @Query("pdDate") date: String? = null
    ): Response<CPovinceResponse>

    @GET("{path}")
    suspend fun getDistrict(
        @Path("path") path: String,
        @Query("pdDate") date: String? = null
    ): Response<CDistrictResponse>
}