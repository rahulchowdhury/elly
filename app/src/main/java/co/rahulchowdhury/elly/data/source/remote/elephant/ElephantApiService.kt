package co.rahulchowdhury.elly.data.source.remote.elephant

import co.rahulchowdhury.elly.data.model.remote.ElephantResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ElephantApiService {
    @GET("/elephants")
    suspend fun fetchElephants(): List<ElephantResponse>

    @GET("/elephants/name/{elephantName}")
    suspend fun fetchElephant(@Path("elephantName") elephantName: String): ElephantResponse
}
