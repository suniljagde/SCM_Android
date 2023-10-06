package com.example.tiplservice.getcall


import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GAPI {



    @Headers("APIKey: nF1ire8VAut/bxf/3q121RyUaCpYtufrqvqVF2+igMnhqCbhGcAZVHB0ybab4s2xZmv3criamJ1aYVxx6I9vIZNpFK46nSIolOONzJOEKBo=")
    @POST("GetCallComplaints")
    fun getData(@Body jsonObject: JsonObject): Call<DMA>

}