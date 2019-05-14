val <T> T.matches: Any
    get() = Match(this)

class Match<T>(private val value: T) {
    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        if (!Function1::class.isInstance(other)) {
            return value == other
        }

        return try {
            @Suppress("UNCHECKED_CAST")
            (other as Function1<T, Boolean>).invoke(value)
        } catch (_: ClassCastException) {
            false
        }

    }

    override fun hashCode() = value.hashCode()
}
