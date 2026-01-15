import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

//TODO: make catch clause more robust.
//TODO: Add Javadoc.
public class rawDataLogger {
    private Path directoryPath;
    private Path filePath;
    private final BufferedReader reader;
    //dname is Directory File name and fName is filename.
    public rawDataLogger (String dName, String fName) {
        createDirectory(dName);
        createFile(fName);
        try {
            reader = Files.newBufferedReader(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] readBytes(){
        try {
            return Files.readAllBytes(filePath);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    public String readLine(){
        try {
            return reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
    public List<String> readFile(){
        try{
            return Files.readAllLines(filePath);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

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
