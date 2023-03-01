package dataLayer

import uiLayer.FeesNC
import java.sql.ResultSet

class FeesDL {
    private val con = MySql.getConnection()
    fun addParticular(fees: FeesNC): Boolean =
        try {
            val query = "INSERT INTO FixedFeesDetails(Particulars,Amount,LastDate) values(?,?,?)"
            val st = con?.prepareStatement(query)

            st?.setString(1, fees.particular)
            st?.setLong(2, fees.amount)
            st?.setString(3,  fees.lastDate.toString())
            val row = st?.executeUpdate()
            if (row != null && row != 0) {
                val query1 = "ALTER TABLE StudentFeesTable ADD column `${fees.particular}` ENUM('Paid','Unpaid')"
                st.executeUpdate(query1)
                val query3 = "UPDATE StudentFeesTable SET `${fees.particular}` = 'Unpaid'"
                st.executeUpdate(query3)
                true
            }
            else false
        } catch (e: Exception) {
            println("Exception in FeesDl  :  $e")
            false
        }

    fun showParticular(): ResultSet? =
        try {
            val query = "SELECT * FROM FixedFeesDetails"
            val st = con?.createStatement()

            st?.executeQuery(query)
        } catch (e: Exception) {
            println("Exception in FeesDl : $e")
            null
        }

    fun deleteParticular(particular: String): Boolean =
        try {
            val query = "DELETE FROM FixedFeesDetails WHERE Particulars='$particular'"
            val st = con?.createStatement()
            val affectedRow = st?.executeUpdate(query)

            if (affectedRow != null && affectedRow != 0) {
                val query1 = "ALTER TABLE StudentFeesTable DROP column `$particular`"
                st.executeUpdate(query1)
                true
            }
            else false
        } catch (e: Exception) {
            println("Exception in FeesDL : $e")
            false
        }

    fun showStudentsPaidList(): ResultSet? = try {
        val query = "SELECT * FROM StudentFeesTable"
        val st = con?.createStatement()
        st?.executeQuery(query)
    } catch (e:Exception) {
        println("Exception in FeesDl : $e")
        null
    }

    fun getParticularLists(): ResultSet? = try {
            val query = "SELECT Particulars FROM FixedFeesDetails"
            val st = con?.createStatement()
            st?.executeQuery(query)
        } catch (e: Exception) {
            println("Exception in getParticularListsDL : $e")
            null
        }

    fun insertNewStu(id: Int) {
        try {
            val query = "INSERT into StudentFeesTable(studentId) values($id)"
            val st = con?.createStatement()
            st?.executeUpdate(query)
            val particularList = getParticularLists()
            while (particularList?.next() == true) {
                val query2 = "UPDATE StudentFeesTable set `${particularList.getString("Particulars")}`='Unpaid' where studentId=$id"
                st?.executeUpdate(query2)
            }
        } catch (e: Exception) {
            println("Exception in FeesDl : $e")
        }
    }




}