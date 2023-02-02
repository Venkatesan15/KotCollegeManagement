package dataLayer

import java.sql.ResultSet
import java.time.LocalDate

class ComplaintsDL {
    private val con=MySql.getConnection()
    fun getComplaints():ResultSet?=try {
        val query="SELECT * FROM Queries"
        val st=con?.createStatement()
        st?.executeQuery(query)
    }
    catch (e:Exception)
    {
        println("Exception in getComplaints : $e")
        null
    }

    fun clearComplaints()=try {
        val query="TRUNCATE TABLE Queries"
        val st=con?.createStatement()
        st?.executeQuery(query)
    }
    catch (e:Exception)
    {
        println("Exception in delete Complaints : $e")
    }

    fun insertQuery(id:Int,query:String)=try {
        val query1="INSERT INTO Queries(Date,Query,studentId) VALUES('${LocalDate.now()}','$query',$id)"
        val st=con?.createStatement()
        st?.executeUpdate(query1)
    }
    catch (e:Exception)
    {
        println("Exception in Insert Complaints : $e")
    }


}