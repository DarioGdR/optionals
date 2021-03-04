package com.dariogdr.com

import kotlinx.coroutines.runBlocking

class Main {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = Main().start()
    }

    fun start() = runBlocking {
        print("I DID IT")
    }

}
