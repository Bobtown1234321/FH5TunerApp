//TODO Comments

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ForzaClient implements Runnable {
    //Port on which to receive telemetry data set in Forza Settings
    private final int port;
    private rawDataLogger dataLogger;

    public ForzaClient(int port) {
        this.port = port;
    }

    public rawDataLogger getDataLogger() {
        return dataLogger;
    }

    @Override
    public void run() {
        try (MulticastSocket clientSocket = new MulticastSocket(port)) {
            int maxUDPSize = 323;
            int timeout = 10000;
            byte[] buffer = new byte[maxUDPSize];
            //Saves to a cache file
            //dataLogger = new rawDataLogger("Telemetry", "testCar1.txt");
            //dataLogger.clearFile();

            clientSocket.setSoTimeout(timeout);
            DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length);
            int x = 1;
            ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            while (true) {
                clientSocket.receive(datagramPacket);


                System.out.print(byteBuffer.getInt(236) + " ");
                System.out.println(byteBuffer.getInt(240));
            }
        } catch (SocketException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Timeout!");
        }
    }
}
