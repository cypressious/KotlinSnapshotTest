package com.cypressworks.kotlinicdaggerissue

class Foo {
    override fun toString() = "Hello!!!!"

    fun bar() {

    }
}

object Obj {
    @JvmField val foo = intArrayOf()

    @JvmStatic private fun priv() = foo

    @JvmStatic fun pub() = with(Any()) { priv() }

}