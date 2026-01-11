//TODO Comments

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Arrays;

public class ForzaClient implements Runnable{
    //Port on which to receive telemetry data set in Forza Settings
    private final int port;

    public ForzaClient(int port){
        this.port = port;
    }

    @Override
    public void run() {
        try(MulticastSocket clientSocket = new MulticastSocket(port)){
            int maxUDPSize = 323;
            int timeout = 10000;
            byte[] buffer = new byte[maxUDPSize];
            //Saves to a cache file
            rawDataLogger dataLogger = new rawDataLogger();
            dataLogger.createDirectory("Telemetry");
            dataLogger.createFile("testCar1.txt");
            dataLogger.clearFile();

            clientSocket.setSoTimeout(timeout);
            DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length);
            while (true){
                clientSocket.receive(datagramPacket);
//                ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
//                byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                if (buffer[0] == 1){
                    dataLogger.writeToFile(Arrays.toString(buffer).getBytes());
                }
            }
        } catch (SocketException e){
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Timeout!");
        }
    }
}
