package com.tp.androiddataarchtiecturejan2021.repo

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.tp.androiddataarchtiecturejan2021.R
import com.tp.androiddataarchtiecturejan2021.data.Monster
import com.tp.androiddataarchtiecturejan2021.data.MonsterService
import com.tp.androiddataarchtiecturejan2021.utils.BASE_URL
import com.tp.androiddataarchtiecturejan2021.utils.FileHalper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MonsterRepo(val app: Application) {
    val listType = Types.newParameterizedType(
        List::class.java,
        Monster::class.java
    )

    val monsters = MutableLiveData<List<Monster>>()

    init {

        if (isNetworkAvailable()) {

            CoroutineScope(Dispatchers.IO).launch {

                getWebData()
            }
        }
    }

    fun readFromRawRes(): MutableLiveData<List<Monster>> {

        val moshi = Moshi.Builder().build()

        val adapter: JsonAdapter<List<Monster>> = moshi.adapter(listType)

        val json = FileHalper.readFileFromRaw(app, R.raw.monster_data)

        val monsterData = adapter.fromJson(json)

        monsters.value = monsterData

        return monsters


    }

    fun isNetworkAvailable(): Boolean {

        val cm = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = cm.activeNetworkInfo

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {

                    return true

                }
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {

                    return true
                }
            }


        } else {
            return networkInfo?.isConnectedOrConnecting ?: false

        }
        return false
    }

    @WorkerThread
    suspend fun getWebData() {

        val retrofit =
            Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create()).baseUrl(
                BASE_URL
            ).build()

        val service = retrofit.create(MonsterService::class.java)

        val monsterData = service.getMonsterData().body() ?: emptyList()

        monsters.postValue(monsterData)

    }

}