package com.tp.androiddataarchtiecturejan2021.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tp.androiddataarchtiecturejan2021.R
import com.tp.androiddataarchtiecturejan2021.data.Monster
import com.tp.androiddataarchtiecturejan2021.utils.TAG


class MainFragment : Fragment() {

    private lateinit var viewModel: MainFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        viewModel.monsterData.observe(viewLifecycleOwner, Observer {

            for (monster in it) {

                Log.i(TAG, "${monster.monsterName} and ${monster.price}")

            }
        })
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

}