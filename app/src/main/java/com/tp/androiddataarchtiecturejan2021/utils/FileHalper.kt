package com.tp.androiddataarchtiecturejan2021.utils

import android.content.Context

class FileHalper {

    companion object  {

        fun readFileFromRaw(context : Context, resId : Int) : String {

           return context.resources.openRawResource(resId).use {

                it.bufferedReader().use {

                    it.readText()
                }
            }
        }
    }
 }