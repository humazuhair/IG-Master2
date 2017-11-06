<!--
 Copyright (C) 2016-2017  Jones Magloire @Joxit

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
-->
<catalog>
  <!-- Begin of tag -->
  <material-card ref="catalog-tag" class="catalog">
    <div class="material-card-title-action">
      <h2>Pizzas of { pizzeria.url() }</h2>
    </div>
    <div hide="{ pizzeria.catalog.loadend }" class="spinner-wrapper">
      <material-spinner></material-spinner>
    </div>
    <table show="{ pizzeria.catalog.loadend }" style="border: none;">
      <thead>
        <tr>
          <th class="material-card-th-left">Nom</th>
          <th>Prix</th>
          <th class="material-card-th-left ingredients">Ingredients</th>
        </tr>
      </thead>
      <tbody>
        <tr each="{ item in pizzeria.catalog.pizze }">
          <td class="material-card-th-left">{ item.name }</td>
          <td>{ item.prix }</td>
          <td class="material-card-th-left ingredients">{ item.ingredients.join(', ') }</td>
        </tr>
      </tbody>
    </table>
  </material-card>

  <script>
    pizzeria.catalog.instance = this;
    this.mixin('rg.router');
    pizzeria.catalog.display = function () {
      fetch(pizzeria.url()).then(function(response) {
        if (response.ok) {
          pizzeria.snackbar(response.statusText);
          return response.json();
        }
        throw new Error(response.statusText);
      }).then(function(json) {
        pizzeria.catalog.pizze = json;
      }).catch(function(err) {
        pizzeria.catalog.pizze = [];
        pizzeria.snackbar('Server not found', true);
        return err;
      }).then(function(){
        pizzeria.catalog.loadend = true;
        pizzeria.catalog.instance.update();
      });
    };
    pizzeria.catalog.display();
  </script>
  <!-- End of tag -->
</catalog>