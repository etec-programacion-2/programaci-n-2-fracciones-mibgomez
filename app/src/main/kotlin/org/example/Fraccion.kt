package org.example

import kotlin.math.abs

class Fraccion(
    numerador: Int,
    denominador: Int
) {

    var numerador: Int = numerador

    var denominador: Int = denominador
        get() = field
        set(value) { 
            if (value == 0) throw IllegalArgumentException("El denominador no puede ser cero")
            field = value 
        }

    init {
        if (denominador == 0) {
            throw IllegalArgumentException("El denominador no puede ser cero")
        }
        if (this.denominador < 0) {
            this.numerador = -this.numerador
            this.denominador = -this.denominador
        }
    }

    private fun mcd(a: Int, b: Int): Int {
        var num1 = abs(a)
        var num2 = abs(b)
        
        while (num2 != 0) {
            val temp = num2
            num2 = num1 % num2
            num1 = temp
        }
        return num1
    }

    private fun simplificar(): Fraccion {
        val divisorComun = mcd(numerador, denominador)
        return Fraccion(numerador / divisorComun, denominador / divisorComun)
    }

    operator fun plus(otra: Fraccion): Fraccion {
        if (this.denominador == 0 || otra.denominador == 0) {
            throw IllegalArgumentException("No se puede operar con fracciones inválidas")
        }
        val nuevoNumerador = this.numerador * otra.denominador + this.denominador * otra.numerador
        val nuevoDenominador = this.denominador * otra.denominador
        
        return Fraccion(nuevoNumerador, nuevoDenominador).simplificar()
    }

    operator fun minus(otra: Fraccion): Fraccion {
        if (this.denominador == 0 || otra.denominador == 0) {
            throw IllegalArgumentException("No se puede operar con fracciones inválidas")
        }
        
        val nuevoNumerador = this.numerador * otra.denominador - this.denominador * otra.numerador
        val nuevoDenominador = this.denominador * otra.denominador
        
        return Fraccion(nuevoNumerador, nuevoDenominador).simplificar()
    }

    override fun toString(): String {
        return "$numerador / $denominador"
    }

    fun mostrar(): String {
        return "$numerador / $denominador"
    }
}