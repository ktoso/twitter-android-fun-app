package pl.compendium.hello.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.google.android.maps.*;
import pl.compendium.hello.R;
import pl.compendium.hello.maps.CustomItemizedOverlay;
import pl.compendium.hello.maps.LatLonPoint;
import roboguice.activity.RoboMapActivity;
import roboguice.inject.InjectView;

import java.util.List;

public class MyMapActivity extends RoboMapActivity {

    @InjectView(R.id.mapview)
    private MapView mapView;
    private final LatLonPoint warszawa = new LatLonPoint(52.13, 21.0);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);

        mapView.setBuiltInZoomControls(true);

        List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = getResources().getDrawable(R.drawable.ic_kapibara);
        CustomItemizedOverlay itemizedOverlay = new CustomItemizedOverlay(drawable, this);

        GeoPoint point = warszawa;
        OverlayItem overlayitem = new OverlayItem(point, "Hello", "Pi pi!");

        itemizedOverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedOverlay);

        MapController mapController = mapView.getController();

        mapController.animateTo(point);
        mapController.setZoom(6);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

}
