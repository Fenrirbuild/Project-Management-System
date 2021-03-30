import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

data class task(
    val taskName: String = "Task",
    val estimateDays: Int,
    var teamAssigned: team,
    var taskDescription: String,
    var priorityTasks: ArrayList<task>,
    var projectName: String = "Project"
) :
    Serializable {

    var status = TaskStatus.NONE


    var delayTime: Int = 0
    var estFinishDate: Date? = null
    var actualFinishDate: Date? = null

}

enum class TaskStatus {
    ACTIVE, PENDING, COMPLETE, NONE
}