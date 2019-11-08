package com.interapp.developer.cleanarch

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import com.interapp.developer.domain.model.IPokemon

const val ENDPOINT_URL = "https://pokeapi.co/api/v2/pokemon/";

/**
Created by Preethi Valsalan on 2019-10-28
 */


var DIFF_CALLBACK: DiffUtil.ItemCallback<IPokemon> = object : DiffUtil.ItemCallback<IPokemon>() {
    override fun areItemsTheSame(oldItem: IPokemon,
                                 newItem: IPokemon) = oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: IPokemon,
                                    newItem: IPokemon) = oldItem.name == newItem.name
}

/**
 * Check whether network is available
 * @param context
 * @return
 */
fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}


/**
 * Common viewModel Factory
 */
class  ViewModelFactory(val viewModel: ViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModel as T
    }

}