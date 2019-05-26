package hr.ferit.brunozoric.taskie.ui.activities

import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.model.Task
import hr.ferit.brunozoric.taskie.persistence.TaskRepository
import hr.ferit.brunozoric.taskie.persistence.TaskRoomRepository
import hr.ferit.brunozoric.taskie.ui.activities.base.BaseActivity
import hr.ferit.brunozoric.taskie.ui.adapters.TaskAdapter
import hr.ferit.brunozoric.taskie.ui.fragments.about.AboutFragment
import hr.ferit.brunozoric.taskie.ui.fragments.task.MenuListener
import hr.ferit.brunozoric.taskie.ui.fragments.task.TasksFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    override fun getLayoutResourceId() = R.layout.activity_main
    private val repository: TaskRepository = TaskRoomRepository()

    private val adapter by lazy { TaskAdapter {onItemSelected(it)} }

    private val mOnNavigationItemSelectedListener  = BottomNavigationView.OnNavigationItemSelectedListener {item ->
        when(item.itemId){
            R.id.about -> {
                showFragment(AboutFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.tasks -> {
                showFragment(TasksFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            else -> return@OnNavigationItemSelectedListener false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.task_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.deleteTasksMenuItem -> {
                repository.clearAllTasks()
                adapter.clearData()
                showFragment(TasksFragment.newInstance())
            }
            R.id.sortByPriorityMenuItem -> {
                adapter.sortByPriority()
                showFragment(TasksFragment.newInstance())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setUpUi() {
        showFragment(TasksFragment.newInstance())
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun onItemSelected(it: Task) {

    }

}