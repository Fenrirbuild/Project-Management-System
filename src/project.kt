import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

data class project(var projectName: String = "project", var projectdate: Date, var prjEndDate: Date?? = null, var prjDescription: String):Serializable {
    var taskList = ArrayList<task>()
}