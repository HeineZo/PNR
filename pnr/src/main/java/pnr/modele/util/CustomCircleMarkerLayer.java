package pnr.modele.util;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Handles pie charts
 */
public class CustomCircleMarkerLayer extends MapLayer {

    private final MapPoint mapPoint;
    private final Circle circle;

    /**
     * CustomCircleMarkerLayer constructor
     * @param mapPoint mappoint
     */
    public CustomCircleMarkerLayer(MapPoint mapPoint) {
        this.mapPoint = mapPoint;

        this.circle = new Circle(3, Color.RED);

        this.getChildren().add(circle);
    }

    @Override
    protected void layoutLayer() {
        Point2D point2d = this.getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());

        circle.setTranslateX(point2d.getX());
        circle.setTranslateY(point2d.getY());
    }
}
