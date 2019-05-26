package hr.ferit.brunozoric.taskie.ui.fragments.task

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.*
import hr.ferit.brunozoric.taskie.model.Task
import hr.ferit.brunozoric.taskie.persistence.TaskRepository
import hr.ferit.brunozoric.taskie.persistence.TaskRoomRepository
import hr.ferit.brunozoric.taskie.ui.activities.ContainerActivity
import hr.ferit.brunozoric.taskie.ui.adapters.TaskAdapter
import hr.ferit.brunozoric.taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tasks.*

class TasksFragment : BaseFragment(),
    AddTaskFragmentDialog.TaskAddedListener, MenuListener{
    private val repository: TaskRepository = TaskRoomRepository()
    private val adapter by lazy { TaskAdapter {onItemSelected(it)} }
    override fun getLayoutResourceId() = R.layout.fragment_tasks

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initListeners()
        refreshTasks()
    }
    private fun initUi() {
        progress.visible()
        noData.visible()
        tasksRecyclerView.layoutManager = LinearLayoutManager(context)
        tasksRecyclerView.adapter = adapter
    }

    private fun initListeners() {
        addTask.setOnClickListener { addTask() }
        swipeToDeleteListener()
    }

    private fun onItemSelected(task: Task){
        activity?.showFragment(R.id.fragmentContainer, TaskDetailsFragment.newInstance(task.pizzaDbId!!.toInt()))
    }

    override fun clearAllTasks() {
        repository.clearAllTasks()
        refreshTasks()
    }

    override fun sortByPriority() {
        adapter.setData(repository.orderByPriority())
    }

    private fun refreshTasks() {
        progress.gone()
        val data = repository.getTasks()
        if (data.isNotEmpty()) {
            noData.gone()
        } else {
            noData.visible()
        }
        adapter.setData(data)
    }

    private fun addTask() {
        val dialog = AddTaskFragmentDialog.newInstance()
        dialog.setTaskAddedListener(this)
        dialog.show(childFragmentManager, dialog.tag)
    }

    override fun onTaskAdded(task: Task) {
        refreshTasks()
    }

    private fun swipeToDeleteListener() {
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val task = adapter.getCurrentTask(position)
                adapter.removeTask(task)
                repository.deleteTask(task)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView)
    }

    companion object {
        fun newInstance(): Fragment {
            return TasksFragment()
        }
    }
}