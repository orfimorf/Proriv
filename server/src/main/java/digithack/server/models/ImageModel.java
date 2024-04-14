package digithack.server.models;

import digithack.server.entities.Image;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class ImageModel {
    private String imageName;

    private double[] coords;

    private Long exhibitId;

    public ImageModel(Image image) {
        this.imageName = image.getImageName();
        this.coords = parseCoords(image.getCoords());
        this.exhibitId = image.getObjectId();
    }

    public ImageModel(String coords) {
        this.coords = parseCoords(coords);
    }

    public double[] parseCoords(String coords) {
        System.out.println(coords);
        return Arrays.stream(coords.split(" "))
                .mapToDouble(Double::parseDouble)
                .toArray();
    }

    public static Double getDistance(ImageModel imageModel1, ImageModel imageModel2) {
        double squaresSum = 0;
        for (int i = 0; i < imageModel1.getCoords().length; i++) {
            squaresSum += Math.pow(imageModel1.getCoords()[i] - imageModel2.getCoords()[i], 2);
        }
        return Math.sqrt(squaresSum);
    }
}
