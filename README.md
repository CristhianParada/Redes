 Cálculo de IMC Remoto con Cliente y Servidor en Java


Descripción general
Este trabajo implementa un sistema cliente-servidor en Java para calcular el Índice de Masa Corporal (IMC) de forma remota, usando sockets TCP.
El cliente envía sus datos personales (sexo, edad, peso y altura) al servidor, el cual calcula el IMC, determina la categoría de peso y devuelve la respuesta al cliente.
Si el usuario es menor de 18 años, se agrega una recomendación informativa indicando que el IMC puede no ser representativo.


Tecnologías utilizadas
- Lenguaje: Java 21
- Comunicación: Sockets TCP (java.net.Socket y java.net.ServerSocket)
- Entorno: Ubuntu(servidor) y Debian(cliente)


Arquitectura del sistema
1. Servidor (ServerIMC.java)
   - Escucha en el puerto TCP 5000.
   - Recibe datos del cliente.
   - Calcula IMC = peso / (altura^2).
   - Determina la categoría según el IMC.
   - Si la edad es menor de 18 años, envía una recomendación adicional.
   - Envía el resultado al cliente.

2. Cliente (ClienteIMC.java)
   - Solicita al usuario ingresar sexo, edad, peso y altura.
   - Envía los datos al servidor.
   - Recibe y muestra la respuesta del servidor.

Flujo de comunicación
1. Ejecutar el servidor:
   java ServerIMC

2. Ejecutar el cliente:
   java ClienteIMC <IP_servidor> 5000

Ejemplo:
   java ClienteIMC 192.168.128.9 5000

Categorías de IMC
| Rango IMC | Categoría |
|------------|-----------|
| ≤ 18.4 | Bajo peso |
| 18.5 – 24.9 | Peso normal |
| 25 – 29.9 | Sobrepeso |
| 30 – 34.9 | Obesidad grado 1 |
| 35 – 39.9 | Obesidad grado 2 |
| ≥ 40 | Obesidad grado 3 |

Ejemplo de ejecución
Cliente:
Ingrese su sexo (Hombre/Mujer): Hombre
Ingrese su edad: 16
Ingrese su peso (kg): 52
Ingrese su altura (m): 1.60

Servidor:
Cliente conectado: /192.168.128.8
Datos recibidos -> Sexo: F, Edad: 16, Peso: 52.0, Altura: 1.6
IMC calculado: 20.31
Cliente desconectado.

Cliente (respuesta):
Tu IMC es: 20.31
Categoría: Peso normal
⚠ Recomendación: El IMC puede no ser representativo para menores de edad.

Captura con Wireshark
El tráfico entre cliente y servidor se realiza con TCP:
- Conexión: SYN, SYN-ACK, ACK
- Envío de datos del cliente
- Respuesta del servidor
- Cierre: FIN, ACK

