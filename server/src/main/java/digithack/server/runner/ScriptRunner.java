package digithack.server.runner;

import digithack.server.k_nighbours.MyKNearestNeighbours;
import digithack.server.models.ImageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

@Component
public class ScriptRunner {

    private final MyKNearestNeighbours nearestNeighbours;

    @Autowired
    public ScriptRunner(MyKNearestNeighbours nearestNeighbours) {
        this.nearestNeighbours = nearestNeighbours;
    }

    public List<ImageModel> findNeighbours(Path imagePath) throws IOException, InterruptedException {
        String coords = getPythonRes(imagePath);
        ImageModel images = new ImageModel(coords);
        return nearestNeighbours.findKNearestNeighbours(images, 10);
    }

    private String getPythonRes(Path imagePath) throws IOException, InterruptedException {
        String scriptPath = "C:\\Users\\Daniil\\Desktop\\digit_hack\\server\\src\\main\\resources\\scripts\\neural.py";

        ProcessBuilder processBuilder = new ProcessBuilder("python", scriptPath);
        Process demoProc = processBuilder.start();

        OutputStream outputStream = demoProc.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));
        writer.println(imagePath);
        writer.flush();
        demoProc.waitFor();

        Thread.sleep(15000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(demoProc.getInputStream()));
        String result1 = reader.readLine();
        String result2 = reader.readLine();



        //System.out.println(result1);
        //System.out.println(result2);

        return result1;
    }
}
