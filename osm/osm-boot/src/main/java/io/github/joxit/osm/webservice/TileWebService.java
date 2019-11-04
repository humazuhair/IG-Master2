package io.github.joxit.osm.webservice;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * C'est le controlleur de l'application, il faut le déclarer comme tel et activer les CrossOrigin
 *
 * @author Jones Magloire @Joxit
 * @since 2019-11-03
 */
public class TileWebService {

  /**
   * Cette méthode est le point d'entrée de l'API, il prend les requetes au format `/{z}/{x}/{y}.png`
   * Attetion, il doit renvoyer le header Content-Type image/png; voir les MediaType de Spring
   *
   * @param z zoom
   * @param x coordonée
   * @param y coordonée
   *
   * @return l'image au format PNG
   */
  public byte[] getTile(int z, int x, int y) {
    return null;
  }
}
