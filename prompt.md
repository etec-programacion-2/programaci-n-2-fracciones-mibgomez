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

