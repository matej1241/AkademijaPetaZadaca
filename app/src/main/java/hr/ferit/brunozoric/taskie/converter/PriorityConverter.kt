package hr.ferit.brunozoric.taskie.converter

import androidx.room.TypeConverter
import hr.ferit.brunozoric.taskie.model.Priority

class PriorityConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromPriority(value: Priority): Int {
            return when(value){
                Priority.LOW -> 0
                Priority.MEDIUM -> 1
                Priority.HIGH -> 2
            }
        }

        @TypeConverter
        @JvmStatic
        fun toPriority(value: Int): Priority {
            return when(value){
                0 -> Priority.LOW
                1 -> Priority.MEDIUM
                2 -> Priority.HIGH
                else -> return Priority.LOW
            }
        }
    }
}