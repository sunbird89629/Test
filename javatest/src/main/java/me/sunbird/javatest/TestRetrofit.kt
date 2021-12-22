package me.sunbird.javatest

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 作者：王豪
 * 日期：2021/11/10
 * 描述：<必填>
 */


data class Repo(var name: String)

interface GitHubService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<Repo>>
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun main() {
    val service = retrofit.create(GitHubService::class.java)
    val call = service.listRepos("sunbird89629")

    call.enqueue(object : Callback<List<Repo>> {
        /**
         * Invoked for a received HTTP response.
         *
         *
         * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
         * Call [Response.isSuccessful] to determine if the response indicates success.
         */
        override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
            println(response.body())
        }

        /**
         * Invoked when a network exception occurred talking to the server or when an unexpected exception
         * occurred creating the request or processing the response.
         */
        override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
            t.printStackTrace()
        }
    })

    while (true) {
        Thread.sleep(1000)
    }
}