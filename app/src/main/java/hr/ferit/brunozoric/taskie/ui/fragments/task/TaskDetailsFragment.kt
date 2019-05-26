package hr.ferit.brunozoric.taskie.ui.fragments.task

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.Taskie
import hr.ferit.brunozoric.taskie.common.EXTRA_TASK_ID
import hr.ferit.brunozoric.taskie.common.displayToast
import hr.ferit.brunozoric.taskie.model.Priority
import hr.ferit.brunozoric.taskie.model.Task

import hr.ferit.brunozoric.taskie.persistence.TaskRoomRepository
import hr.ferit.brunozoric.taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_dialog_new_task.*
import kotlinx.android.synthetic.main.fragment_task_details.*
import kotlinx.android.synthetic.main.fragment_task_details.prioritySelector
import kotlinx.android.synthetic.main.item_task.*

class TaskDetailsFragment : BaseFragment() {

    private val repository = TaskRoomRepository()
    private var taskID = NO_TASK

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_task_details
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(EXTRA_TASK_ID)?.let { taskID = it }
        tryDisplayTask(taskID)
        initListeners()
    }

    private fun tryDisplayTask(id: Int) {
        try {
            val task = repository.getTaskById(id.toLong())
            Log.d("Tag", task.toString())
            displayTask(task)
        } catch (e: NoSuchElementException) {
            context?.displayToast(getString(R.string.noTaskFound))
        }
    }

    private fun displayTask(task: Task) {
        detailsTaskTitle.setText(task.title)
        detailsTaskDescription.setText(task.description)
        detailsPriorityView.setBackgroundResource(task.priority.getColor())
        prioritySelector.adapter = ArrayAdapter<Priority>(Taskie.instance, android.R.layout.simple_spinner_dropdown_item, Priority.values())
        when(task.priority){
            Priority.LOW -> prioritySelector.setSelection(0)
            Priority.MEDIUM -> prioritySelector.setSelection(1)
            Priority.HIGH -> prioritySelector.setSelection(2)
        }
    }

    private fun initListeners() {
        editTaskButton.setOnClickListener { editTask() }
    }

    private fun editTask() {
        val title = detailsTaskTitle.text.toString()
        val description = detailsTaskDescription.text.toString()
        val priority = prioritySelector.selectedItem as Priority
        repository.updateTask(taskID.toLong(), title, description, priority)
    }

    companion object {
        const val NO_TASK = -1

        fun newInstance(taskId: Int): TaskDetailsFragment {
            val bundle = Bundle().apply { putInt(EXTRA_TASK_ID, taskId) }
            return TaskDetailsFragment().apply { arguments = bundle }
        }
    }
}
