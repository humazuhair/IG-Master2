var pizzeria={URL_QUERY_PARAM_REGEX:/[&?]url=/,URL_PARAM_REGEX:/^url=/,url:function(){if(!pizzeria._url){var a=pizzeria.getUrlQueryParam();if(a)try{return pizzeria._url=pizzeria.decodeURI(a),pizzeria._url}catch(a){console.log(a)}}return pizzeria._url},getUrlQueryParam:function(){var a=window.location.search;if(pizzeria.URL_QUERY_PARAM_REGEX.test(a)){var t=a.split(/^\?|&/).find(function(a){return a&&pizzeria.URL_PARAM_REGEX.test(a)});return t?t.replace(pizzeria.URL_PARAM_REGEX,""):t}},decodeURI:function(a){return a.startsWith("http")?window.decodeURIComponent(a):atob(a)},catalog:{}};window.addEventListener("DOMContentLoaded",function(){riot.mount("*")}),riot.tag2("app",'<header> <material-navbar> <div class="logo">Pizzeria</div> </material-navbar> </header> <main> <catalog></catalog> <material-snackbar></material-snackbar> </main> <footer> <material-footer> <a class="material-footer-logo" href="https://joxit.dev/IG-Master2/ui/">Pizzeria UI</a> <ul class="material-footer-link-list"> <li> <a href="https://github.com/Joxit/IG-Master2">Projet sur GitHub</a> </li> <li> <a href="https://github.com/Joxit/IG-Master2/tree/master/beamer">Cours pour l\'Institut Galilée</a> </li> </ul> </material-footer> </footer>',"","",function(a){this.mixin("rg.router"),this.router.add({name:"home",url:""}),this.router.on("go",a=>{switch(a.name){case"home":pizzeria.catalog.display&&(pizzeria.catalog.loadend=!1,pizzeria.catalog.display())}}),pizzeria.appTag=this,pizzeria.snackbar=function(a,t){pizzeria.appTag.tags["material-snackbar"].addToast({message:a,isError:t})},pizzeria.errorSnackbar=function(a){return pizzeria.snackbar(a,!0)},this.router.start()}),riot.tag2("catalog",'<material-card ref="catalog-tag" class="catalog"> <div class="material-card-title-action"> <h2>Pizzas of {pizzeria.url()}</h2> </div> <div hide="{pizzeria.catalog.loadend}" class="spinner-wrapper"> <material-spinner></material-spinner> </div> <div show="{pizzeria.catalog.loadend}" class="buttons-list"> <material-button onclick="pizzeria.catalog.display(\'lazzy\')">Lazzy</material-button> <material-button onclick="pizzeria.catalog.display(\'eager\')">Eager</material-button> <material-button onclick="pizzeria.catalog.display(\'custom\')">Custom</material-button> <material-button onclick="pizzeria.catalog.display(\'spring-data-jpa\')">Spring Data JPA</material-button> <material-button onclick="pizzeria.catalog.display(\'errorHandled\')">Error Handled</material-button> <material-button onclick="pizzeria.catalog.display(\'errorNotHandled\')">Error Not Handled</material-button> </div> <table show="{pizzeria.catalog.loadend}" style="border: none;"> <thead> <tr> <th class="material-card-th-left">Nom</th> <th>Prix</th> <th class="material-card-th-left ingredients">Ingredients</th> </tr> </thead> <tbody> <tr each="{item in pizzeria.catalog.pizze}"> <td class="material-card-th-left">{item.name}</td> <td>{item.prix}</td> <td class="material-card-th-left ingredients">{item.ingredients.join(\', \')}</td> </tr> </tbody> </table> </material-card>',"","",function(a){pizzeria.catalog.instance=this,this.mixin("rg.router"),pizzeria.catalog.display=function(a){fetch(pizzeria.url()+"?type="+(a||"lazzy")).then(function(a){if(a.ok)return pizzeria.snackbar(a.statusText),a.json();if(500==a.status||400==a.status)return a.text().then(function(a){throw a});throw{message:"Server Not Found"}}).then(function(a){pizzeria.catalog.pizze=a}).catch(function(a){var t;if(pizzeria.catalog.pizze=[],"string"==typeof a)try{t=JSON.parse(a.message||a).message}catch(a){t="Not JSON Error"}else t=a;return pizzeria.snackbar(t,!0),a}).then(function(){pizzeria.catalog.loadend=!0,pizzeria.catalog.instance.update()})},pizzeria.catalog.display()});