package digithack.server.k_nighbours;

import digithack.server.models.ImageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class MyKNearestNeighbours {
    private final List<ImageModel> images;

    @Autowired
    public MyKNearestNeighbours(List<ImageModel> images) {
        this.images = images;
    }

    public List<ImageModel> findKNearestNeighbours(ImageModel images, int k) {
        return this.images.stream()
                .sorted(Comparator.comparing(o -> ImageModel.getDistance(o, images)))
                .limit(k)
                .toList();
    }
}
