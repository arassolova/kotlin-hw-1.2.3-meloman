fun main() {

    calculateTotalPrice(regularCustomer = true, lastPurchase = 10001.0, currentPurchase = 100.0)
}

fun calculateTotalPrice(
    regularCustomer: Boolean = false,
    lastPurchase: Double = 0.0,
    currentPurchase: Double
) {
    val discountStart = 1_000
    val discountStartForBigPurchase = 10_000
    println("покупка - " + textPriceRubCop(currentPurchase))

    if (regularCustomer) {
        if (lastPurchase <= discountStart) {
            println("после применения 1% скидки - " + textPriceRubCop(calculateRegularDiscountPrice(currentPurchase)))
        } else if (lastPurchase > discountStart && lastPurchase <= discountStartForBigPurchase) {
            println(
                "после применения скидки 100 рублей - "
                        + textPriceRubCop(calculateSmallDiscountPrice(currentPurchase))
                        + "\nпосле применения 1% скидки - "
                        + textPriceRubCop(calculateRegularDiscountPrice(calculateSmallDiscountPrice(currentPurchase)))
            )
        } else {
            println(
                "после применения 5% скидки - "
                        + textPriceRubCop(calculateBigDiscountPrice(currentPurchase))
                        + "\nпосле применения 1% скидки - "
                        + textPriceRubCop(calculateRegularDiscountPrice(calculateBigDiscountPrice(currentPurchase)))
            )
        }
    } else {
        if (lastPurchase <= discountStart) {
            currentPurchase
        } else if (lastPurchase > discountStart && lastPurchase <= discountStartForBigPurchase) {
            println(
                "после применения скидки 100 рублей - "
                        + textPriceRubCop(calculateSmallDiscountPrice(currentPurchase))
            )
        } else {
            println(
                "после применения 5% скидки - "
                        + textPriceRubCop(calculateBigDiscountPrice(currentPurchase))
            )
        }
    }
}

fun calculateSmallDiscountPrice(price: Double): Double {
    val smallDiscount = 100
    return price - smallDiscount
}

fun calculateBigDiscountPrice(price: Double): Double {
    val discount = 0.05
    return price * (1 - discount)
}

fun calculateRegularDiscountPrice(price: Double): Double {
    val discountForRegularCustomer = 0.01
    return price * (1 - discountForRegularCustomer)
}

fun textPriceRub(price: Double): String {
    val priceRub = price.toInt()
    return when (val ostatok100 = price.toInt() % 100) {
        0, in 5..20 -> "$priceRub рублей"
        1 -> "$priceRub рубль"
        2, 3, 4 -> "$priceRub рубля"
        else -> {
            when (ostatok100 % 10) {
                1 -> "$priceRub рубль"
                2, 3, 4 -> "$priceRub рубля"
                else -> "$priceRub рублей"
            }
        }
    }
}

fun textPriceCop(price: Double): String {
    val priceCop = (price * 100).toInt()
    return when (val copeeks = priceCop % 100) {
        0 -> ""
        in 5..20 -> String.format("%02d", copeeks) + " копеек"
        1 -> String.format("%02d", copeeks) + " копейка"
        2, 3, 4 -> String.format("%02d", copeeks) + " копейки"
        else -> {
            when (copeeks % 10) {
                1 -> "$copeeks копейка"
                2, 3, 4 -> "$copeeks копейки"
                else -> "$copeeks копеек"
            }
        }
    }
}

fun textPriceRubCop(price: Double): String {
    return textPriceRub(price) + " " + textPriceCop(price)
}

