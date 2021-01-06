package com.tp.androiddataarchtiecturejan2021.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.tp.androiddataarchtiecturejan2021.data.Monster
import com.tp.androiddataarchtiecturejan2021.repo.MonsterRepo

class MainFragmentViewModel(app : Application) : AndroidViewModel(app) {

    private val mRepo = MonsterRepo(app)

    val monsterData = mRepo.monsters

}