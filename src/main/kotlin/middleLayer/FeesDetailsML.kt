package middleLayer
import dataLayer.FeesDL
import uiLayer.FeesNC
import java.sql.ResultSet

class FeesDetailsML {
    private val feesDetailDL=FeesDL()
    fun showParticulars():ResultSet?=feesDetailDL.showParticular()
    fun addParticular(feesObj :FeesNC):Boolean=feesDetailDL.addParticular(feesObj)
    fun deleteParticular(particular :String):Boolean=feesDetailDL.deleteParticular(particular)
    fun showStudentPaidLists():ResultSet?=feesDetailDL.showStudentsPaidList()
    fun getParticularList():ResultSet?=feesDetailDL.getParticularLists()
}