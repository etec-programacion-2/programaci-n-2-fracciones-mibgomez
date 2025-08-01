package org.example

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
    
    // Operaciones básicas
    mostrarOperacionesBasicas(f1, f2)
    
    // Pruebas de comparación
    mostrarComparaciones(f1, f2, f3, f4, f5)
    
    // Conversiones
    mostrarConversiones(f1, f2, f3, f4)
    
    // Métodos de utilidad
    mostrarMetodosUtilidad(f1, f3)
    
    // Validaciones
    mostrarValidaciones(f1)
    
    // Operaciones avanzadas
    mostrarOperacionesAvanzadas(f1, f2, f3, f4, f5)
}

/**
 * Demuestra las operaciones aritméticas básicas
 */
fun mostrarOperacionesBasicas(f1: Fraccion, f2: Fraccion) {
    println("\n=== Operaciones Básicas ===")
    println("${f1.mostrar()} + ${f2.mostrar()} = ${(f1 + f2).mostrar()}")
    println("${f1.mostrar()} - ${f2.mostrar()} = ${(f1 - f2).mostrar()}")
    println("${f1.mostrar()} * ${f2.mostrar()} = ${(f1 * f2).mostrar()}")
    println("${f1.mostrar()} / ${f2.mostrar()} = ${(f1 / f2).mostrar()}")
}

/**
 * Demuestra los métodos y operadores de comparación
 */
fun mostrarComparaciones(f1: Fraccion, f2: Fraccion, f3: Fraccion, f4: Fraccion, f5: Fraccion) {
    println("\n=== Comparaciones con métodos ===")
    println("${f1.mostrar()} == ${f5.mostrar()} ? ${f1 == f5}")
    println("${f1.mostrar()} > ${f2.mostrar()} ? ${f1.esMayor(f2)}")
    println("${f2.mostrar()} < ${f1.mostrar()} ? ${f2.esMenor(f1)}")
    println("${f3.mostrar()} > ${f4.mostrar()} ? ${f3.esMayor(f4)}")
    
    println("\n=== Comparaciones con operadores ===")
    println("${f1.mostrar()} > ${f2.mostrar()} ? ${f1 > f2}")
    println("${f2.mostrar()} < ${f1.mostrar()} ? ${f2 < f1}")
    println("${f1.mostrar()} >= ${f5.mostrar()} ? ${f1 >= f5}")
    println("${f3.mostrar()} <= ${f4.mostrar()} ? ${f3 <= f4}")
}

/**
 * Demuestra las conversiones entre fracciones y decimales
 */
fun mostrarConversiones(f1: Fraccion, f2: Fraccion, f3: Fraccion, f4: Fraccion) {
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
}

/**
 * Demuestra los métodos de utilidad para verificar propiedades de las fracciones
 */
fun mostrarMetodosUtilidad(f1: Fraccion, f3: Fraccion) {
    println("\n=== Métodos de Utilidad ===")
    println("${f1.mostrar()} es positiva? ${f1.esPositiva()}")
    println("${f3.mostrar()} es negativa? ${f3.esNegativa()}")
    println("${Fraccion(0, 1).mostrar()} es cero? ${Fraccion(0, 1).esCero()}")
    println("Valor absoluto de ${f3.mostrar()} = ${f3.valorAbsoluto().mostrar()}")
}

/**
 * Demuestra el manejo de validaciones y excepciones
 */
fun mostrarValidaciones(f1: Fraccion) {
    println("\n=== Pruebas de Validación ===")
    
    // Intento de crear fracción con denominador cero
    try {
        val fraccionInvalida = Fraccion(1, 0)
        println("Esta línea no debería ejecutarse")
    } catch (e: IllegalArgumentException) {
        println("Error capturado correctamente: ${e.message}")
    }
    
    // Intento de división por cero
    try {
        val fraccionCero = Fraccion(0, 1)
        val divisionPorCero = f1 / fraccionCero
        println("Esta línea no debería ejecutarse")
    } catch (e: ArithmeticException) {
        println("Error capturado correctamente: ${e.message}")
    }
    
    // Intento de conversión desde NaN
    try {
        val fraccionDesdeNaN = Fraccion.desdeDecimal(Double.NaN)
        println("Esta línea no debería ejecutarse")
    } catch (e: IllegalArgumentException) {
        println("Error capturado correctamente: ${e.message}")
    }
}

/**
 * Demuestra operaciones avanzadas como encadenamiento y ordenamiento
 */
fun mostrarOperacionesAvanzadas(f1: Fraccion, f2: Fraccion, f3: Fraccion, f4: Fraccion, f5: Fraccion) {
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
    println("f1 = ${f1.mostrar()}, hashCode = ${f1.hashCode()}")
    println("f5 = ${f5.mostrar()}, hashCode = ${f5.hashCode()}")
    println("f1 == f5 ? ${f1 == f5}")
    println("f1.hashCode() == f5.hashCode() ? ${f1.hashCode() == f5.hashCode()}")
    
    println("\n=== Pruebas de Simplificación Automática ===")
    val fraccionCompleja1 = Fraccion(12, 16)  // 12/16 = 3/4
    val fraccionCompleja2 = Fraccion(15, 25)  // 15/25 = 3/5
    
    println("Fracción 12/16 se muestra como: ${fraccionCompleja1.mostrar()}")
    println("Fracción 15/25 se muestra como: ${fraccionCompleja2.mostrar()}")
    
    val operacionCompleja = fraccionCompleja1 * fraccionCompleja2
    println("${fraccionCompleja1.mostrar()} * ${fraccionCompleja2.mostrar()} = ${operacionCompleja.mostrar()}")
    
    println("\n=== Casos Extremos ===")
    val fraccionUno = Fraccion(5, 5)           // 1/1 = 1
    val fraccionNegativaUno = Fraccion(-3, 3)  // -1/1 = -1
    val fraccionGrande = Fraccion(1000000, 3)  // Fracción con números grandes
    
    println("Fracción 5/5 = ${fraccionUno.mostrar()}")
    println("Fracción -3/3 = ${fraccionNegativaUno.mostrar()}")
    println("Fracción 1000000/3 = ${fraccionGrande.mostrar()} = ${fraccionGrande.aDecimal()}")
    
    println("\n¡Todas las pruebas completadas exitosamente!")
}