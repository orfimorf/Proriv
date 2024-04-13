package digithack.server.runner;

import digithack.server.k_nighbours.MyKNearestNeighbours;
import digithack.server.models.ImageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class ScriptRunner {

    private final MyKNearestNeighbours nearestNeighbours;

    @Autowired
    public ScriptRunner(MyKNearestNeighbours nearestNeighbours) {
        this.nearestNeighbours = nearestNeighbours;
    }

    public List<ImageModel> findNeighbours(MultipartFile image) throws IOException, InterruptedException {
        String coords = getPythonRes(image);
        ImageModel images = new ImageModel(coords);
        return nearestNeighbours.findKNearestNeighbours(images, 10);
    }

    private String getPythonRes(MultipartFile image) throws IOException, InterruptedException {
        // Путь к Python-скрипту
        String scriptPath = "C:\\Users\\Daniil\\Desktop\\haha_2\\hihi\\src\\main\\python\\test.py";

        // Создание процесса для выполнения Python-скрипта
        ProcessBuilder processBuilder = new ProcessBuilder("python", scriptPath);
        Process demoProc = processBuilder.start();

        // Создание BufferedReader для чтения стандартного вывода процесса Python
        BufferedReader reader = new BufferedReader(new InputStreamReader(demoProc.getInputStream()));
        StringBuilder pythonOutput = new StringBuilder();
        String line;

        // Чтение строк из потока вывода процесса Python
        while ((line = reader.readLine()) != null) {
            pythonOutput.append(line).append("\n");
        }

        // Ожидание завершения процесса Python
        demoProc.waitFor();

        // Вывод полученных данных из Python
        return pythonOutput.toString();

        // Здесь вы можете обработать полученные данные
    }
}
