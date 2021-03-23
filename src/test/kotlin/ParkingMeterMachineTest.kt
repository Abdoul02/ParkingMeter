import org.junit.Test
import kotlin.test.assertEquals
import kotlin.text.StringBuilder

class ParkingMeterMachineTest {

    @Test
    fun `GIVEN insertAmount is less than 0 WHEN getLeftOver is called,THEN return negative amount message`() {
        val insertAmount = -20
        val response = ParkingMeterMachine.getLeftOver(insertAmount, 5)
        assertEquals(ParkingMeterMachine.NEGATIVE_AMOUNT, response)
    }

    @Test
    fun `GIVEN amountToDeduct is less than 0 WHEN getLeftOver is called,THEN return negative amount message`() {
        val amountToDeduct = -20
        val response = ParkingMeterMachine.getLeftOver(5, amountToDeduct)
        assertEquals(ParkingMeterMachine.NEGATIVE_AMOUNT, response)
    }

    @Test
    fun `GIVEN amountToDeduct is more than insertAmount WHEN getLeftOver is called, THEN return insufficient amount message`() {
        val amountToDeduct = 20
        val insertAmount = 10
        val response = ParkingMeterMachine.getLeftOver(insertAmount, amountToDeduct)
        assertEquals(insufficientAmount(amountToDeduct - insertAmount), response)
    }

    @Test
    fun `GIVEN amountToDeduct is equal to insertAmount WHEN getLeftOver is called,THEN return zero change message`() {
        val amountToDeduct = 20
        val insertAmount = 20
        val response = ParkingMeterMachine.getLeftOver(insertAmount, amountToDeduct)
        assertEquals(ParkingMeterMachine.ZERO_CHANGE, response)
    }

    @Test
    fun `GIVEN amountToDeduct is 0 WHEN getLeftOver is called,THEN return free ticket message`() {
        val amountToDeduct = 0
        val insertAmount = 20
        val response = ParkingMeterMachine.getLeftOver(insertAmount, amountToDeduct)
        assertEquals(ParkingMeterMachine.FREE_TICKET, response)
    }

    @Test
    fun `GIVEN amountToDeduct is 7 and insertAmount is 20 WHEN getLeftOver is called,THEN correct message is shown with right format`() {
        val amountToDeduct = 7
        val insertAmount = 20
        val response = ParkingMeterMachine.getLeftOver(insertAmount, amountToDeduct)
        assertEquals(userChangeWithDenomination(), response)
    }

    @Test
    fun `GIVEN change is 13 WHEN getDenomination is called,THEN return list of pairs (Pair(10,1),Pair(2,1),Pair(1,1))`() {
        val expectedDenomination = listOf(Pair(10, 1), Pair(2, 1), Pair(1, 1))
        val response = ParkingMeterMachine.getDenomination(13)
        assertEquals(expectedDenomination, response)
    }

    private fun userChangeWithDenomination(): String {
        val stringBuilder = StringBuilder()
        return stringBuilder.append("Your change is: R13 \n")
            .append("R10 X 1 \n")
            .append("R2 X 1 \n")
            .append("R1 X 1 \n")
            .toString()
            .trim()
    }

    private fun insufficientAmount(amount: Int) = "You have insufficient fund, you need R$amount more"

}