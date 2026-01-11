import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class rawDataLogger {
    private Path directoryPath;
    private Path filePath;

    public void clearFile(){
        try{
            Files.write(filePath, "".getBytes());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void writeToFile(byte[] buffer){
        try{
            Files.write(filePath, buffer, StandardOpenOption.APPEND);
            Files.write(filePath, System.getProperty("line.separator").getBytes(), StandardOpenOption.APPEND);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createDirectory(String name) {
        try {
                directoryPath = Paths.get(name);
            if (!Files.exists(directoryPath)) {
                Files.createDirectory(directoryPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createFile(String name) {
        try {
            filePath = Paths.get(directoryPath + "/" + name);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
