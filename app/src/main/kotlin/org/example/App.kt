package org.example

fun main() {
    println("=== Prueba de Fracciones ===")
    
    val f1 = Fraccion(3, 4)
    val f2 = Fraccion(1, 2)
    val f3 = Fraccion(-2, 3)
    val f4 = Fraccion(5, -6) 
    
    println("Fracción 1: ${f1.mostrar()}")
    println("Fracción 2: ${f2.mostrar()}")
    println("Fracción 3: ${f3.mostrar()}")
    println("Fracción 4: ${f4.mostrar()}")
    
    println("\n=== Operaciones de Suma ===")
    val suma1 = f1 + f2
    println("${f1.mostrar()} + ${f2.mostrar()} = ${suma1.mostrar()}")
    
    val suma2 = f1 + f3 
    println("${f1.mostrar()} + ${f3.mostrar()} = ${suma2.mostrar()}")
    
    val suma3 = f2 + f4 
    println("${f2.mostrar()} + ${f4.mostrar()} = ${suma3.mostrar()}")
    
    println("\n=== Operaciones de Resta ===")
    val resta1 = f1 - f2 
    println("${f1.mostrar()} - ${f2.mostrar()} = ${resta1.mostrar()}")
    
    val resta2 = f2 - f3 
    println("${f2.mostrar()} - ${f3.mostrar()} = ${resta2.mostrar()}")
    
    val resta3 = f3 - f1 
    println("${f3.mostrar()} - ${f1.mostrar()} = ${resta3.mostrar()}")
    
    println("\n=== Prueba de Simplificación ===")
    val f5 = Fraccion(6, 9) 
    val f6 = Fraccion(4, 8)  
    println("Fracción 6/9 se muestra como: ${f5.mostrar()}")
    println("Fracción 4/8 se muestra como: ${f6.mostrar()}")
    
    val sumaCompleja = f5 + f6 
    println("${f5.mostrar()} + ${f6.mostrar()} = ${sumaCompleja.mostrar()}")
}