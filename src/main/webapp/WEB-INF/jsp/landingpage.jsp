<!DOCTYPE HTML SYSTEM>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <title>NMDC landing page</title>   

    <link rel="stylesheet" href="https://openlayers.org/en/v3.19.1/css/ol.css" type="text/css">
    <style>

    </style>
    <script src="https://openlayers.org/en/v3.19.1/build/ol.js" type="text/javascript"></script>
        
<style>
 
.box {
	float: center;
	/**border:1px solid black;*/
}
loopBox {
	font-weight: bold;
	padding-top: 50px;
}
.label {
  font-style: italic;
  display: inline-block;
  width: 200px;
  padding-left: 5px;
}
.value {
	text-indent: 200px;
}
.loopLabel {
	font-style: bold;
	font-size: 110%;
}
.metadata {
	width:50%;
	float: left;
}
.map {
	height: 400px;
    width: 48%;
    float:right;
}
.display-field {
  display: inline-block;
  padding-left: 50px;
  text-indent: -50px;
  vertical-align: top;
  width: 200px; /* for testing purposes only */
}
</style>
        
        
    </head>
    <body> 
	<div class="metadata">
		<c:out value="${page}" escapeXml="false"/>
	</div>

    <div id="map" class="map"></div>
    <script type="text/javascript">
      var format = new ol.format.WKT();
      var bbox = document.getElementById('bbox');
      var vector = null;
      if ( bbox != null ) {
	      var feature = format.readFeature( bbox.getAttribute("wkt") );
	      feature.getGeometry().transform('EPSG:4326', 'EPSG:3857');
	
	      vector = new ol.layer.Vector({
	        source: new ol.source.Vector({
	          features: [feature]
	        })
	      });
      }
    
      var map = new ol.Map({
        target: 'map',
        layers: [
          new ol.layer.Tile({
            source: new ol.source.OSM()
          })
        ],
        view: new ol.View({
          center: ol.proj.fromLonLat([20, 65.82]),
          zoom: 3
        })     
      });

      if ( bbox != null ) {
    	map.addLayer( vector )
      	var extent = vector.getSource().getExtent();
      	map.getView().fit(extent, map.getSize());
      }
    </script>
    
    </body>
</html>