package uiLayer

import uiLayer.admin.LogInPage
import uiLayer.student.StudentMenu

class WelcomePage {
    fun welcome() {
        var userIp:Int? = 1
        while (userIp==1||userIp==2)
        {
            println("Admin ----->  1")
            println("Student --->  2")
            println("Exit ------>  3")
            userIp= readln().toIntOrNull()

            if(userIp!=null) {
                when (userIp) {
                    1 -> {
                        val adminLogin = LogInPage()
                        adminLogin.checkPass()
                    }

                    2 -> {
                        val studentMenu=StudentMenu()
                        studentMenu.logIn()
                    }
                    3 -> println("Thank You")
                    else -> {
                        println("Please Enter valid input")
                        userIp = 1
                    }
                }
            }
            else
            {
                println("Please Enter valid Input")
                userIp=1
            }


        }
    }

}