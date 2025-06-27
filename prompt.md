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