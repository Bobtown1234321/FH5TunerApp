import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.SQLOutput;
import java.util.Arrays;

public class UdpUnicastClient implements Runnable {
    private final int port;

    public UdpUnicastClient(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try(MulticastSocket clientSocket = new MulticastSocket(port)){
            int maxUDPSize = 323;
            int timeout = 10000;
            byte[] buffer = new byte[maxUDPSize];

            int inRace;
            Long timestamp;

            clientSocket.setSoTimeout(timeout);
            DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length);
            while (true){
                
                clientSocket.receive(datagramPacket);
                ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
                byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                System.out.println(byteBuffer.capacity());
                //System.out.println(Arrays.toString(buffer));

            }
        } catch (SocketException e){
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Timeout!");
        }
    }
}
