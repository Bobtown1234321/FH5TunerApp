import java.io.IOException;
import java.net.*;

public class UdpUnicastServer implements Runnable {
    private final int clientPort;

    public UdpUnicastServer(int clientPort) {
        this.clientPort = clientPort;
    }

    @Override
    public void run() {
        try(DatagramSocket serverSocket = new DatagramSocket(5000)){
            String message = "Hello World";
            System.out.println(InetAddress.getLocalHost());
            DatagramPacket packet = new DatagramPacket(
                    message.getBytes(),
                    message.length(),
                    InetAddress.getLocalHost(),
                    clientPort
            );
            while (true){
                serverSocket.send(packet);
            }


        }catch (SocketException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
