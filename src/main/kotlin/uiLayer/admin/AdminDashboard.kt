package uiLayer.admin
import middleLayer.StudentsDetails
import uiLayer.Gender
import uiLayer.StudentNC
import validator.Validator

class AdminDashboard :AdminUI{
    override fun addStudent()
    {
        val validator=Validator()
        val rollNumber= validator.getRollNumber()
        val name= validator.getName()
        val gender:Gender=validator.getGender()
        val phoneNumber =validator.getPhoneNumber()
        val dob= validator.getDOB()
        val age=validator.getAge(dob)

        val studentObj=StudentNC(rollNumber,name,gender,phoneNumber,dob,age)

        val studentsDetails=StudentsDetails()
        studentsDetails.insertStudent(studentObj)
    }
    override fun getStudentProfile(rollNumber1:String)
    {
        var rollNumber=rollNumber1
        if(rollNumber.isEmpty()) {
             print("Enter Roll Number  :  ")
             rollNumber= readln().uppercase()
        }

        val studentDetailsMl=StudentsDetails()
        if(studentDetailsMl.rollNoExists(rollNumber))
        {
            val student=studentDetailsMl.getStudentProfile(rollNumber)


            val showStudentProfile=StudentProfile()
            showStudentProfile.showStudentProfile(student!!)

        }
        else
        {
            println("This RollNumber is Not in the Students List")
        }
    }
    override fun getStudentsList()
    {
        val studentsDetails=StudentsDetails()
        val studentList=studentsDetails.getStudentsList()
        val showStudentProfile=StudentProfile()

        showStudentProfile.showStudentProfile(studentList!!)

    }
    override fun showAttendanceMenu()
    {
        val attendance=Attendance()
        var userIp:Int?=1
        while (userIp in 1..3) {
            println("Attendance History By date        --->  1")
            println("Attendance History By Roll Number --->  2")
            println("Take Attendance                   --->  3")
            println("Back                              --->  4")
            userIp= readln().toIntOrNull()
            if(userIp!=null && userIp in 1..4) {

                when(userIp)
                {
                    1 -> attendance.getHistByDate()
                    2 -> attendance.getHistByRollNo()
                    3 -> attendance.takeAttendance()
                }

            }
            else
            {
                println("Please enter valid input")
                userIp=1
            }

        }
    }
    override fun showFessMenu()
    {
        val feesFees=Fees()
        var userIp:Int?=1
        while (userIp in 1..4) {
            println("Show Particulars         --->  1")
            println("Delete particular        --->  2")
            println("Add Particular           --->  3")
            println("Show student Paid Status --->  4")
            println("Back                     --->  5")
            userIp= readln().toIntOrNull()
            if(userIp!=null && userIp in 1..5) {

                when(userIp)
                {
                    1 -> feesFees.showParticular()
                    2 -> feesFees.deleteParticular()
                    3 -> feesFees.addParticular()
                    4 -> feesFees.showStudentsPaidStatus()
                }

            }
            else
            {
                println("Please enter valid input")
                userIp=1
            }
            println()

        }
    }

    override fun showQueries()
    {
        val complaints=Complaints()
        var ui:Int?=1
        while (ui in 1..2)
        {
            println("Show Complaints  ---->   1")
            println("Delete Complaints --->   2")
            println("Back              --->   3")
            ui= readln().toIntOrNull()

            if(ui!=null && ui in 1..3)
            {
                when(ui)
                {
                    1 -> complaints.showComplaints()
                    2 -> complaints.clearQueries()
                }
            }
            else
            {
                println("Please enter valid input")
                ui=1
            }
        }
    }
    override fun editStudent()
    {
        val editStudentDetail=EditStudentDetail()
        print("Student RollNumber : ")
        val rollNumber:String?= readlnOrNull()
        if(!rollNumber.isNullOrEmpty())
        {
            editStudentDetail.edit(rollNumber)
        }
        else
        {
            println("Please enter valid input ")
            return editStudent()
        }
    }
    override fun setTimeTable()
    {
        val timeTable=TimeTable()
        var ui:Int?=1
        while (ui in 1..4)
        {
            println("Add New Time Table      ---->   1")
            println("Delete TimeTable        ---->   2")
            println("Edit By Day Order       ---->   3")
            println("Add one extra Day Order ---->   4")
            println("Back                    ---->   5")
            ui= readln().toIntOrNull()

            if(ui!=null && ui in 1..5)
            {
                when(ui)
                {
                    1 -> timeTable.setTimeTable()
                    2 -> timeTable.delete()
                    3 -> timeTable.editByDayOrder()
                    4 -> timeTable.addExtraDayOrder()
                }
            }
            else
            {
                println("Please enter valid input")
                ui=1
            }
        }
    }
    override fun showTimeTable(){
        val timeTable=TimeTable()
        timeTable.showTimeTable()
    }
}