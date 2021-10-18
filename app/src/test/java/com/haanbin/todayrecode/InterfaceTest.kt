package com.haanbin.todayrecode

interface InterfaceTest {


    val test: String

    val test2: String
        get() = "123"

    fun test(): String

    fun test2(): String {
        return test
    }

}


class ImplTest : InterfaceTest {

    lateinit var test213: String
    val trestasdsdf: Int by lazy { 1 }

    fun testset(){
        object : InterfaceTest {
            override val test: String
                get() = TODO("Not yet implemented")

            override fun test(): String {
                TODO("Not yet implemented")
            }
        }
        val interfaceTest: InterfaceTest = object : InterfaceTest {
            override val test: String
                get() = TODO("Not yet implemented")

            override fun test(): String {
                TODO("Not yet implemented")
            }

        }
    }
    override val test: String
        get() = "1"

    override fun test(): String {
        TODO("Not yet implemented")
    }

    override val test2: String
        get() = super.test2

    override fun test2(): String {
        return if (test == "123") {
            super.test2()
        } else {
            test
        }
    }
}