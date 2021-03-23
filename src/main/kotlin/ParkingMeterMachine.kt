import java.lang.StringBuilder

class ParkingMeterMachine {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val insertAmount = 20
            val amountToDeduct = 7
            print(getLeftOver(insertAmount, amountToDeduct))
        }

        fun getLeftOver(insertAmount: Int, amountToDeduct: Int): String {
            return if (amountToDeduct < 0 || insertAmount < 0) {
                NEGATIVE_AMOUNT
            } else if (amountToDeduct == 0) {
                FREE_TICKET
            } else if (amountToDeduct > insertAmount) {
                val balance = amountToDeduct - insertAmount
                "You have insufficient fund, you need R$balance more"
            } else if (amountToDeduct == insertAmount) {
                ZERO_CHANGE
            } else {
                mapChangeToMessage(insertAmount - amountToDeduct)
            }
        }

        private fun mapChangeToMessage(amount: Int): String {
            val response = StringBuilder()
            response.append("Your change is: R$amount \n")
            val userChange = getDenomination(amount)
            userChange.forEach {
                response.append("R${it.first} X ${it.second} \n")
            }
            return response.toString().trim()
        }

        fun getDenomination(amount: Int): List<Pair<Int, Int>> {
            var totalAmount = amount
            var count: Int
            val zarDenominations = listOf(200, 100, 50, 20, 10, 5, 2, 1) //from R200 to R1
            val change = mutableListOf<Pair<Int, Int>>()
            zarDenominations
                .filter { it <= amount } //No need to loop through value larger than amount
                .forEach { denomination ->
                    count = totalAmount / denomination
                    if (count > 0) {
                        change.add(Pair(denomination, count))
                    }
                    totalAmount %= denomination
                }
            return change
        }

        const val NEGATIVE_AMOUNT = "You can not have negative amounts"
        const val FREE_TICKET = "Your ticket is free, have a nice day"
        const val ZERO_CHANGE = "Your change is R0.00, have a nice day"
    }
}