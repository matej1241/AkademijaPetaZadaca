package hr.ferit.brunozoric.taskie.ui.fragments.about


import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.ui.fragments.base.BaseFragment

class AboutAuthorFragment : BaseFragment() {
    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_about_author
    }

    companion object {
        fun newInstance(): AboutAuthorFragment {
            return AboutAuthorFragment()
        }
    }

}
