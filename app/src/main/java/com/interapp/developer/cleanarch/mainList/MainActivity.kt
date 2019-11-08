package com.interapp.developer.cleanarch.mainList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.interapp.developer.cleanarch.DIFF_CALLBACK
import com.interapp.developer.cleanarch.R
import com.interapp.developer.cleanarch.ViewModelFactory
import com.interapp.developer.cleanarch.databinding.ActivityMainBinding
import com.interapp.developer.cleanarch.databinding.RowPokeListBinding
import com.interapp.developer.domain.model.IPokemon
import com.interapp.developer.domain.model.Response
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var mAdapter : MainListPageAdapter
    @Inject
    lateinit var mViewModelFactory : ViewModelFactory

    private lateinit var mViewModel : MainListViewModel

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)


        mViewModel = ViewModelProviders.of(this, mViewModelFactory)[MainListViewModel::class.java]

        mViewModel.closingListLiveData?.observe(this, Observer {
            mAdapter.submitList(it)

        })

        mViewModel.responseState.observe(this, Observer<Response> {
            binding.response = it
            if(it is Response.Failure<*>) {
                binding.errorMessage = it.message as String
            }
        })
        mainListView.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = mAdapter;

        }
    }
}



/**
 * Adapter class for pagination
 */
class MainListPageAdapter() : PagedListAdapter<IPokemon, MainListPageAdapter.Holder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val closingItem = getItem(position);
        closingItem?.let {  holder.bindTo(closingItem)}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = RowPokeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    /**
     * Holder class for each news item
     */
    inner class Holder(private val binding : RowPokeListBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener({openDetails()})
        }

        private fun openDetails() {

        }

        fun bindTo(item: IPokemon) {
            binding.pokemonModel = item
        }

    }
}