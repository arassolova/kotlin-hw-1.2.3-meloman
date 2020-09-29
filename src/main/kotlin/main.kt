fun main() {
    val regularCustomer = true
    val currentPurchase = 100.0
    val bigDiscountPrice = calculateBigDiscountPrice(currentPurchase)
    val regularCustomerPrice = calculateRegularDiscountPrice(bigDiscountPrice)

    println("покупка - " + textPriceRubCop(currentPurchase)
            + "\nпосле применения 5% скидки - " + textPriceRubCop(bigDiscountPrice)
            + "\nпосле применения 1% скидки - " + textPriceRubCop(regularCustomerPrice))
}

fun calculateTotalPrice(
    regularCustomer: Boolean = false,
    lastPurchase: Double = 0.0,
    currentPurchase: Double
): Double {
    val discountStart = 1_000
    val discountStartForBigPurchase = 10_000

    return if (regularCustomer) {
        if (lastPurchase <= discountStart) calculateRegularDiscountPrice(currentPurchase)
        else if (lastPurchase > discountStart && lastPurchase <= discountStartForBigPurchase) {
            calculateRegularDiscountPrice(calculateSmallDiscountPrice(currentPurchase))
        } else {
            calculateRegularDiscountPrice(calculateBigDiscountPrice(currentPurchase))
        }
    } else {
        if (lastPurchase <= discountStart) {
            currentPurchase
        } else if (lastPurchase > discountStart && lastPurchase <= discountStartForBigPurchase) {
            calculateSmallDiscountPrice(currentPurchase)
        } else {
            calculateBigDiscountPrice(currentPurchase)
        }
    }
}
fun calculateSmallDiscountPrice(price: Double): Double {
    val smallDiscount = 100
    return price - smallDiscount
}

fun calculateBigDiscountPrice(price: Double): Double {
    val discount = 0.05
    return price * (1-discount)
}

fun calculateRegularDiscountPrice(price: Double): Double {
    val discountForRegularCustomer = 0.01
    return price * (1 - discountForRegularCustomer)
}

fun textPriceRub(price: Double): String{
    val priceRub = price.toInt()
    return when(val ostatok100 = price.toInt()%100){
        0, in 5..20 -> "$priceRub рублей"
        1 -> "$priceRub рубль"
        2, 3, 4 -> "$priceRub рубля"
        else -> {
            when(val ostatok10 = ostatok100%10) {
                1 -> "$priceRub рубль"
                2, 3, 4 -> "$priceRub рубля"
                else -> "$priceRub рублей"
            }
        }
    }
}

fun textPriceCop(price: Double): String{
    val priceCop = (price * 100).toInt()
    val copeeks = priceCop%100
    return when(copeeks){
        0, in 5..20 -> "$copeeks копеек"
        1 -> "$copeeks копейка"
        2, 3, 4 -> "$copeeks копейки"
        else -> {
            when(val ostatok10 = copeeks%10) {
                1 -> "$copeeks копейка"
                2, 3, 4 -> "$copeeks копейки"
                else -> "$copeeks копеек"
            }
        }
    }
}

fun textPriceRubCop(price: Double): String{
    return textPriceRub(price) + " " + textPriceCop(price)
}

/* println("Сумма покупок для постоянного покупателя:"
         + "\nСумма покупки без скидки за предыдущие покупки: "
         + calculateTotalPrice(true, 0.0, 1_000.0)
         + "\nСумма покупки со скидкой за предыдущие покупки от 1_000: "
         + calculateTotalPrice(true, 5_000.0, 1_000.0)
         + "\nСумма покупки со скидкой за предыдущие покупки от 10_000: "
         + calculateTotalPrice(true, 11_000.0, 1_000.0)
 )
 println()
 println("Сумма покупок для непостоянного покупателя:"
         + "\nСумма покупки без скидки за предыдущие покупки: "
         + calculateTotalPrice(currentPurchase = 1000.0)
         + "\nСумма покупки со скидкой за предыдущие покупки от 1_000: "
         + calculateTotalPrice(lastPurchase = 5_000.0, currentPurchase = 1_000.0)
         + "\nСумма покупки со скидкой за предыдущие покупки от 10_000: "
         + calculateTotalPrice(lastPurchase = 11_000.0, currentPurchase = 1_000.0)
 )*/
/*fun calculateTotalPrice(
    regularCustomer: Boolean = false,
    lastPurchase: Double = 0.0,
    currentPurchase: Double
): Double {
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
}*/
