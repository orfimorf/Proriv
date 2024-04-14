package digithack.server.k_nighbours;

import digithack.server.entities.Image;
import digithack.server.models.ImageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class MyKNearestNeighbours {
    private final List<ImageModel> images;

    @Autowired
    public MyKNearestNeighbours(List<ImageModel> images) {
        this.images = images;
    }

    public List<ImageModel> findKNearestNeighbours(ImageModel image, int k) {
        return this.images.stream()
                .sorted(Comparator.comparing(o -> ImageModel.getDistance(o, image)))
                .limit(k)
                .toList();
//        ImageModel first = this.images.get(0);
//        ImageModel second = this.images.get(1);
//        ImageModel third = this.images.get(2);
//        double firstDIst = Double.MAX_VALUE;
//        double secondDist = Double.MAX_VALUE;
//        double thirdDist = Double.MAX_VALUE;
//        for (ImageModel thisImage : this.images) {
//            var dist = ImageModel.getDistance(thisImage, image);
//            if (dist < firstDIst) {
//                thirdDist = secondDist;
//                secondDist = firstDIst;
//                firstDIst = dist;
//
//                third = second;
//                second = first;
//                first = thisImage;
//            }
//            else if (dist < secondDist) {
//                thirdDist = secondDist;
//                secondDist = dist;
//
//                third = second;
//                second = thisImage;
//            }
//            else if (dist < thirdDist) {
//                thirdDist = dist;
//                third = thisImage;
//            }
//        }
//        List<ImageModel> result = new ArrayList<>();
//        System.out.println(firstDIst);
//        System.out.println(secondDist);
//        System.out.println(thirdDist);
//        result.add(first);
//        result.add(second);
//        result.add(third);
//        return result;
    }
}
