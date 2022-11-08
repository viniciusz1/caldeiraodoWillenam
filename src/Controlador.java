import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.io.*;


public class Controlador {
    static Scanner sc = new Scanner(System.in);
    private String pediodoControlador;

    public static void main(String[] args) {
        System.out.println("1– Laço de controle como tarefa periódica para a temperatura;\n" +
                "2– Uso do tanque e da saída de água;\n" +
                "3– Informações na tela sobre a situação corrente.");
        try {
        arquivo();

        }catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }
//temperatura uso do tanque, saída da agua


    public static void arquivo() throws IOException {
        FileWriter arq = new FileWriter("C:\\Users\\vinicius_benner\\Desktop\\arquivo.txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf("                    +---------------------------------+%n");
        gravarArq.printf("                    |         Controle de Temperatura |%n");
        gravarArq.printf("                    +---------------------------------+%n");
        gravarArq.printf("                    | Temperatura: 20ºC               |%n");
        gravarArq.printf("                    | Tanque: 100L                    |%n");
        gravarArq.printf("                    | Saída de Água: 10L              |%n");
        gravarArq.printf("                    +---------------------------------+%n");
        arq.close();
    }





    void calculaTempo(){
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now();
        long milliseconds = ChronoUnit.MILLIS.between(from, to);
        System.out.println(milliseconds);
    }

}
