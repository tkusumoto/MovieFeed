package com.kussuue.moviefeed.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kussuue.moviefeed.R
import com.kussuue.moviefeed.data.Movie
import com.kussuue.moviefeed.util.YOUTUBE_URL
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.movie_item.*

class MovieListAdapter(private val activity: Activity) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var movieList: List<Movie> = emptyList()

    class ViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView!!), LayoutContainer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val selectedMovie = movieList[position]

        selectedMovie.snippet?.thumbnails?.medium?.let {
            Glide.with(activity)
                .load(selectedMovie.snippet.thumbnails.medium.url)
                .into(holder.imageView)
        }

        holder.apply {
            txtMovieTitle.text = selectedMovie.snippet?.title
            txtChannelName.text = selectedMovie.snippet?.channelName
            txtPublishDate.text = selectedMovie.snippet?.publishDate
        }

        holder.itemView.setOnClickListener {
            selectedMovie.id?.let {
                activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_URL + selectedMovie.id.videoId)))
            }
        }
    }
}