package dataLayer

import java.sql.ResultSet

class FeesStudentDL {
    private val con=MySql.getConnection()
    fun showUnpaid(id : Int):ResultSet?= try {
        val query="SELECT * FROM StudentFeesTable WHERE studentId=$id"
        val st=con?.createStatement()
        st?.executeQuery(query)
        }
    catch (e:Exception)
    {
        println("Exception in FeesStudent")
        null
    }
    fun setPaid(id:Int,columnName :String)=try {
        val query="UPDATE StudentFeesTable set `$columnName`='Paid' where studentId=$id"
        val st=con?.createStatement()
        st?.executeUpdate(query)
    }
    catch (e:Exception)
    {
        println("Exception in FeesStudentDl  : $e")
    }
}