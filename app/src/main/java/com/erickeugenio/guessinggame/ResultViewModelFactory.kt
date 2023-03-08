package com.erickeugenio.guessinggame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultViewModelFactory(private val finalResult: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(String::class.java).newInstance(finalResult)

//        if (modelClass.isAssignableFrom(ResultViewModel::class.java))
//            return ResultViewModel(finalResult) as T
//        throw IllegalArgumentException("Unknown ViewModel")
    }
}