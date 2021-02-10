package com.dicoding.jpsubtiga

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.jpsubtiga.favoritemovie.FavoriteMovieFragment
import com.dicoding.jpsubtiga.favoritetvshow.FavoriteTvshowFragment
import com.dicoding.jpsubtiga.movies.MovieFragment
import com.dicoding.jpsubtiga.tvshow.TvshowFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    @StringRes
    private val tabTitles = intArrayOf(R.string.content_tab_movie, R.string.content_tab_tvshow, R.string.content_tab_favorite_movie, R.string.content_tab_favorite_tvshow)
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MovieFragment()
            1 -> fragment = TvshowFragment()
            2 -> fragment = FavoriteMovieFragment()
            3 -> fragment = FavoriteTvshowFragment()
        }
        return fragment as Fragment
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence {
        return mContext.resources.getString(tabTitles[position])
    }
    override fun getCount(): Int {
        return tabTitles.size
    }

}