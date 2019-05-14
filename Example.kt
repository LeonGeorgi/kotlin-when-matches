fun main() {
    val string = "Hello World!"
    when (string.matches) {
        "Hello" -> println("string is Hello");
        { it: String -> "World" in it } -> println("string contains World")
        else -> println("string is not matching")
    }
}
