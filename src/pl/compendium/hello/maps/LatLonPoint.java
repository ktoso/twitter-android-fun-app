package pl.compendium.hello.maps;

import com.google.android.maps.GeoPoint;

public class LatLonPoint extends GeoPoint {
      public LatLonPoint(double latitude, double longitude) {
        super((int) (latitude * 1E6), (int) (longitude * 1E6));
      }
    }