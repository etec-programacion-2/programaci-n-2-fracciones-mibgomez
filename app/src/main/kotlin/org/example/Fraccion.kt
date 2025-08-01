package org.example

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.round

/**
 * Clase que representa una fracción matemática con numerador y denominador
 * Implementa operaciones aritméticas básicas y métodos de comparación
 * 
 * @param numerador El numerador de la fracción
 * @param denominador El denominador de la fracción (no puede ser cero)
 */
class Fraccion(
    numerador: Int,
    denominador: Int
) : Comparable<Fraccion> {

    companion object {
        /**
         * Convierte un número decimal a fracción
         * @param decimal El número decimal a convertir
         * @param precision La precisión deseada (número de decimales a considerar)
         * @return Una nueva fracción que representa el decimal
         * @throws IllegalArgumentException si el decimal es NaN o Infinito
         */
        fun desdeDecimal(decimal: Double, precision: Int = 6): Fraccion {
            if (decimal.isNaN() || decimal.isInfinite()) {
                throw IllegalArgumentException("No se puede convertir NaN o Infinito a fracción")
            }
            
            val factor = 10.0.pow(precision).toInt()
            val numerador = (decimal * factor).toInt()
            
            return Fraccion(numerador, factor)
        }
    }

    /**
     * Numerador de la fracción
     */
    var numerador: Int = numerador
        private set

    /**
     * Denominador de la fracción (no puede ser cero)
     */
    var denominador: Int = denominador
        private set

    init {
        if (denominador == 0) {
            throw IllegalArgumentException("El denominador no puede ser cero")
        }
        normalizarSigno()
    }

    /**
     * Normaliza el signo de la fracción: el denominador siempre será positivo
     */
    private fun normalizarSigno() {
        if (this.denominador < 0) {
            this.numerador = -this.numerador
            this.denominador = -this.denominador
        }
    }

    /**
     * Calcula el máximo común divisor usando el algoritmo de Euclides
     * @param a Primer número
     * @param b Segundo número
     * @return El máximo común divisor de a y b
     */
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

    /**
     * Simplifica la fracción dividiéndola por su MCD
     * @return Una nueva fracción simplificada
     */
    private fun simplificar(): Fraccion {
        val divisorComun = mcd(numerador, denominador)
        return Fraccion(numerador / divisorComun, denominador / divisorComun)
    }

    /**
     * Valida que una fracción sea válida para operaciones
     * @param fraccion La fracción a validar
     * @throws IllegalArgumentException si la fracción no es válida
     */
    private fun validarFraccion(fraccion: Fraccion) {
        if (fraccion.denominador == 0) {
            throw IllegalArgumentException("No se puede operar con fracciones inválidas")
        }
    }

    /**
     * Operador suma: (a/b) + (c/d) = (a*d + b*c)/(b*d)
     * @param otra La fracción a sumar
     * @return Una nueva fracción con el resultado de la suma
     */
    operator fun plus(otra: Fraccion): Fraccion {
        validarFraccion(otra)
        
        val nuevoNumerador = this.numerador * otra.denominador + this.denominador * otra.numerador
        val nuevoDenominador = this.denominador * otra.denominador
        
        return Fraccion(nuevoNumerador, nuevoDenominador).simplificar()
    }

    /**
     * Operador resta: (a/b) - (c/d) = (a*d - b*c)/(b*d)
     * @param otra La fracción a restar
     * @return Una nueva fracción con el resultado de la resta
     */
    operator fun minus(otra: Fraccion): Fraccion {
        validarFraccion(otra)
        
        val nuevoNumerador = this.numerador * otra.denominador - this.denominador * otra.numerador
        val nuevoDenominador = this.denominador * otra.denominador
        
        return Fraccion(nuevoNumerador, nuevoDenominador).simplificar()
    }

    /**
     * Operador multiplicación: (a/b) * (c/d) = (a*c)/(b*d)
     * @param otra La fracción a multiplicar
     * @return Una nueva fracción con el resultado de la multiplicación
     */
    operator fun times(otra: Fraccion): Fraccion {
        validarFraccion(otra)
        
        val nuevoNumerador = this.numerador * otra.numerador
        val nuevoDenominador = this.denominador * otra.denominador
        
        return Fraccion(nuevoNumerador, nuevoDenominador).simplificar()
    }

    /**
     * Operador división: (a/b) / (c/d) = (a*d)/(b*c)
     * Equivale a multiplicar por el recíproco de la segunda fracción
     * @param otra La fracción divisor
     * @return Una nueva fracción con el resultado de la división
     * @throws ArithmeticException si se intenta dividir por cero
     */
    operator fun div(otra: Fraccion): Fraccion {
        validarFraccion(otra)
        
        if (otra.numerador == 0) {
            throw ArithmeticException("No se puede dividir por una fracción con numerador cero (división por cero)")
        }
        
        val nuevoNumerador = this.numerador * otra.denominador
        val nuevoDenominador = this.denominador * otra.numerador
        
        return Fraccion(nuevoNumerador, nuevoDenominador).simplificar()
    }

    /**
     * Compara esta fracción con otra para determinar orden
     * @param other La fracción a comparar
     * @return -1 si es menor, 0 si son iguales, 1 si es mayor
     */
    override fun compareTo(other: Fraccion): Int {
        validarFraccion(other)
        
        // Comparamos usando productos cruzados para evitar divisiones
        val valor1 = this.numerador * other.denominador
        val valor2 = other.numerador * this.denominador
        
        return valor1.compareTo(valor2)
    }

    /**
     * Verifica si esta fracción es igual a otro objeto
     * @param other El objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Fraccion) return false
        
        // Dos fracciones son iguales si tienen el mismo valor cuando están simplificadas
        val thisSimplificada = this.simplificar()
        val otherSimplificada = other.simplificar()
        
        return thisSimplificada.numerador == otherSimplificada.numerador &&
               thisSimplificada.denominador == otherSimplificada.denominador
    }

    /**
     * Genera el código hash para la fracción
     * @return El código hash basado en la fracción simplificada
     */
    override fun hashCode(): Int {
        val simplificada = this.simplificar()
        return 31 * simplificada.numerador + simplificada.denominador
    }

    /**
     * Determina si esta fracción es mayor que otra
     * @param otra La fracción a comparar
     * @return true si esta fracción es mayor
     */
    fun esMayor(otra: Fraccion): Boolean {
        return this.compareTo(otra) > 0
    }

    /**
     * Determina si esta fracción es menor que otra
     * @param otra La fracción a comparar
     * @return true si esta fracción es menor
     */
    fun esMenor(otra: Fraccion): Boolean {
        return this.compareTo(otra) < 0
    }

    /**
     * Determina si esta fracción es mayor o igual que otra
     * @param otra La fracción a comparar
     * @return true si esta fracción es mayor o igual
     */
    fun esMayorOIgual(otra: Fraccion): Boolean {
        return this.compareTo(otra) >= 0
    }

    /**
     * Determina si esta fracción es menor o igual que otra
     * @param otra La fracción a comparar
     * @return true si esta fracción es menor o igual
     */
    fun esMenorOIgual(otra: Fraccion): Boolean {
        return this.compareTo(otra) <= 0
    }

    /**
     * Convierte la fracción a su representación decimal
     * @return El valor decimal de la fracción
     */
    fun aDecimal(): Double {
        return numerador.toDouble() / denominador.toDouble()
    }

    /**
     * Obtiene el valor absoluto de la fracción
     * @return Una nueva fracción con el valor absoluto
     */
    fun valorAbsoluto(): Fraccion {
        return Fraccion(abs(numerador), denominador)
    }

    /**
     * Verifica si la fracción es positiva
     * @return true si la fracción es mayor que cero
     */
    fun esPositiva(): Boolean {
        return numerador > 0
    }

    /**
     * Verifica si la fracción es negativa
     * @return true si la fracción es menor que cero
     */
    fun esNegativa(): Boolean {
        return numerador < 0
    }

    /**
     * Verifica si la fracción es cero
     * @return true si el numerador es cero
     */
    fun esCero(): Boolean {
        return numerador == 0
    }

    /**
     * Representación en cadena de la fracción
     * @return La fracción como string en formato "numerador/denominador"
     */
    override fun toString(): String {
        return "$numerador/$denominador"
    }

    /**
     * Muestra la fracción en formato legible
     * @return La fracción como string en formato "numerador/denominador"
     */
    fun mostrar(): String {
        return "$numerador/$denominador"
    }
}