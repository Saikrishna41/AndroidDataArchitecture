package com.tp.androiddataarchtiecturejan2021.data

import com.tp.androiddataarchtiecturejan2021.utils.BASE_URL
import retrofit2.Response
import retrofit2.http.GET

interface MonsterService {

    @GET("/feed/monster_data.json")
    suspend fun getMonsterData() : Response<List<Monster>>
}