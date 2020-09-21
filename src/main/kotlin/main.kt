fun main() {
    val price = 100
    val count = 10

    val regularCustomer = true
    val lastPurchase = 11_000 //сумма ПРЕДЫДУЩЕЙ покупки, по условиям задачи от нее зависят скидки

    val discountStart = 1_000
    val discount = 100

    val discountStartBigPurchase = 10_000
    val discountBigPurchase = 0.05

    val discountForRegularCustomer = 0.01

    val currentPurchase = price * count

    val currentPurchaseResult =
        calculateTotalPrice(
            regularCustomer,
            lastPurchase,
            discountStart,
            currentPurchase,
            discountForRegularCustomer,
            discountStartBigPurchase,
            discount,
            discountBigPurchase
        )

    println("Сумма покупки: $currentPurchase"
            +"\nСумма покупки со скидками: $currentPurchaseResult"
    )
}

private fun calculateTotalPrice(
    regularCustomer: Boolean,
    lastPurchase: Int,
    discountStart: Int,
    currentPurchase: Int,
    discountForRegularCustomer: Double,
    discountStartBigPurchase: Int,
    discount: Int,
    discountBigPurchase: Double
): Number {
    return if (regularCustomer) {
        if (lastPurchase <= discountStart) currentPurchase - currentPurchase * discountForRegularCustomer
        else if (lastPurchase > discountStart && lastPurchase <= discountStartBigPurchase) {
            (currentPurchase - discount) - (currentPurchase - discount) * discountForRegularCustomer
        } else {
            (currentPurchase - currentPurchase * discountBigPurchase) - (currentPurchase - currentPurchase * discountBigPurchase) * discountForRegularCustomer
        }
    } else {
        if (lastPurchase <= discountStart) {
            currentPurchase
        } else if (lastPurchase > discountStart && lastPurchase <= discountStartBigPurchase) {
            currentPurchase - discount
        } else {
            currentPurchase - currentPurchase * discountBigPurchase
        }
    }
}