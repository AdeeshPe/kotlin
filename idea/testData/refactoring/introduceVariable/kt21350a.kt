fun test() = "123"
// Does not work yet due to KT-21350
val f = <selection>test()</selection> + "456"

fun foo(test: String) {
    val x = test()
}