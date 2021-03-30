import java.io.*

class persistence {


    val path = System.getProperty("user.dir")
    var projectPath= "$path/ProjectList.txt" // the system will auto generate the file, if an error persists just empty the file and try again
    var teamPath = "$path/TeamList.txt"

init {
    print(projectPath)
}
    fun saving(mainProjectData: ArrayList<project>){
        try {
            ObjectOutputStream(FileOutputStream(projectPath)).use{it.writeObject(mainProjectData)}
        } catch (e:IOException){
            println(" Cannot Save")
        }


    }

    fun saving(list : ArrayList<team>, bool: Boolean) {
        println("$teamPath")
        try {
            ObjectOutputStream(FileOutputStream(teamPath)).use{ it -> it.writeObject(list)} //Lambda used to save to file
        }catch (e:IOException){
            println("Error: Unable to save")
        }

        println("List of Projects Saved to file\n")

    }
    fun loading(): ArrayList<project> {


        println("$projectPath")

        val nullList = ArrayList<project>()
        var projectList : ArrayList<project>

        try {

            projectList = ObjectInputStream(FileInputStream(projectPath)).use { it -> it.readObject() as ArrayList<project>}
            println("Project Loaded ")  // Accessging the index from the arrayList
        }catch (ioe : IOException){
            println("Error: Can't load from :\n $projectPath")
            return nullList
        }catch (c: ClassNotFoundException){
            println("Error: Class not found")
            return nullList
        }


        return projectList
    }

    fun loading(bool: Boolean): ArrayList<team> {


        println("$teamPath")

        val nullList = ArrayList<team>()
        var teamList : ArrayList<team>

        try {

            teamList = ObjectInputStream(FileInputStream(teamPath)).use { it -> it.readObject() as ArrayList<team>}
            println("Projects Loaded ")

        }catch (ioe : IOException){
            println("Error: Could not load from file:\n $teamPath")
            return nullList
        }catch (c: ClassNotFoundException){
            println("Error: Class not found")
            return nullList
        }


        return teamList
    }
}