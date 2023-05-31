.data
peso: .double 66.5  #variable para el peso
altura: .double 1.78  #variable para la altura
IMC: .double 0.0  #variable para guardar el imc
estado: .word 0.0  # variable para guardar 1,2,3 o 4 segun el imc
est1: .double 18.5# variables para almacenar los valores para comparar
est2: .double 25# las pusimos en memoria porque no se pueden poner de una en registros de punto flotante
est3: .double 30



.code
l.d f2, altura(r0)# cargamos peso y altura en f2 y f1.
l.d f1, peso(r0)
daddi r1,r0,0# inicializamos r1 en 0
mul.d f2,f2,f2; hacemos altura al cuadrado
div.d f3,f1,f2; dividimos peso por altura al cuadrado y tenemos el imc en f3


l.d f4, est1(r0); cargamos en f4 el primer valor a comparar (18.5)
c.lt.d f3,f4; pone FP en 1 si f3 es menor a f4.
bc1f  sig; si el flag es 0, salta al sig (ya que f3 es mayor o igual)
daddi r1,r0,1
sd r1, estado(r0);aca entra si el FP estaba en 1, osea f3<f4. y carga en estado un 1
j fin


sig: l.d f4, est2(r0)  ;cargamos en f4 el segundo valor a comparar (25)
c.lt.d f3,f4  ; pone FP en 1 si f3 es menor a f4.
bc1f sig2  ; si el flag es 0, salta al sig (ya que f3 es mayor o igual)
daddi r1,r0,2 ;aca entra si el FP estaba en 1, osea f3<f4. y carga en estado un 2
sd r1, estado(r0)
j fin


sig2: l.d f4, est3(r0) ;cargamos en f4 el tercer valor a comparar (30)
c.lt.d f3,f4  ; pone FP en 1 si f3 es menor a f4.
bc1f sig3  ; si el flag es 0, salta al sig (ya que f3 es mayor o igual)
daddi r1,r0,3 ;aca entra si el FP estaba en 1, osea f3<f4. y carga en estado un 3 
sd r1, estado(r0)
j fin


sig3: daddi r1,r0,4  ;si llega aca es porque IMC>=30
sd r1, estado(r0); carga 4 en el estado
fin: s.d f3, IMC(r0); aca carga en memoria el resultado del IMC
halt