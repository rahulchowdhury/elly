package co.rahulchowdhury.elly.data.source.remote.elephant

import co.rahulchowdhury.elly.data.model.remote.ElephantResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ElephantApiService {
    @GET("/elephants/name/{elephantName}")
    fun fetchElephant(@Path("elephantName") elephantName: String): Call<ElephantResponse>
}
