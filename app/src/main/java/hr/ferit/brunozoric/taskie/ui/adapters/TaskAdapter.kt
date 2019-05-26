package hr.ferit.brunozoric.taskie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.model.Task

class TaskAdapter(private val onItemSelected: (Task) -> Unit) : Adapter<TaskHolder>() {

    private val data: MutableList<Task> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskHolder(v)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bindData(data[position], onItemSelected)
    }

    fun setData(data: List<Task>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun clearData(){
        this.data.clear()
        notifyDataSetChanged()
    }

    fun sortByPriority() {
        data.sortByDescending { it.priority.value }
        notifyDataSetChanged()
    }

    fun getCurrentTask(position: Int) = data[position]

    fun removeTask(task: Task){
        this.data.remove(task)
        notifyDataSetChanged()
    }

}





