package com.android.movieappcompose.module.commons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.android.movieappcompose.databinding.ListItemMoviesBinding
import com.android.movieappcompose.rest.Urls.Companion.BASE_IMAGE_URL
import com.android.movieappcompose.rest.data.Movie

class MovieAppAdapter(
private val customerJobDetailsOnClickListener: OnclickListener

) : ListAdapter<Movie, MovieAppAdapter.MovieViewHolder>(DashBoardDiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val items: Movie? = getItem(position)
        holder.itemView.setOnClickListener {
            customerJobDetailsOnClickListener.onClick(items)
        }

        holder.bind(items)
    }

    class MovieViewHolder private constructor(private val binding: ListItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            movie: Movie?
        ) {
            binding.movieposterImageView.load(BASE_IMAGE_URL + movie?.backdropPath)
            binding.movieTitle.text = movie?.name

        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ListItemMoviesBinding.inflate(layoutInflater, parent, false)
                return MovieViewHolder(binding)
            }
        }
    }

    class DashBoardDiffUtils : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem == newItem
        }
    }

    class OnclickListener(
        val clickListener: (
            customerInfoResponseModel: Movie?
        ) -> Unit
    ) {
        fun onClick(customerInfoResponseModel: Movie?) =
            clickListener(customerInfoResponseModel)
    }
}
