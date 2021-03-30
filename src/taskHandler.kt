import javax.swing.table.DefaultTableModel
import kotlin.collections.ArrayList


class taskHandler() {

    var taskList = ArrayList<task>()

    fun updateTaskTables(
        activeTable: DefaultTableModel,
        PendingTable: DefaultTableModel,
        completeTable: DefaultTableModel
    ) {
        println("Updating the tables")
        println("Clearing the tables")
        activeTable.setNumRows(0)
        PendingTable.setNumRows(0)
        completeTable.setNumRows(0)

        println("Updating rows")

        if (taskList.isNotEmpty()) {
            for (i in taskList) {

                val checkResult = taskStatus(i)
                if (checkResult == "ACTIVE") {
                    activeTable.addRow(arrayOf<Any>(i.taskName, i.teamAssigned.teamName, i.estimateDays, "NA", "NA"))
                } else if (checkResult == "PENDING") {
                    PendingTable.addRow(
                        arrayOf<Any>(
                            i.taskName, i.teamAssigned.teamName, i.estimateDays, covertPriority(i)
                        )
                    )

                } else if (checkResult == "COMPLETE") {
                    completeTable.addRow(arrayOf<Any>(i.taskName, i.teamAssigned.teamName,i.estimateDays, "NA"))
                }

            }

        }

        println("Tables are updated")

    }

    fun covertPriority(task: task): String {
        var taskStr = ""
        for (i in task.priorityTasks) {
            taskStr += i.taskName + ", "
        }
        return if (taskStr.isNullOrBlank()) {
            "?"
        } else {
            taskStr
        }
    }

    private fun taskStatus(i: task): String {
        println("Checking task status")
        return when (i.status) {        //Uses Lambda
            TaskStatus.NONE -> {

                calculateTaskStatus(i)
                taskStatus(i)      //uses recursion as the status should be known
            }
            TaskStatus.ACTIVE -> {
                "ACTIVE"
            }
            TaskStatus.PENDING -> {
                "PENDING"
            }
            TaskStatus.COMPLETE -> {
                "COMPLETE"
            }
            else -> {
                "ERROR"                 // this isn't working yet
            }
        }
    }


    fun createTask(
        taskName: String = "Task",
        estimatedDays: Int,
        teamAssigned: team,
        taskDescription: String,
        priority: String,
        mainFrame: MainGUI) {

        println("Creating Tasks")

        val preReqList = getPriority(priority)      //gets the priority tasks as an ArrayList of Tasks
        taskList.add(task(taskName, estimatedDays, teamAssigned, taskDescription, preReqList))
        calculateTaskStatus(taskList.last())
        println("Task created -> \n$taskList")
        Main.projectH.savingNewProject()
        mainFrame.updateTaskPanels()
    }


    fun findTask(taskName: String): task {
        taskList.forEach { task ->
            if (taskName == task.taskName) {
                return task
            }
        }
        return taskList[0]
    }

    fun deleteTask(selectedTaskIndex: Int, selectedTaskName: String, mainFrame: MainGUI) {
        println("Deleting Tasks")

        taskList.removeAt(selectedTaskIndex)
        removePriority(selectedTaskName)
        mainFrame.updateTaskPanels()
        Main.projectH.savingNewProject()

        println("Tasks Deleted")
    }

    fun completeTask(selectedTask: String, mainFrame: MainGUI) {

        val foundTask = findTask(selectedTask)
        //val taskIndex = tasks.indexOf(foundTask)


        foundTask.status = TaskStatus.COMPLETE
        println(foundTask.status)// It works


        println("attempt at completing the task")
        println("found task Status -> ${foundTask.status}")
        println("current project tasks -> " + Main.projectH.currentProject.taskList)
        removePriority(selectedTask)

        Main.projectH.savingNewProject()

        mainFrame.updateTaskPanels()
    }

    private fun removePriority(selectedTaskName: String) {
        for (i in taskList) {
            for (j in i.priorityTasks) {
                if (selectedTaskName == j.taskName) {
                    i.priorityTasks.remove(j)
                    break
                    calculateTaskStatus(i)  //recalculating the statuses
                }
            }
            calculateTaskStatus(i)
        }
        for (i in taskList) {
            calculateTaskStatus(i)
        }

    }

    private fun calculateTaskStatus(task: task) {

        if (task.priorityTasks.isNotEmpty()) {
            for (i in task.priorityTasks) {
                if (i.status == TaskStatus.PENDING || i.status == TaskStatus.ACTIVE) {
                    task.status = TaskStatus.PENDING
                    println("Status=PENDING")
                    break
                } else if (i.status == TaskStatus.COMPLETE) {
                    task.status = TaskStatus.ACTIVE

                    println(" Status=ACTIVE")
                } else {
                    println("ERROR: Priority doesn't have status")
                }
            }

        } else {
            if (task.status == TaskStatus.COMPLETE) {
                println(" Task is Complete")
            } else {
                task.status = TaskStatus.ACTIVE
                println(" Status=ACTIVE")
            }

        }


    }

    private fun getPriority(priority: String): ArrayList<task> {
        println(" Getting the priority tasks")
        val returnList = ArrayList<task>()
        return if (priority.isEmpty()) {
            returnList
        } else {
            //split the string
            val temp: Array<String> = priority.split(", ".toRegex()).toTypedArray()
            val thTask = Main.taskH.taskList

            //loops through list of user input and checks them against each task in task handler//
            for (i in temp.indices) {
                for (j in thTask.indices) {
                    if (temp[i] == thTask[j].taskName) {
                        returnList.add(thTask[j])
                        println("Added Priority task: " + thTask[j])
                    }
                }
            }
            returnList
        }
    }

}