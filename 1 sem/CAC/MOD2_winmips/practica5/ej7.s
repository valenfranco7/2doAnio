
.data
numM: .word 10
tabla: .word 2,3,4,5,6,7,12,14,15

cantnum: .word 9
resultado: .word 0


.code 
ld $a0,numM($zero) #numero a a0
ld $a2, cantnum($z)# diml a cant num
daddi $a1,$0,tabla #direc de tabla a a1
daddi $a3,$0,1 #para comparar
jal subrutina
sd $v0,resultado($zero) 
halt

subrutina: ld $t0,0($a1) #dato de la tabla en t0
slt $t1, $t0,$a0 #
beq $a3,$t1, seguir # t1 devuelve 1 si el dato de la tabla es menor que M
daddi $v0,$v0,1  #incrementamos el contador
seguir: daddi $a1,$a1,8 #avanzamos en la tabla
daddi $a2,$a2,-1 #decrementamos diml
bnez $a2,subrutina 
jr $ra