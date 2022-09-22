package io.github.joxit.osm.controller;

import io.github.joxit.osm.model.Tile;
import io.github.joxit.osm.service.TileService;
import mil.nga.sf.geojson.GeoJsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * C'est le controlleur de l'application, il faut le déclarer comme tel et activer les CrossOrigin
 *
 * @author Jones Magloire @Joxit
 * @since 2019-11-03
 */
@CrossOrigin
@RestController
@RequestMapping
public class TileController {

  /**
   * Cette méthode est le point d'entrée de l'API, il prend les requêtes au format `/{z}/{x}/{y}.png`.
   * Attention, il doit renvoyer le header Content-Type image/png; voir les MediaType de Spring
   *
   * @param z zoom
   * @param x coordonée
   * @param y coordonée
   * @return l'image au format PNG
   */
  @Autowired
  private TileService tileService;

  @RequestMapping(value = "/{z}/{x}/{y}.png", produces = "image/png", method = RequestMethod.GET)
  public byte[] getTile(int z, int x, int y) {
    Tile tile = new Tile(z, x, y);
    return tileService.getTile(tile);
  }

  /**
   * Cette méthode est le point d'entrée des préfectures, il prend les requêtes sur l'entrée `/prefectures.geojson`.
   * Attention, il doit renvoyer le header Content-Type application/json
   *
   * @return String representant le GeoJSON des prefectures
   */
  public String getPrefectures() throws IOException {
    return null;
  }

  /**
   * Cette méthode est le point d'entrée de l'API POIs sous persistence, il prend les requêtes sur l'entrée `/pois.geojson`.
   * Attention, il doit renvoyer le header Content-Type application/json
   *
   * @return le geojson des POIs à renvoyer
   */
  public GeoJsonObject getPOIs() {
    return null;
  }
}
