package com.yash.movie_app.ui.components.movies

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.yash.movie_app.R
import com.yash.movie_app.ui.base.BaseActivity
import com.yash.movie_app.ui.components.movies.categories.CategoryFragment
import com.yash.movie_app.utils.AppUtils
import com.yash.movie_app.utils.Constants
import com.yash.movie_app.utils.Progress
import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject

/**
 * Created by Joshi on 01-06-2020.
 */
class MoviesActivity : BaseActivity<MoviesContract.View, MoviesContract.Presenter>(),
    MoviesContract.View {

    @Inject
    lateinit var moviesPresenter: MoviesPresenter

    private lateinit var progress: Progress
    private lateinit var mPager: ViewPager

    override fun initPresenter() = moviesPresenter

    override fun initUI() {
        supportActionBar!!.elevation = 0F
        supportActionBar!!.title = getString(R.string.lbl_movies)
        progress = Progress(this, R.string.please_wait)

        mPager = findViewById(R.id.vpMovieList)
        val pagerAdapter =
            SliderAdapter(
                supportFragmentManager
            )
        mPager.adapter = pagerAdapter
        val tabLayout = findViewById<TabLayout>(R.id.tlMovieListTab)
        tabLayout.setupWithViewPager(vpMovieList)
    }

    override fun injectDependencies() = getApplicationComponent().inject(this)

    override fun getLayoutResId() = R.layout.activity_movies

    class SliderAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

        override fun getCount(): Int {
            return NUM_ITEMS
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> CategoryFragment.switchCategories(0, Constants.NOW_PLAYING)
                1 -> CategoryFragment.switchCategories(1, Constants.POPULAR)
                else -> CategoryFragment()
            }
        }

        // Returns the page title for the top indicator
        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> Constants.NOW_PLAYING
                1 -> Constants.POPULAR
                else -> null
            }
        }

        companion object {
            private const val NUM_ITEMS = 2
        }
    }

/*    override fun setMoviesToAdapter(notifications: List<Movies>) {
        rcvAlertList.visible = true
        tvNoData.visible = false
        val adapter = MoviesAdapter(this, notifications)
        rcvAlertList.layoutManager = LinearLayoutManager(this)
        rcvAlertList.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun showNoData() {
        rcvAlertList.visible = false
        tvNoData.visible = true
    }*/
}