package hr.ferit.brunozoric.taskie.persistence

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hr.ferit.brunozoric.taskie.model.Priority
import hr.ferit.brunozoric.taskie.model.Task

interface TaskRepository {

    fun addTask(task: Task)
    fun getTasks(): List<Task>
    fun deleteTask(task: Task)
    fun clearAllTasks()
    fun orderByPriority(): List<Task>
    fun getTaskById(id: Long?): Task
    fun updateTask(taskId: Long, title: String, description: String, priority: Priority)
}
