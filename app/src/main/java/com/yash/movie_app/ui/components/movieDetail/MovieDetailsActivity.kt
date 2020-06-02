package com.yash.movie_app.ui.components.movieDetail

import android.view.MenuItem
import com.bumptech.glide.Glide
import com.yash.movie_app.R
import com.yash.movie_app.model.MovieDetails
import com.yash.movie_app.model.Results
import com.yash.movie_app.remote.ApiService
import com.yash.movie_app.ui.base.BaseActivity
import com.yash.movie_app.utils.AppUtils
import com.yash.movie_app.utils.Constants
import com.yash.movie_app.utils.Progress
import kotlinx.android.synthetic.main.activity_movie_details.*
import javax.inject.Inject

/**
 * Created by Joshi on 01-06-2020.
 */
class MovieDetailsActivity :
    BaseActivity<MovieDetailsContract.View, MovieDetailsContract.Presenter>(),
    MovieDetailsContract.View {

    @Inject
    lateinit var movieDetailsPresenter: MovieDetailsPresenter

    private lateinit var progress: Progress
    private lateinit var details: Results

    override fun initPresenter() = movieDetailsPresenter

    override fun initUI() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        progress = Progress(this, R.string.please_wait)

        details = intent.getSerializableExtra(Constants.MOVIE_DETAILS) as Results
        presenter?.getCastDetails(details.id!!)
    }

    override fun injectDependencies() = getApplicationComponent().inject(this)

    override fun getLayoutResId() = R.layout.activity_movie_details

    override fun showProgress() = progress.show()

    override fun stopProgress() = progress.dismiss()

    override fun showToast(toast: String) = AppUtils.showToast(this, toast)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun setUI(cast: List<MovieDetails>) {

        Glide.with(this)
            .load(ApiService.fetchImageUrl + details.poster_path)
            .placeholder(R.drawable.no_image)
            .into(ivMoviePoster)

        AppUtils.checkNullAndSetText(tvMovieName, details.title, getString(R.string.na))
        AppUtils.checkNullAndSetText(tvReleaseDate, details.release_date, getString(R.string.na))

        var runtime = ""
        if (details.runtime != null) {
            val hours = details.runtime!! / 60
            val minutes = details.runtime!! % 60
            if (hours != 0) {
                runtime += hours.toString().trim() + "h"
            }
            if (minutes != 0) {
                runtime += minutes.toString().trim() + "m"
            }
        }

        AppUtils.checkNullAndSetText(
            tvRunningTime,
            if (runtime.isNotEmpty()) runtime else null,
            getString(R.string.na)
        )

        val listOfGenres = details.genres
        var appendGenres = ""
        if (!listOfGenres.isNullOrEmpty()) {
            for (genre in listOfGenres) {
                appendGenres += genre.name + ", "
            }
        }

        val genreSubstring = appendGenres.substring(0, appendGenres.length - 2)
        AppUtils.checkNullAndSetText(
            tvGenre,
            if (genreSubstring.isNotEmpty()) genreSubstring else null,
            getString(R.string.na)
        )

        val ratings = details.vote_average!! * 10
        AppUtils.checkNullAndSetText(
            tvRatingScore,
            if (ratings != 0.0) ratings.toInt().toString().trim() + "%" else null,
            this.getString(R.string.na)
        )

        AppUtils.checkNullAndSetText(
            tvTotalVote,
            details.vote_count.toString().trim(),
            getString(R.string.na)
        )

        var appendCast = ""
        var counter = 0
        if (!cast.isNullOrEmpty()) {
            for (person in cast) {
                while (counter < 5) {
                    appendCast += person.name + " as \"" + person.character + "\"\n"
                    counter++
                    break
                }
            }
        }

        AppUtils.checkNullAndSetText(tvCast, appendCast, getString(R.string.na))
        AppUtils.checkNullAndSetText(tvOverview, details.overview, getString(R.string.na))
    }
}