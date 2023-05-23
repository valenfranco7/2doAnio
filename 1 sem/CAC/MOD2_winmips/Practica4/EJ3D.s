
.data
A: .word 1
B: .word 6
array: .space 48   # Reserva espacio para 6 elementos de 8 bytes (48 bytes en total)
.code
ld r1, A(r0)
ld r2, B(r0)
ld r3, 0(r0)       # Inicializa r3 como 0, para usar como índice del arreglo
loop: dsll r1, r1, 1
      sd r1, array(r3) # Guarda el contenido de r1 en el arreglo
      daddi r3, r3, 8  # Aumenta el índice del arreglo en 8 (tamaño de una palabra doble)
      daddi r2, r2, -1
      bnez r2, loop
halt