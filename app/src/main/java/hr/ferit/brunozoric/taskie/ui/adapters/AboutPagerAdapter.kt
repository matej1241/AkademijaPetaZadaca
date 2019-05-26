package hr.ferit.brunozoric.taskie.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.ferit.brunozoric.taskie.ui.fragments.about.AboutAppFragment
import hr.ferit.brunozoric.taskie.ui.fragments.about.AboutAuthorFragment
class AboutPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    val fragments = arrayOf(
        AboutAppFragment.newInstance(),
        AboutAuthorFragment.newInstance()
    )

    val titles = arrayOf(
        "About Application",
        "About author"
    )

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}

