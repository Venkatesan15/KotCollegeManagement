package uiLayer.admin

class AdminMenu {
    fun adminMenu() {
        val adminDashboard: AdminUI = AdminDashboard()

        var userIp: Int? = 1
        while(userIp in 1..9) {
            println("*****************************************")
            println("**     Student Profile---------->1     **")
            println("**     Add New Joiners---------->2     **")
            println("**     Display Students List---->3     **")
            println("**     Attendance--------------->4     **")
            println("**     Fees Details------------->5     **")
            println("**     Show Queries Received---->6     **")
            println("**     Edit Student Details----->7     **")
            println("**     Set Time Table----------->8     **")
            println("**     Show Time Table---------->9     **")
            println("**     Exit--------------------->10    **")
            println("*****************************************")
            userIp = readln().toIntOrNull()
            when (userIp) {
                1 -> adminDashboard.getStudentProfile()
                2 -> adminDashboard.addStudent()
                3 -> adminDashboard.getStudentsList()
                4 -> adminDashboard.showAttendanceMenu()
                5 -> adminDashboard.showFessMenu()
                6 -> adminDashboard.showQueries()
                7 -> adminDashboard.editStudent()
                8 -> adminDashboard.setTimeTable()
                9 -> adminDashboard.showTimeTable()
                10 -> return
                else -> {
                    println("Please Enter valid input")
                    userIp = 1
                }
            }
        }
    }
}