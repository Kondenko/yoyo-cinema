package com.vladimirkondenko.yoyocinema

import com.vladimirkondenko.yoyocinema.di.modules
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.test.KoinTest

class DiTest : KoinTest {

    @Test
    fun testKoin() {
        startKoin(modules)
        stopKoin()
    }

}