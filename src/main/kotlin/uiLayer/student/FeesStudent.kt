package uiLayer.student

import middleLayer.FeesDetailsML
import middleLayer.FeesStudentML
import uiLayer.admin.Fees

class FeesStudent {
    private val feesML = FeesStudentML()
    fun fees(studentId: Int) {
        var ui: Int? = 1
        while (ui in 1..2) {
            println("Show Particulars  ---> 1")
            println("Pay fees          ---> 2")
            println("Back              ---> 3")
            ui = readln().toIntOrNull()
            if (ui != null && ui in 1..3) {
                when(ui) {
                    1 ->{
                        val fees = Fees()
                        fees.showParticular()
                    }
                    2 -> payFees(studentId)
                }
            }
            else {
                println("Please enter valid Input")
                return fees(studentId)
            }
        }
    }

    private fun payFees(id: Int) {
        try {
            val rs = feesML.getUnpaidList(id)
            rs?.next()
            val feesDetails = FeesDetailsML()
            val particularList = feesDetails.getParticularList()
            var particular: String?
            if (particularList != null) {
                val list = mutableListOf<String>()
                var c = 0
                while (particularList.next()) {
                    particular = particularList.getString("Particulars")
                    if (rs?.getString(particular) == "Unpaid") {
                        c++
                        list.add(particular)
                        System.out.printf("%-20s --->  %d", particular, c)
                        println()
                    }
                }
                println()
                if (c == 0) {
                    println("No Fees Available or You Paid all the Fees1" +
                            "")
                    println()
                    return
                }
                val particularNo = readln().toIntOrNull()

                if (particularNo != null && particularNo in  1..c) {
                    val particularName = list[particularNo-1]
                    feesML.setPaid(id, particularName)
                    println("Successfully Paid")
                    list.removeAt(particularNo - 1)
                    println()
                }
                else {
                    println("Please enter valid Input ")
                    return payFees(id)
                }
            }
        }
        catch (e:Exception) {
            println("Exception in FeesStudentUI : $e")
        }
    }

}