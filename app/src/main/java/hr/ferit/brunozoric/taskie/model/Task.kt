package hr.ferit.brunozoric.taskie.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) var pizzaDbId : Long? = null,
    @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "priority") val priority: Priority
)