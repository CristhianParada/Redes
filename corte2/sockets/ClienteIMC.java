import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteIMC {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java ClienteIMC <IP servidor> <puerto>");
            return;
        }

        String host = args[0];
        int puerto = Integer.parseInt(args[1]);

        try (Socket socket = new Socket(host, puerto)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);

            // Capturar datos del usuario
            System.out.print("Ingrese su sexo (Hombre/Mujer): ");
            String sexo = sc.nextLine();

            System.out.print("Ingrese su edad: ");
            int edad = sc.nextInt();

            System.out.print("Ingrese su peso (kg): ");
            double peso = sc.nextDouble();

            System.out.print("Ingrese su altura (m): ");
            double altura = sc.nextDouble();

            // Enviar datos al servidor
            output.println(sexo);
            output.println(edad);
            output.println(peso);
            output.println(altura);

            // Leer respuesta del servidor
            String respuesta;
            while ((respuesta = input.readLine()) != null) {
                System.out.println(respuesta);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}