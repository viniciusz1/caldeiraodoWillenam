import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class creens extends JFrame implements Runnable {
    private JTextPane textPane1;
    private JTextPane textPane2;
    private JTextPane textPane3;
    private JTextPane textPane4;
    private JPanel princ;

    public void criarTela() {
        setContentPane(princ);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        run();
        pack();
    }

    public creens(JTextPane textPane1, JTextPane textPane2, JTextPane textPane3, JTextPane textPane4, JPanel princ) {
        this.textPane1 = textPane1;
        this.textPane2 = textPane2;
        this.textPane3 = textPane3;
        this.textPane4 = textPane4;
        this.princ = princ;
        criarTela();
        try {
            comunica(textPane1, textPane2, textPane3, textPane4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void comunica(JTextPane t1, JTextPane t2, JTextPane t3, JTextPane t4) throws IOException {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
                System.in));
        //cria um objeto IP/UPD
        DatagramSocket clientSocket = new DatagramSocket();

        String servidor = "localhost";
        int porta = 9091;
        //transforma um nome em IP
        InetAddress IPAddress = InetAddress.getByName(servidor);
        //cria os buffers de envio e recebimento
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        System.out.println("Digite o texto a ser enviado ao servidor: ");
        String sentence = "st-0";
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

        System.out.println("Texto recebido do servidor: " + modifiedSentence);
        t1.setText(modifiedSentence);
        clientSocket.close();
        System.out.println("Socket cliente fechado!");
    }

    public static void main(String[] args) {
        creens programa = new creens(new JTextPane(), new JTextPane(), new JTextPane(), new JTextPane(), new JPanel());
    }

    @Override
    public void run() {
        if (!isVisible()) {
            setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "A janela ja está aberta!");
        }
    }
}
