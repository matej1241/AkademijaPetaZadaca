package hr.ferit.brunozoric.taskie.db

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import hr.ferit.brunozoric.taskie.model.Priority
import hr.ferit.brunozoric.taskie.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAllTasks(): List<Task>

    @Insert(onConflict = IGNORE)
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(pizza: Task)

    @Query("DELETE from Task")
    fun deleteAllTasks()

    @Query("SELECT * FROM Task ORDER BY priority DESC")
    fun orderByPriority(): List<Task>

    @Query("SELECT * FROM Task WHERE pizzaDbId = :taskId")
    fun getTaskById(taskId: Long?): Task

    @Query("UPDATE Task SET title= :title, description= :description, priority= :priority WHERE pizzaDbId = :taskId")
    fun updateTask(taskId: Long, title: String, description: String, priority: Priority)
}

