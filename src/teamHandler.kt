class teamHandler {
    var teams = ArrayList<team>()
    var pers = persistence();

    fun newTeam(teamName: String, teamDescription: String) {
        teams.add(team(teamName, teamDescription))
        println("this works")
        saveNewTeam()
    }

    fun getTeam(teamName: String): team {
        teams.forEach { team ->
            if (teamName == team.teamName) {
                return team
            }
        }
        return teams[0]
    }

    // The delete team form isnt ready yet so no need to call it

    fun deleteTeam(teamIndex: Int) {
        teams.removeAt(teamIndex)
        saveNewTeam()
    }


    fun loadExistingTeam() {
        teams = pers.loading(true)
        println(teams)
        println("Team: " + teams[0] + " is loaded")
    }

    fun saveNewTeam() {
        pers.saving(teams, true)
        println(teams)
    }

    init {
        teams.add(team("N/A", "No team assigned"))
        loadExistingTeam()
    }
}