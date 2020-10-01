package com.kussuue.moviefeed.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kussuue.moviefeed.R
import com.kussuue.moviefeed.util.LoadStatus
import com.kussuue.moviefeed.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private val movieAdapter: MovieListAdapter by lazy {
        MovieListAdapter(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(movieList) {
            layoutManager = LinearLayoutManager(activity)
            adapter = movieAdapter
        }

        searchButton.setOnClickListener {
            viewModel.getMovie(inputSearchWord.text.toString())
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        viewModel.movieList.observe(requireActivity(), Observer { list ->
            txtSearchWord.text = viewModel.searchWord.value
            list?.let {
                movieAdapter.movieList = it
            }
            movieAdapter.notifyDataSetChanged()
        })

        viewModel.getState().observe(requireActivity(), Observer {status ->
            progressBar.visibility = if(status == LoadStatus.LOADING) View.VISIBLE else View.GONE

        })
    }
}