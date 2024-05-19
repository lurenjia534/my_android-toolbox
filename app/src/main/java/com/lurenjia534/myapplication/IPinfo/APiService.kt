package com.lurenjia534.myapplication.IPinfo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{ip}/json")
    fun getIpInfo(@Path("ip") ip: String): Call<Ipinfo>
}
