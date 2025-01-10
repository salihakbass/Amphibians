package com.salihakbas.amphibians.data.repository

import com.salihakbas.amphibians.data.model.Amphibians
import com.salihakbas.amphibians.data.remote.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun getAmphibiansData(): List<Amphibians>
}

class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    override suspend fun getAmphibiansData(): List<Amphibians> = amphibiansApiService.getData()
}