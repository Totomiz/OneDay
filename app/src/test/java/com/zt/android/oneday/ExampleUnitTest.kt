package com.zt.android.oneday

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        assertEquals(4, (2 + 2).toLong())

        print("ir".equals("iR", true))
        print("ir".equals("IR", true))
        print("ir".equals("Ir", true))
        println(TimeUnit.MILLISECONDS.toSeconds(6300L))
        println(TimeUnit.MILLISECONDS.toSeconds(6500L))
        println(TimeUnit.MILLISECONDS.toSeconds(6900))
        println(TimeUnit.MILLISECONDS.toSeconds(900))
        println(TimeUnit.MILLISECONDS.toSeconds(7000))
        println("------2------------")
        println((6501/1000f))
        println(String.format("%.2f", 6564L/1000f).toFloat())
        println(6511L.millisToSecconds(1))
        println(6511L.millisToSecconds(0))
        println(6511L.millisToSecconds(2))
        println(6501L.millisToSecconds(2))
        println(1580547330000L.millisToSecconds(1))
        println(-564L.millisToSecconds(1))
        println(-564L.millisToSecconds(1))
        println(-544L.millisToSecconds(1))
        println("------------------")
        println((System.currentTimeMillis()-0L).millisToSecconds())
        println((System.currentTimeMillis()-(System.currentTimeMillis()-10)).toBigDecimal().round(MathContext(2)))

        test()
    }

    fun Long.millisToSecconds(scale:Int=1):BigDecimal{
        return String.format("%.${scale}f", this/1000f).toBigDecimal()
//        return (this/1000f).toBigDecimal().round(MathContext(scale))
//        return this.toBigDecimal()
    }

    fun Long.a(scale: Int = 1): Float {
//    ${"#".repeat(scale)}
        return DecimalFormat("#.${"0".repeat(scale)}", DecimalFormatSymbols(Locale.US)).apply { roundingMode = RoundingMode.HALF_UP }.format(this / 1000f).toFloat()
    }

    fun test(){
        for( v in 0..10000000 step 333){

//            println(v.toLong().millisToSecconds())
            println("${v.toLong()} ---${v.toLong().a(2).toLong()}")
        }
    }
}