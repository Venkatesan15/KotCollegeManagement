package dataLayer

import java.sql.Connection
import java.sql.DriverManager

class MySql {
    companion object
    {
        private var con: Connection?=null
        init {
            con=try {
                val url = "jdbc:mysql://localhost:3306/CollegeManagement"
                val un = "root"
                val pass = "Jayaramang@1"
                DriverManager.getConnection(url, un, pass)
            } catch (e: Exception) {
                println(e)
                null
            }
        }
        fun getConnection(): Connection? {
            return con
        }
    }
}