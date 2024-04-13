package digithack.server.models;

import digithack.server.entities.Exhibit;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class ExhibitModel {
    private Long exhibitId;

    private String exhibitName;

    private String exhibitDescription;

    private String exhibitGroup;

    private double[] coords;

    public ExhibitModel(Exhibit exhibit) {
        this.exhibitId = exhibit.getExhibitId();
        this.exhibitName = exhibit.getExhibitName();
        this.exhibitDescription = exhibit.getExhibitDescription();
        this.exhibitGroup = exhibit.getExhibitGroup();

        this.coords = Arrays.stream(exhibit.getCoords().split(" "))
                .mapToDouble(Double::parseDouble)
                .toArray();
    }

    public static Double getDistance(ExhibitModel exhibitModel1, ExhibitModel exhibitModel2) {
        double squaresSum = 0;
        for (int i = 0; i < exhibitModel1.getCoords().length; i++) {
            squaresSum += Math.pow(exhibitModel1.getCoords()[i] - exhibitModel2.getCoords()[i], 2);
        }
        return Math.sqrt(squaresSum);
    }
}
