# Better when statements

## Regular `when` statements

When statements in Kotlin can be defined in two ways. Both have disadvantages.

### With subject

```kotlin
val string = "Hello World!"
val i = when (string) {
    "Hello" -> 1
    "World" -> 2
    in listOf("foo") -> 3
    else -> 4
}
```

This syntax only allows equality, contains and instance checks. However it is
not possible to check anything different like checking if the length of the
string is even. For that we have to use `when` without argument.


### Without subject

```kotlin
val string = "Hello World!"
val i = when {
    string == "Hello" -> 1
    string.length % 2 == 0 -> 2
    string in listOf("foo") -> 3
    else -> 4
}
```

This syntax gives you more freedom. You can check anything you want. But it's a
bit verbose, since you have to use the variable `string` in every line.

## `when` statements with the extension

The extension makes when statements work as follows:

```kotlin
val string = "Hello World!"
val i = when (string.matches) {
    "Hello" -> 1
    String::isNotEmpty -> 2
    { it: String -> it.length % 2 == 0 } -> 3
    else -> 4
}
```

When you call the getter of the property `matches` on an object, you can also
use method references to check a predicate. As consequence you can also use
lambdas to check if a predicate is fullfilled.

You may ask why this works. This feature is an abuse of the `equals` function.
The function `matches` returns an object, which overrides the `equals` method
such that it returns `true`, if this value equals the other value or if the other
value is a predicate (a function taking the value's type as argument and returns
a `Boolean`) which returns `true` for this value.

For the exact implementation have a look at the file `Match.kt`.
