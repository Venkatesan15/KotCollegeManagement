package uiLayer

import uiLayer.admin.LogInPage
import uiLayer.student.StudentMenu

class WelcomePage {
    fun welcome() {

        val adminLogin = LogInPage()
        val studentMenu = StudentMenu()
        var userIp:Int? = 1

        while (userIp in 1..2) {
            println("Admin ----->  1")
            println("Student --->  2")
            println("Exit ------>  3")
            userIp = readln().toIntOrNull()

            when (userIp) {
                1    -> adminLogin.checkPass()

                2    -> studentMenu.logIn()

                3    -> println("Thank You")
                else -> {
                    println("Please Enter valid input")
                    userIp = 1
                }
            }
        }
    }

}