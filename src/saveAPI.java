import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class saveAPI {
    public saveAPI (){
        Path defaultPath = Paths.get("SampleData.txt");
        System.out.println(defaultPath.toAbsolutePath());
    }
}
