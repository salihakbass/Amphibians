package com.salihakbas.amphibians.data.remote

import com.salihakbas.amphibians.data.model.Amphibians
import retrofit2.http.GET

interface AmphibiansApiService {
@GET("amphibians")
suspend fun getData() : List<Amphibians>
}