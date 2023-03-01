package uiLayer.admin

import middleLayer.FeesDetailsML
import middleLayer.StudentsDetails
import uiLayer.FeesNC
import java.sql.ResultSet
import java.time.LocalDate

class Fees {
    private val feesDetailML = FeesDetailsML()
    private val a by lazy {
        run select@ {
            var ui: Int? = 0
            while (ui != 1 && ui != 2) {
                println("Try Again--->1")
                println("Back--->2")
                ui = readln().toIntOrNull()

            }
            return@select ui
        }
    }
    fun showParticular() {
        val rs:ResultSet? = feesDetailML.showParticulars()
        var particular: String
        var amount: Long
        var date: LocalDate
        if (rs?.next() == false) return println("Particular List is Empty...")

        System.out.printf("%-20s  :  %-13s  :  %-15s", "Particulars", "Amount", "Last Date")
        println()
        do{
            particular = rs!!.getString("Particulars")
            amount = rs.getLong("Amount")
            date = LocalDate.parse(rs.getString("LastDate"))

            val feesObj = FeesNC(particular, amount, date)

            System.out.printf("%-20s  :  %-13s  :  %-15s", feesObj.particular, feesObj.amount, feesObj.lastDate)
            println()
        }while (rs?.next() == true)
        println()
    }
    fun deleteParticular() {
        print("Enter Particular Name  :  ")
        val particular= readln()
        if(feesDetailML.deleteParticular(particular)) {
            println("Successfully Deleted")
        }
        else {
            println("The Particular not in the list")
            if(a==1) return deleteParticular()
        }
    }
    fun addParticular() {
        print("Enter Particular Name : ")
        val particular = readln()
        print("Enter Amount : ")
        var amount: Long? = readln().toLongOrNull()
        while (amount == null) {
            println("Please Enter valid Input ")
            amount = readln().toLongOrNull()
        }
        print("Enter Last Date(yyyy-MM-dd)   : " )
        val lastDate: LocalDate? = run getDate@ {
            while (true) {
                try {
                    return@getDate LocalDate.parse(readln())
                } catch (e: Exception) {
                    println("Please enter valid date format")
                }
            }
        } as LocalDate?

        val feesObj = FeesNC(particular, amount, lastDate!!)
        feesDetailML.addParticular(feesObj)
        println("SuccessFully added ")

    }

    fun showStudentsPaidStatus() {
        val particularList:ResultSet? = feesDetailML.getParticularList()
        System.out.printf("%-15s : %-15s ","Roll Number","Name")
        while (particularList?.next() == true) {
            System.out.printf(" : %-20s", particularList.getString("Particulars"))
        }
        println()
        val studentsDetails = StudentsDetails()
        val paidList = feesDetailML.showStudentPaidLists()
        var studentId: Int?
        try {
            while (paidList?.next() == true) {
                studentId = paidList.getInt("studentId")
                val stuNameAndRollNo = studentsDetails.getStuDetailById(studentId)
                stuNameAndRollNo?.next()
                System.out.printf("%-15s : %-15s ", stuNameAndRollNo?.getString("RollNumber"), stuNameAndRollNo?.getString("Name"))
                val particularList2=feesDetailML.getParticularList()
                while (particularList2?.next() == true) {
                    val particular = particularList2.getString("Particulars")
                    System.out.printf(" : %-20s", paidList.getString(particular))
                }
                println()
            }
            println()
        }
        catch (e: Exception) {
            println("Exception in FeesUI : $e")
        }
    }

}