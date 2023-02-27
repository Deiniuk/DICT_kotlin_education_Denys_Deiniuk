fun main() {
    val classCoffee = CoffeeMachine()
    do {
        print("Write action (buy, fill, take, remaining, exit):\n> ")
        when (readln()) {
            "buy" -> classCoffee.buy()
            "fill" -> classCoffee.fill()
            "take" -> classCoffee.take()
            "remaining" -> classCoffee.remaining()
            "exit" -> break
            else -> println("ERROR!")
        }
    }while(true)
}


class CoffeeMachine {
    private var water: Int = 400
    private var milk: Int = 540
    private var beans: Int = 120
    private var money: Int = 550
    private var cups: Int = 9
    private val coffeeMap = mapOf(
        "espresso" to mapOf("water" to 250, "milk" to 0, "beans" to 16, "money" to 4),
        "latte" to mapOf("water" to 350, "milk" to 75, "beans" to 20, "money" to 7),
        "cappuccino" to mapOf("water" to 200, "milk" to 100, "beans" to 12, "money" to 6)
    )

    fun remaining() {
        println("The coffee machine has: \n" +
                water +
                " ml of water \n" +
                milk +
                " ml of milk \n" +
                beans +
                " of coffee beans \n" +
                cups +
                " of disposable cups \n" +
                money +
                " of money")
    }

    fun buy() {
        print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:\n> ")
        when(readln()) {
            "1" -> makeCoffee("espresso")
            "2" -> makeCoffee("latte")
            "3" -> makeCoffee("cappuccino")
            "back" -> main()
            else -> {
                println("ERROR!")
                buy()
            }
        }
    }

    fun fill() {
        print("Write how many ml of water you want to add:\n> ")
        water.plusAssign(readln().toInt())
        print("Write how many ml of milk you want to add:\n> ")
        milk.plusAssign(readln().toInt())
        print("Write how many grams of coffee beans you want to add:\n> ")
        beans.plusAssign(readln().toInt())
        print("Write how many disposable coffee cups you want to add:\n> ")
        cups.plusAssign(readln().toInt())
    }

    fun take() {
        println("I gave you $money")
        money = 0
    }

    private fun makeCoffee(typeCoffee: String) {
        if (water < takeValue(typeCoffee, "water")) {
            println("Sorry, not enough water!")
        }
        if (milk < takeValue(typeCoffee, "milk")) {
            println("Sorry, not enough milk!")
        }
        if (beans < takeValue(typeCoffee, "beans")) {
            println("Sorry, not enough beans!")
        }
        if (cups < 1) {
            println("Sorry, not enough cups!")
        }
        if (water >= takeValue(typeCoffee, "water")
            && milk >= takeValue(typeCoffee, "milk")
            && beans >= takeValue(typeCoffee, "beans")
            && cups >= 1)
        {
            println("I have enough resources, making you a coffee!")
            money += takeValue(typeCoffee, "money")
            water -= takeValue(typeCoffee, "water")
            milk -= takeValue(typeCoffee, "milk")
            beans -= takeValue(typeCoffee, "beans")
            cups.minus(1)
        }
    }

    private fun takeValue(typeCoffee: String, takeIngredients: String) : Int? = coffeeMap[typeCoffee]?.let { it[takeIngredients] }

}

private operator fun Int.minusAssign(let: Int?) {

}

private operator fun Int.plusAssign(let: Int?) {

}

private operator fun Int.compareTo(let: Int?): Int {
    return -1
}