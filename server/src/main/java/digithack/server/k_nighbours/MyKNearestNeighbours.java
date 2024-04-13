package digithack.server.k_nighbours;

import digithack.server.models.ExhibitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class MyKNearestNeighbours {
    private final List<ExhibitModel> exhibits;

    @Autowired
    public MyKNearestNeighbours(List<ExhibitModel> exhibits) {
        this.exhibits = exhibits;
    }

    public List<ExhibitModel> findKNearestNeighbours(ExhibitModel exhibit, int k) {
        return exhibits.stream()
                .sorted(Comparator.comparing(o -> ExhibitModel.getDistance(o, exhibit)))
                .limit(k)
                .toList();
    }
}
