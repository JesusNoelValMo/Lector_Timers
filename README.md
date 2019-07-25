# Lector_Timers
Proyecto para prueba de tablillas con timers y comunicacion de arduino a android

En este proyecto se le manda un pulso positivo a dispositivo a probar, con el se ponen timers en alto por un cierto tiempo y se ponen en bajo.

La logica para probar el circuito será, por salidas de un arduino mandar el pulso necesario a la tablilla y que ésta ponga los timers en alto y leerlos con entradas del mismo arduino.
Para poder trabajar con varios timers a la vez se van autilizar timers del arduino para que en cada uno de ellos se haga el conteo del tiempo y en un timer aparte se mande por usb el valor de todas las cuentas a una tablet.

La tablet mostrará el conteo actual de cada uno de los timers y los mostrará en pantalla.
