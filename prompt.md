****en esta carpeta que te comparti hay dos archivos para programar en kotlin que trabajan en simultaneo 
    usando clases, poque me estan dando error cuando los trato de ejecutar (24 jun, kotlin):

Veo el problema en tu código. El error ocurre porque la clase Fraccion está heredando de Numero(), pero esta clase padre no existe en los archivos que compartiste.
Aquí tienes una posible solución:

package org.example

class Fraccion(
    numerador: Int,
    denominador: Int
) {

    var numerador: Int = numerador
        get() = field
        set(value) { field = value }

    var denominador: Int = denominador
        get() = field
        set(value) { 
            if (value == 0) throw IllegalArgumentException("El denominador no puede ser cero")
            field = value 
        }

    init {
        // Validación inicial
        if (denominador == 0) {
            throw IllegalArgumentException("El denominador no puede ser cero")
        }
    }

    override fun toString(): String {
        return "$numerador / $denominador"
    }
}

Simplemente elimina : Numero() de la declaración de la clase Fraccion, como se muestra en el código de arriba.

_____________________________________________________________________________________________________________

****### Etapa 2: Suma y Resta de Fracciones
    Objetivo: Implementar las operaciones de suma y resta entre fracciones usando operadores de Kotlin.
    Pasos a seguir:
    1. Operador suma:
    - Crear el operador operator fun plus(otra: Fraccion): Fraccion
    - Implementar la fórmula: (a/b) + (c/d) = (ad + bc)/(bd)
    - El operador debe devolver una nueva fracción con el resultado
    2. Operador resta:
    - Crear el operador operator fun minus(otra: Fraccion): Fraccion
    - Implementar la fórmula: (a/b) - (c/d) = (ad - bc)/(bd)
    - El operador debe devolver una nueva fracción con el resultado
    3. Método simplificar:
    - Crear un método privado simplificar() que reduzca la fracción a su forma más simple
    - Usar el algoritmo de Euclides para encontrar el máximo común divisor (MCD)
    - Aplicar la simplificación en los operadores de suma y resta
    4. Validaciones:
    - Verificar que las fracciones a operar sean válidas (denominador ≠ 0)
    - Manejar casos de fracciones negativas
    5. Push al repositorio con el mensaje "Etapa 2: Implementación de operadores suma y resta de fracciones".
    Utilizando el codigo anteriormente proporcionado resuelve estas consignas explicando todos los pasos a seguir


Te ayudo a implementar la suma y resta de fracciones paso a paso. Vamos a extender el código anterior con todas las funcionalidades solicitadas.
Explicación de los pasos:
1. Algoritmo de Euclides para el MCD
Primero necesitamos un método para calcular el máximo común divisor, que nos permitirá simplificar las fracciones:
2. Método simplificar
Este método reducirá la fracción a su forma más simple dividiendo numerador y denominador por su MCD.
3. Operadores suma y resta
Implementaremos los operadores usando las fórmulas matemáticas correspondientes.
4. Manejo de signos
Manejaremos correctamente las fracciones negativas.
Aquí está la implementación completa:

package org.example

import kotlin.math.abs

class Fraccion(
    numerador: Int,
    denominador: Int
) {
    var numerador: Int = numerador
    
    var denominador: Int = denominador
        set(value) {
            if (value == 0) throw IllegalArgumentException("El denominador no puede ser cero")
            field = value
        }

    init {
        if (denominador == 0) {
            throw IllegalArgumentException("El denominador no puede ser cero")
        }
        // Normalizamos el signo: el denominador siempre será positivo
        if (this.denominador < 0) {
            this.numerador = -this.numerador
            this.denominador = -this.denominador
        }
    }

    /**
     * Calcula el máximo común divisor usando el algoritmo de Euclides
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
     */
    private fun simplificar(): Fraccion {
        val divisorComun = mcd(numerador, denominador)
        return Fraccion(numerador / divisorComun, denominador / divisorComun)
    }

    /**
     * Operador suma: (a/b) + (c/d) = (a*d + b*c)/(b*d)
     */
    operator fun plus(otra: Fraccion): Fraccion {
        // Verificamos que ambas fracciones sean válidas
        if (this.denominador == 0 || otra.denominador == 0) {
            throw IllegalArgumentException("No se puede operar con fracciones inválidas")
        }
        
        val nuevoNumerador = this.numerador * otra.denominador + this.denominador * otra.numerador
        val nuevoDenominador = this.denominador * otra.denominador
        
        return Fraccion(nuevoNumerador, nuevoDenominador).simplificar()
    }

    /**
     * Operador resta: (a/b) - (c/d) = (a*d - b*c)/(b*d)
     */
    operator fun minus(otra: Fraccion): Fraccion {
        // Verificamos que ambas fracciones sean válidas
        if (this.denominador == 0 || otra.denominador == 0) {
            throw IllegalArgumentException("No se puede operar con fracciones inválidas")
        }
        
        val nuevoNumerador = this.numerador * otra.denominador - this.denominador * otra.numerador
        val nuevoDenominador = this.denominador * otra.denominador
        
        return Fraccion(nuevoNumerador, nuevoDenominador).simplificar()
    }

    override fun toString(): String {
        return "$numerador/$denominador"
    }

    fun mostrar(): String {
        return "$numerador/$denominador"
    }
}

fun main() {
    println("=== Pruebas de Fracciones ===")
    
    // Creación de fracciones
    val f1 = Fraccion(3, 4)
    val f2 = Fraccion(1, 2)
    val f3 = Fraccion(-2, 3)
    val f4 = Fraccion(5, -6) // Se normalizará a -5/6
    
    println("Fracción 1: ${f1.mostrar()}")
    println("Fracción 2: ${f2.mostrar()}")
    println("Fracción 3: ${f3.mostrar()}")
    println("Fracción 4: ${f4.mostrar()}")
    
    println("\n=== Operaciones de Suma ===")
    val suma1 = f1 + f2  // 3/4 + 1/2 = 3/4 + 2/4 = 5/4
    println("${f1.mostrar()} + ${f2.mostrar()} = ${suma1.mostrar()}")
    
    val suma2 = f1 + f3  // 3/4 + (-2/3) = 9/12 - 8/12 = 1/12
    println("${f1.mostrar()} + ${f3.mostrar()} = ${suma2.mostrar()}")
    
    val suma3 = f2 + f4  // 1/2 + (-5/6) = 3/6 - 5/6 = -2/6 = -1/3
    println("${f2.mostrar()} + ${f4.mostrar()} = ${suma3.mostrar()}")
    
    println("\n=== Operaciones de Resta ===")
    val resta1 = f1 - f2  // 3/4 - 1/2 = 3/4 - 2/4 = 1/4
    println("${f1.mostrar()} - ${f2.mostrar()} = ${resta1.mostrar()}")
    
    val resta2 = f2 - f3  // 1/2 - (-2/3) = 1/2 + 2/3 = 3/6 + 4/6 = 7/6
    println("${f2.mostrar()} - ${f3.mostrar()} = ${resta2.mostrar()}")
    
    val resta3 = f3 - f1  // -2/3 - 3/4 = -8/12 - 9/12 = -17/12
    println("${f3.mostrar()} - ${f1.mostrar()} = ${resta3.mostrar()}")
    
    println("\n=== Pruebas de Simplificación ===")
    val f5 = Fraccion(6, 9)    // Se simplifica a 2/3
    val f6 = Fraccion(4, 8)    // Se simplifica a 1/2
    println("Fracción 6/9 se muestra como: ${f5.mostrar()}")
    println("Fracción 4/8 se muestra como: ${f6.mostrar()}")
    
    val sumaCompleja = f5 + f6  // 2/3 + 1/2 = 4/6 + 3/6 = 7/6
    println("${f5.mostrar()} + ${f6.mostrar()} = ${sumaCompleja.mostrar()}")
}

Explicación detallada de cada componente:
1. Algoritmo de Euclides (método mcd)

private fun mcd(a: Int, b: Int): Int

Calcula el máximo común divisor de dos números
Usa valores absolutos para manejar números negativos
Implementa el algoritmo clásico de división sucesiva

2. Método simplificar()

private fun simplificar(): Fraccion

Encuentra el MCD del numerador y denominador
Divide ambos por el MCD para obtener la fracción reducida
Devuelve una nueva fracción simplificada

3. Operador suma (plus)

operator fun plus(otra: Fraccion): Fraccion

Implementa la fórmula: (a/b) + (c/d) = (a*d + b*c)/(b*d)
Valida que ambas fracciones sean válidas
Simplifica automáticamente el resultado

4. Operador resta (minus)

operator fun minus(otra: Fraccion): Fraccion

Implementa la fórmula: (a/b) - (c/d) = (a*d - b*c)/(b*d)
Valida que ambas fracciones sean válidas
Simplifica automáticamente el resultado

5. Manejo de signos 
En el init:

if (this.denominador < 0) {
    this.numerador = -this.numerador
    this.denominador = -this.denominador
}

Normaliza el signo: el denominador siempre será positivo
Si el denominador es negativo, cambia el signo de ambos

6. Validaciones implementadas

Verificación de denominador ≠ 0 en constructor y setter
Validación en operadores antes de realizar cálculos
Manejo correcto de fracciones negativas

7. Casos de prueba incluidos
El main() incluye pruebas para:

Creación de fracciones (positivas y negativas)
Operaciones de suma con diferentes combinaciones
Operaciones de resta con diferentes combinaciones
Verificación de simplificación automática
_____________________________________________________________________________________________________________

****### Etapa 3: Multiplicación y División de Fracciones
    Objetivo: Implementar las operaciones de multiplicación y división entre fracciones usando operadores de Kotlin.
    Pasos a seguir:
    1. Operador multiplicación:
    - Crear el operador operator fun times(otra: Fraccion): Fraccion
    - Implementar la fórmula: (a/b) * (c/d) = (ac)/(bd)
    - El operador debe devolver una nueva fracción con el resultado
    2. Operador división:
    - Crear el operador operator fun div(otra: Fraccion): Fraccion
    - Implementar la fórmula: (a/b) / (c/d) = (ad)/(bc)
    - El operador debe devolver una nueva fracción con el resultado
    3. Validaciones adicionales:
    - En división, verificar que el numerador de la segunda fracción no sea cero
    - Manejar el caso de división por cero lanzando una excepción
    4. Aplicar simplificación:**
    - Usar el método simplificar() en los resultados de multiplicación y división
    Realiza las consignas mencionadas de la misma forma de las anteriores y explicando lo realizado

Te ayudo a implementar la multiplicación y división de fracciones. Vamos a extender el código anterior con estas nuevas operaciones.
Explicación de los pasos:
1. Operador multiplicación (times)
La multiplicación de fracciones es directa: se multiplican numeradores entre sí y denominadores entre sí.
2. Operador división (div)
La división se convierte en multiplicación por el recíproco de la segunda fracción.
3. Validaciones especiales para división
Debemos verificar que no se divida por una fracción con numerador cero (equivale a dividir por cero).
4. Simplificación automática
Ambos operadores aplicarán simplificación al resultado.
Aquí está la implementación completa:

package org.example

import kotlin.math.abs

class Fraccion(
    numerador: Int,
    denominador: Int
) {
    var numerador: Int = numerador
    
    var denominador: Int = denominador
        set(value) {
            if (value == 0) throw IllegalArgumentException("El denominador no puede ser cero")
            field = value
        }

    init {
        if (denominador == 0) {
            throw IllegalArgumentException("El denominador no puede ser cero")
        }
        // Normalizamos el signo: el denominador siempre será positivo
        if (this.denominador < 0) {
            this.numerador = -this.numerador
            this.denominador = -this.denominador
        }
    }

    /**
     * Calcula el máximo común divisor usando el algoritmo de Euclides
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
     */
    private fun simplificar(): Fraccion {
        val divisorComun = mcd(numerador, denominador)
        return Fraccion(numerador / divisorComun, denominador / divisorComun)
    }

    /**
     * Operador suma: (a/b) + (c/d) = (a*d + b*c)/(b*d)
     */
    operator fun plus(otra: Fraccion): Fraccion {
        // Verificamos que ambas fracciones sean válidas
        if (this.denominador == 0 || otra.denominador == 0) {
            throw IllegalArgumentException("No se puede operar con fracciones inválidas")
        }
        
        val nuevoNumerador = this.numerador * otra.denominador + this.denominador * otra.numerador
        val nuevoDenominador = this.denominador * otra.denominador
        
        return Fraccion(nuevoNumerador, nuevoDenominador).simplificar()
    }

    /**
     * Operador resta: (a/b) - (c/d) = (a*d - b*c)/(b*d)
     */
    operator fun minus(otra: Fraccion): Fraccion {
        // Verificamos que ambas fracciones sean válidas
        if (this.denominador == 0 || otra.denominador == 0) {
            throw IllegalArgumentException("No se puede operar con fracciones inválidas")
        }
        
        val nuevoNumerador = this.numerador * otra.denominador - this.denominador * otra.numerador
        val nuevoDenominador = this.denominador * otra.denominador
        
        return Fraccion(nuevoNumerador, nuevoDenominador).simplificar()
    }

    /**
     * Operador multiplicación: (a/b) * (c/d) = (a*c)/(b*d)
     */
    operator fun times(otra: Fraccion): Fraccion {
        // Verificamos que ambas fracciones sean válidas
        if (this.denominador == 0 || otra.denominador == 0) {
            throw IllegalArgumentException("No se puede operar con fracciones inválidas")
        }
        
        val nuevoNumerador = this.numerador * otra.numerador
        val nuevoDenominador = this.denominador * otra.denominador
        
        return Fraccion(nuevoNumerador, nuevoDenominador).simplificar()
    }

    /**
     * Operador división: (a/b) / (c/d) = (a*d)/(b*c)
     * Equivale a multiplicar por el recíproco de la segunda fracción
     */
    operator fun div(otra: Fraccion): Fraccion {
        // Verificamos que ambas fracciones sean válidas
        if (this.denominador == 0 || otra.denominador == 0) {
            throw IllegalArgumentException("No se puede operar con fracciones inválidas")
        }
        
        // Verificamos que no se divida por cero (numerador de la segunda fracción no puede ser 0)
        if (otra.numerador == 0) {
            throw ArithmeticException("No se puede dividir por una fracción con numerador cero (división por cero)")
        }
        
        val nuevoNumerador = this.numerador * otra.denominador
        val nuevoDenominador = this.denominador * otra.numerador
        
        return Fraccion(nuevoNumerador, nuevoDenominador).simplificar()
    }

    override fun toString(): String {
        return "$numerador/$denominador"
    }

    fun mostrar(): String {
        return "$numerador/$denominador"
    }
}

fun main() {
    println("=== Pruebas de Fracciones ===")
    
    // Creación de fracciones
    val f1 = Fraccion(3, 4)
    val f2 = Fraccion(1, 2)
    val f3 = Fraccion(-2, 3)
    val f4 = Fraccion(5, -6) // Se normalizará a -5/6
    
    println("Fracción 1: ${f1.mostrar()}")
    println("Fracción 2: ${f2.mostrar()}")
    println("Fracción 3: ${f3.mostrar()}")
    println("Fracción 4: ${f4.mostrar()}")
    
    println("\n=== Operaciones de Suma ===")
    val suma1 = f1 + f2  // 3/4 + 1/2 = 3/4 + 2/4 = 5/4
    println("${f1.mostrar()} + ${f2.mostrar()} = ${suma1.mostrar()}")
    
    val suma2 = f1 + f3  // 3/4 + (-2/3) = 9/12 - 8/12 = 1/12
    println("${f1.mostrar()} + ${f3.mostrar()} = ${suma2.mostrar()}")
    
    val suma3 = f2 + f4  // 1/2 + (-5/6) = 3/6 - 5/6 = -2/6 = -1/3
    println("${f2.mostrar()} + ${f4.mostrar()} = ${suma3.mostrar()}")
    
    println("\n=== Operaciones de Resta ===")
    val resta1 = f1 - f2  // 3/4 - 1/2 = 3/4 - 2/4 = 1/4
    println("${f1.mostrar()} - ${f2.mostrar()} = ${resta1.mostrar()}")
    
    val resta2 = f2 - f3  // 1/2 - (-2/3) = 1/2 + 2/3 = 3/6 + 4/6 = 7/6
    println("${f2.mostrar()} - ${f3.mostrar()} = ${resta2.mostrar()}")
    
    val resta3 = f3 - f1  // -2/3 - 3/4 = -8/12 - 9/12 = -17/12
    println("${f3.mostrar()} - ${f1.mostrar()} = ${resta3.mostrar()}")
    
    println("\n=== Operaciones de Multiplicación ===")
    val mult1 = f1 * f2  // 3/4 * 1/2 = 3/8
    println("${f1.mostrar()} * ${f2.mostrar()} = ${mult1.mostrar()}")
    
    val mult2 = f1 * f3  // 3/4 * (-2/3) = -6/12 = -1/2
    println("${f1.mostrar()} * ${f3.mostrar()} = ${mult2.mostrar()}")
    
    val mult3 = f3 * f4  // (-2/3) * (-5/6) = 10/18 = 5/9
    println("${f3.mostrar()} * ${f4.mostrar()} = ${mult3.mostrar()}")
    
    println("\n=== Operaciones de División ===")
    val div1 = f1 / f2  // 3/4 / 1/2 = 3/4 * 2/1 = 6/4 = 3/2
    println("${f1.mostrar()} / ${f2.mostrar()} = ${div1.mostrar()}")
    
    val div2 = f2 / f3  // 1/2 / (-2/3) = 1/2 * (-3/2) = -3/4
    println("${f2.mostrar()} / ${f3.mostrar()} = ${div2.mostrar()}")
    
    val div3 = f1 / f4  // 3/4 / (-5/6) = 3/4 * (-6/5) = -18/20 = -9/10
    println("${f1.mostrar()} / ${f4.mostrar()} = ${div3.mostrar()}")
    
    println("\n=== Pruebas de Simplificación ===")
    val f5 = Fraccion(6, 9)    // Se simplifica a 2/3
    val f6 = Fraccion(4, 8)    // Se simplifica a 1/2
    println("Fracción 6/9 se muestra como: ${f5.mostrar()}")
    println("Fracción 4/8 se muestra como: ${f6.mostrar()}")
    
    val multCompleja = f5 * f6  // 2/3 * 1/2 = 2/6 = 1/3
    println("${f5.mostrar()} * ${f6.mostrar()} = ${multCompleja.mostrar()}")
    
    val divCompleja = f5 / f6   // 2/3 / 1/2 = 2/3 * 2/1 = 4/3
    println("${f5.mostrar()} / ${f6.mostrar()} = ${divCompleja.mostrar()}")
    
    println("\n=== Pruebas de Validación ===")
    try {
        val fraccionCero = Fraccion(0, 1)
        println("Fracción cero creada: ${fraccionCero.mostrar()}")
        
        // Esto debería lanzar una excepción
        val divisionPorCero = f1 / fraccionCero
        println("Esta línea no debería ejecutarse")
    } catch (e: ArithmeticException) {
        println("Error capturado correctamente: ${e.message}")
    }
    
    // Ejemplo de operaciones encadenadas
    println("\n=== Operaciones Encadenadas ===")
    val resultado = (f1 + f2) * f3 / f4  // ((3/4 + 1/2) * (-2/3)) / (-5/6)
    println("(${f1.mostrar()} + ${f2.mostrar()}) * ${f3.mostrar()} / ${f4.mostrar()} = ${resultado.mostrar()}")
}

Explicación detallada de las nuevas implementaciones:

1. Operador multiplicación (times)
operator fun times(otra: Fraccion): Fraccion

Fórmula implementada: (a/b) * (c/d) = (a*c)/(b*d)
Características:

Multiplica numeradores entre sí: this.numerador * otra.numerador
Multiplica denominadores entre sí: this.denominador * otra.denominador
Aplica simplificación automática al resultado
Incluye validaciones de fracciones válidas

Ejemplo: 3/4 * 1/2 = 3*1/4*2 = 3/8

2. Operador división (div)
operator fun div(otra: Fraccion): Fraccion

Fórmula implementada: (a/b) / (c/d) = (a*d)/(b*c)
Características:

Equivale a multiplicar por el recíproco de la segunda fracción
Multiplica el numerador de la primera por el denominador de la segunda
Multiplica el denominador de la primera por el numerador de la segunda
Validación especial: Verifica que el numerador de la segunda fracción no sea cero
Lanza ArithmeticException en caso de división por cero
Aplica simplificación automática al resultado

Ejemplo: 3/4 / 1/2 = 3/4 * 2/1 = 6/4 = 3/2

3. Validaciones adicionales implementadas
Validación de división por cero:

if (otra.numerador == 0) {
    throw ArithmeticException("No se puede dividir por una fracción con numerador cero (división por cero)")
}

Esta validación es crucial porque:

Una fracción con numerador 0 representa el valor 0
Dividir por 0 es matemáticamente indefinido
Se lanza una excepción específica ArithmeticException

Validaciones heredadas:

Verificación de denominadores válidos (≠ 0)
Manejo correcto de signos negativos
Normalización automática de fracciones

4. Casos de prueba añadidos
Multiplicación:

3/4 * 1/2 = 3/8
3/4 * (-2/3) = -1/2 (con simplificación)
(-2/3) * (-5/6) = 5/9 (dos negativos = positivo)

División:

3/4 / 1/2 = 3/2
1/2 / (-2/3) = -3/4
3/4 / (-5/6) = -9/10

Pruebas de validación:

Intento de división por fracción con numerador cero
Manejo correcto de excepciones
Operaciones encadenadas complejas

5. Ventajas de la implementación
Simplicidad de uso:

val resultado = fraccion1 * fraccion2  // Multiplicación natural
val division = fraccion1 / fraccion2   // División natural

Operaciones encadenadas:

val complejo = (f1 + f2) * f3 / f4  // Múltiples operaciones

Manejo robusto de errores:

try {
    val resultado = fraccion / fraccionCero
} catch (e: ArithmeticException) {
    println("Error: ${e.message}")
}

6. Características matemáticas correctas

Simplificación automática: Todos los resultados se reducen a su mínima expresión
Manejo de signos: Correcta propagación de signos negativos
Precisión: No hay pérdida de precisión (se trabaja con enteros)
Validación completa: Prevención de estados inválidos
_________________________________________________________________________________________________

### Etapa 4: Validaciones
Objetivo: Implementar validaciones adicionales y métodos de utilidad para completar la funcionalidad de la clase.
Pasos a seguir:
1. Validaciones de entrada:
- Verificar que el denominador nunca sea cero en cualquier operación
- Manejar fracciones negativas correctamente
- Validar que los parámetros de entrada sean válidos
2. Operadores de comparación:
- Crear el operador operator fun compareTo(otra: Fraccion): Int para comparar fracciones
- Implementar operator fun equals(other: Any?): Boolean para verificar igualdad
3. Métodos de utilidad:
- Crear un método esMayor(otra: Fraccion): Boolean que determine si una fracción es mayor que otra
- Crear un método esMenor(otra: Fraccion): Boolean que determine si una fracción es menor que otra
4. Método para convertir a decimal:
- Crear un método aDecimal(): Double que convierta la fracción a su representación decimal
5. Método para crear fracción desde decimal:
- Crear un método companion object fun desdeDecimal(decimal: Double): Fraccion que convierta un decimal a fracción
6. Documentación:
- Documentar los parámetros y valores de retorno
resuelve las consignas anteriores de la misma forma que las anteriores


Te ayudo a implementar las validaciones adicionales y métodos de utilidad para completar la funcionalidad de la clase Fracción.
Explicación de los pasos:
1. Operadores de comparación
Implementaremos compareTo para permitir comparaciones naturales entre fracciones y equals para verificar igualdad.
2. Métodos de utilidad de comparación
Crearemos métodos auxiliares que faciliten las comparaciones.
3. Conversión a decimal
Método para obtener la representación decimal de la fracción.
4. Conversión desde decimal
Companion object para crear fracciones a partir de decimales.
5. Validaciones mejoradas
Reforzaremos las validaciones existentes y añadiremos nuevas.
Aquí está la implementación completa:

package org.example

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.round

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
         */
        fun desdeDecimal(decimal: Double, precision: Int = 6): Fraccion {
            if (decimal.isNaN() || decimal.isInfinite()) {
                throw IllegalArgumentException("No se puede convertir NaN o Infinito a fracción")
            }
            
            val factor = 10.0.pow(precision).toInt()
            val numerador = (decimal * factor).round().toInt()
            
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

fun main() {
    println("=== Pruebas Completas de Fracciones ===")
    
    // Creación de fracciones
    val f1 = Fraccion(3, 4)        // 3/4
    val f2 = Fraccion(1, 2)        // 1/2
    val f3 = Fraccion(-2, 3)       // -2/3
    val f4 = Fraccion(5, -6)       // -5/6
    val f5 = Fraccion(6, 8)        // 6/8 = 3/4
    
    println("Fracciones creadas:")
    println("f1 = ${f1.mostrar()}")
    println("f2 = ${f2.mostrar()}")
    println("f3 = ${f3.mostrar()}")
    println("f4 = ${f4.mostrar()}")
    println("f5 = ${f5.mostrar()}")
    
    println("\n=== Operaciones Básicas ===")
    println("${f1.mostrar()} + ${f2.mostrar()} = ${(f1 + f2).mostrar()}")
    println("${f1.mostrar()} - ${f2.mostrar()} = ${(f1 - f2).mostrar()}")
    println("${f1.mostrar()} * ${f2.mostrar()} = ${(f1 * f2).mostrar()}")
    println("${f1.mostrar()} / ${f2.mostrar()} = ${(f1 / f2).mostrar()}")
    
    println("\n=== Comparaciones ===")
    println("${f1.mostrar()} == ${f5.mostrar()} ? ${f1 == f5}")
    println("${f1.mostrar()} > ${f2.mostrar()} ? ${f1.esMayor(f2)}")
    println("${f2.mostrar()} < ${f1.mostrar()} ? ${f2.esMenor(f1)}")
    println("${f3.mostrar()} > ${f4.mostrar()} ? ${f3.esMayor(f4)}")
    
    println("\n=== Comparaciones con operadores ===")
    println("${f1.mostrar()} > ${f2.mostrar()} ? ${f1 > f2}")
    println("${f2.mostrar()} < ${f1.mostrar()} ? ${f2 < f1}")
    println("${f1.mostrar()} >= ${f5.mostrar()} ? ${f1 >= f5}")
    println("${f3.mostrar()} <= ${f4.mostrar()} ? ${f3 <= f4}")
    
    println("\n=== Conversiones a Decimal ===")
    println("${f1.mostrar()} = ${f1.aDecimal()}")
    println("${f2.mostrar()} = ${f2.aDecimal()}")
    println("${f3.mostrar()} = ${f3.aDecimal()}")
    println("${f4.mostrar()} = ${f4.aDecimal()}")
    
    println("\n=== Conversiones desde Decimal ===")
    val decimal1 = 0.75
    val decimal2 = 0.333333
    val decimal3 = -1.25
    
    val fracDesdeDecimal1 = Fraccion.desdeDecimal(decimal1)
    val fracDesdeDecimal2 = Fraccion.desdeDecimal(decimal2)
    val fracDesdeDecimal3 = Fraccion.desdeDecimal(decimal3)
    
    println("$decimal1 → ${fracDesdeDecimal1.mostrar()}")
    println("$decimal2 → ${fracDesdeDecimal2.mostrar()}")
    println("$decimal3 → ${fracDesdeDecimal3.mostrar()}")
    
    println("\n=== Métodos de Utilidad ===")
    println("${f1.mostrar()} es positiva? ${f1.esPositiva()}")
    println("${f3.mostrar()} es negativa? ${f3.esNegativa()}")
    println("${Fraccion(0, 1).mostrar()} es cero? ${Fraccion(0, 1).esCero()}")
    println("Valor absoluto de ${f3.mostrar()} = ${f3.valorAbsoluto().mostrar()}")
    
    println("\n=== Pruebas de Validación ===")
    try {
        val fraccionInvalida = Fraccion(1, 0)
    } catch (e: IllegalArgumentException) {
        println("Error capturado: ${e.message}")
    }
    
    try {
        val fraccionCero = Fraccion(0, 1)
        val divisionPorCero = f1 / fraccionCero
    } catch (e: ArithmeticException) {
        println("Error capturado: ${e.message}")
    }
    
    try {
        val fraccionDesdeNaN = Fraccion.desdeDecimal(Double.NaN)
    } catch (e: IllegalArgumentException) {
        println("Error capturado: ${e.message}")
    }
    
    println("\n=== Operaciones Encadenadas ===")
    val resultado1 = (f1 + f2) * f3 / f4
    println("(${f1.mostrar()} + ${f2.mostrar()}) * ${f3.mostrar()} / ${f4.mostrar()} = ${resultado1.mostrar()}")
    
    val resultado2 = f1.valorAbsoluto() + f3.valorAbsoluto()
    println("|${f1.mostrar()}| + |${f3.mostrar()}| = ${resultado2.mostrar()}")
    
    println("\n=== Ordenamiento de Fracciones ===")
    val fracciones = listOf(f1, f2, f3, f4, f5)
    val fraccionesOrdenadas = fracciones.sorted()
    
    println("Fracciones originales: ${fracciones.map { it.mostrar() }}")
    println("Fracciones ordenadas: ${fraccionesOrdenadas.map { it.mostrar() }}")
    
    println("\n=== Verificación de Hashcode y Equals ===")
    println("f1.hashCode() = ${f1.hashCode()}")
    println("f5.hashCode() = ${f5.hashCode()}")
    println("f1 == f5 ? ${f1 == f5}")
    println("f1.hashCode() == f5.hashCode() ? ${f1.hashCode() == f5.hashCode()}")
}

_________________________________________________________________________________________________

