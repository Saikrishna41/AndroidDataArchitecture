package com.tp.androiddataarchtiecturejan2021.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tp.androiddataarchtiecturejan2021.R
import com.tp.androiddataarchtiecturejan2021.data.Monster
import com.tp.androiddataarchtiecturejan2021.utils.TAG


class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val monster = Monster(
            "bob", "hfhfh", "CAPTION", "DESCRIPTION", 22.1, 4
        )
        Log.i(TAG, "${monster.monsterName} and ${monster.price}")
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

}