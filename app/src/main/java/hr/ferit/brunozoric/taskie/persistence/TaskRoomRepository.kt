package hr.ferit.brunozoric.taskie.persistence

import hr.ferit.brunozoric.taskie.Taskie
import hr.ferit.brunozoric.taskie.db.DaoProvider
import hr.ferit.brunozoric.taskie.model.Priority
import hr.ferit.brunozoric.taskie.model.Task

class TaskRoomRepository: TaskRepository {
    private var db = DaoProvider.getInstance(Taskie.instance)

    private var taskDao = db.taskDao()

    override fun addTask(task: Task) {
        taskDao.insertTask(task)
    }

    override fun getTasks(): List<Task> = taskDao.getAllTasks()
    override fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }


    override fun clearAllTasks() {
        taskDao.deleteAllTasks()
    }

    override fun getTaskById(id: Long?): Task = taskDao.getTaskById(id)

    override fun orderByPriority(): List<Task> = taskDao.orderByPriority()

    override fun updateTask(taskId: Long, title: String, description: String, priority: Priority){
        taskDao.updateTask(taskId, title, description, priority)
    }
}