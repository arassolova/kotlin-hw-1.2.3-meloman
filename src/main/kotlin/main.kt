fun main() {

    println("Сумма покупок для постоянного покупателя:"
            + "\nСумма покупки без скидки за предыдущие покупки: "
            + calculateTotalPrice(true, 0, 1_000)
            + "\nСумма покупки со скидкой за предыдущие покупки от 1_000: "
            + calculateTotalPrice(true, 5_000, 1_000)
            + "\nСумма покупки со скидкой за предыдущие покупки от 10_000: "
            + calculateTotalPrice(true, 11_000, 1_000)
    )
    println()
    println("Сумма покупок для непостоянного покупателя:"
            + "\nСумма покупки без скидки за предыдущие покупки: "
            + calculateTotalPrice(currentPurchase = 1000)
            + "\nСумма покупки со скидкой за предыдущие покупки от 1_000: "
            + calculateTotalPrice(lastPurchase = 5_000, currentPurchase = 1_000)
            + "\nСумма покупки со скидкой за предыдущие покупки от 10_000: "
            + calculateTotalPrice(lastPurchase = 11_000, currentPurchase = 1_000)
    )
}

fun calculateTotalPrice(
    regularCustomer: Boolean = false,
    lastPurchase: Int = 0,
    currentPurchase: Int
): Number {
    val discountStart = 1_000
    val discount = 100
    val priceWithSmallDiscount = currentPurchase - discount

    val discountStartForBigPurchase = 10_000
    val discountForBigPurchase = 0.05
    val priceWithBigDiscount = currentPurchase * (1-discountForBigPurchase)

    val discountForRegularCustomer = 0.01

    return if (regularCustomer) {
        if (lastPurchase <= discountStart) currentPurchase * (1 - discountForRegularCustomer)
        else if (lastPurchase > discountStart && lastPurchase <= discountStartForBigPurchase) {
            priceWithSmallDiscount * (1 - discountForRegularCustomer)
        } else {
            priceWithBigDiscount * (1 - discountForRegularCustomer)
        }
    } else {
        if (lastPurchase <= discountStart) {
            currentPurchase
        } else if (lastPurchase > discountStart && lastPurchase <= discountStartForBigPurchase) {
            priceWithSmallDiscount
        } else {
            priceWithBigDiscount
        }
    }
}
