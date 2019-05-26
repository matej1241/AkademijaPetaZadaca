package hr.ferit.brunozoric.taskie.ui.fragments.about


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.ui.adapters.AboutPagerAdapter
import hr.ferit.brunozoric.taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_about.*


class AboutFragment : BaseFragment() {

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_about
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }


    private fun initUi() {
        viewPager.adapter = AboutPagerAdapter(fragmentManager!!)
        tabLayout.setupWithViewPager(viewPager)
    }

    companion object {
        fun newInstance(): Fragment {
            return AboutFragment()
        }
    }

}
