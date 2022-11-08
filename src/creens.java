import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class creens {
    private JTextPane textPane1;
    private JTextPane textPane2;
    private JTextPane textPane3;
    private JTextPane textPane4;
    private JPanel princ;

    public static void criarTela(){
        JFrame frame = new JFrame("creens");
        frame.setContentPane(new creens().princ);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
                System.in));
        //cria um objeto IP/UPD
        DatagramSocket clientSocket = new DatagramSocket();

        String servidor = "localhost";
        int porta = 9090;
        //transforma um nome em IP
        InetAddress IPAddress = InetAddress.getByName(servidor);
        //cria os buffers de envio e recebimento
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        System.out.println("Digite o texto a ser enviado ao servidor: ");
        String sentence = inFromUser.readLine();
        sendData = sentence.getBytes();
        //cria o pacote UDP a ser enviado
        DatagramPacket sendPacket = new DatagramPacket(sendData,
                sendData.length, IPAddress, porta);

        System.out.println("Enviando pacote UDP para " + servidor + ":" + porta);
        //envia o pacote UDP
        clientSocket.send(sendPacket);
        //cria um datagrama para guardar a resposta
        DatagramPacket receivePacket = new DatagramPacket(receiveData,
                receiveData.length);
        //aguarda o recebimento da resposta
        clientSocket.receive(receivePacket);
        System.out.println("Pacote UDP recebido...");

        String modifiedSentence = new String(receivePacket.getData());

        System.out.println("Texto recebido do servidor:" + modifiedSentence);
        clientSocket.close();
        System.out.println("Socket cliente fechado!");

    }
}
