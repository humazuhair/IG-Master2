package io.github.joxit.osm.service;

import io.github.joxit.osm.model.Tile;

/**
 * Service pour retourner les tuiles.
 *
 * @author Jones Magloire @Joxit
 * @since 2019-11-03
 */
public class TileService {

  /**
   * Ici il faut prendre les coordonnées de la tuile et renvoyer la donnée PNG associée.
   * Vous pouvez y ajouter des fonctionnalités en plus pour améliorer les perfs.
   *
   * @param tile qu'il faut renvoyer
   *
   * @return le byte array au format png
   */
  public byte[] getTile(Tile tile) {
    return null;
  }
}
