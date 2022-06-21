package pnr.modele.util;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CustomCircleMarkerLayer extends MapLayer {

    private final MapPoint mapPoint;
    private final Circle circle;

    /**
     * @param mapPoint le point (latitude et longitude) où afficher le cercle
     * @see com.gluonhq.maps.MapPoint
     */
    public CustomCircleMarkerLayer(MapPoint mapPoint) {
        this.mapPoint = mapPoint;

        /* Cercle rouge de taille 5 */
        this.circle = new Circle(5, Color.RED);

        /* Ajoute le cercle au MapLayer */
        this.getChildren().add(circle);
    }

    @Override
    protected void layoutLayer() {
        /* Conversion du MapPoint vers Point2D */
        Point2D point2d = this.getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());

        /* Déplace le cercle selon les coordonnées du point */
        circle.setTranslateX(point2d.getX());
        circle.setTranslateY(point2d.getY());
    }
}
