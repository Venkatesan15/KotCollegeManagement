package uiLayer.admin

interface AdminUI {
    fun addStudent()
    fun getStudentProfile(rollNumber1: String="")
    fun getStudentsList()
    fun showAttendanceMenu()
    fun showFessMenu()
    fun showQueries()
    fun editStudent()
    fun setTimeTable()
    fun showTimeTable()
}