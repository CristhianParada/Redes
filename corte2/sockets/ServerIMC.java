import java.io.*;
import java.net.*;

public class ServerIMC {
    public static void main(String[] args) {
        int puerto = 5000;

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor IMC escuchando en el puerto " + puerto + "...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                // Leer datos del cliente
                String sexo = input.readLine();
                int edad = Integer.parseInt(input.readLine());
                double peso = Double.parseDouble(input.readLine());
                double altura = Double.parseDouble(input.readLine());

                // Calcular IMC
                double imc = peso / (altura * altura);
                String categoria;

                // Determinar la categoría del IMC
                if (imc <= 18.4) {
                    categoria = "Bajo peso";
                } else if (imc <= 24.9) {
                    categoria = "Peso normal";
                } else if (imc <= 29.9) {
                    categoria = "Sobrepeso";
                } else if (imc <= 34.9) {
                    categoria = "Obesidad grado 1";
                } else if (imc <= 39.9) {
                    categoria = "Obesidad grado 2";
                } else {
                    categoria = "Obesidad grado 3";
                }

                // Construir respuesta
                String mensaje = "Sexo: " + sexo + "\n" +
                                 "Edad: " + edad + " años\n" +
                                 "IMC: " + String.format("%.2f", imc) + "\n" +
                                 "Categoría: " + categoria + "\n";

                // Solo mostrar recomendación si es menor de edad
                if (edad < 18) {
                    mensaje += "⚠ Recomendación: El IMC puede no ser representativo para menores de edad.\n";
         }

                // Enviar la respuesta al cliente
                output.println(mensaje);

                socket.close();
                System.out.println("Cliente desconectado.\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}