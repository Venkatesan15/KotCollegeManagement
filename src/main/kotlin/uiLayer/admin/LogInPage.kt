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
            println("Try Again --->  1")
            println("Back -------->  2")
            var userIp=1
            while (userIp==1||userIp==2)
            {
                userIp= readln().toInt()
                if(userIp==1)
                {
                    checkPass()
                }
                else if(userIp==2)
                {
                    return
                }
                else
                {
                    println("Please enter valid Input")
                    userIp=1
                }
            }
        }
    }
}