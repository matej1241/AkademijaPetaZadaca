package hr.ferit.brunozoric.taskie.ui.fragments.about


import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.ui.fragments.base.BaseFragment

class AboutAppFragment : BaseFragment() {
    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_about_app
    }

    companion object {
        fun newInstance(): AboutAppFragment {
            return AboutAppFragment()
        }
    }
}
