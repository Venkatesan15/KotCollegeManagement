package uiLayer.admin

class LogInPage{
    fun checkPass()
    {
        print("Please Enter The admin Password  :  ")
        val pass= readln()
        if(pass=="ZOHO")
        {
            val adminMenu=AdminMenu()
            adminMenu.adminMenu()

        }
        else
        {
            println("The password is wrong")
            val a by lazy {
                run select@
                {
                    var ui: Int? = 0
                    while (ui != 1 && ui != 2) {
                        println("Try Again--->1")
                        println("Back--->2")
                        ui = readln().toIntOrNull()

                    }
                    return@select ui
                }
            }
            if(a==1) return checkPass()
        }
    }
}