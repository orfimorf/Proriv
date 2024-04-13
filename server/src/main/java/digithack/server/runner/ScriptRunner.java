package digithack.server.runner;

import digithack.server.entities.Exhibit;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ScriptRunner {
    public static List<Exhibit> run(MultipartFile image) {
        throw new UnsupportedOperationException("МЕТОД НЕ РЕАЛИЗОВАН");
    }

    private String getPythonRes() throws IOException, InterruptedException {
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
