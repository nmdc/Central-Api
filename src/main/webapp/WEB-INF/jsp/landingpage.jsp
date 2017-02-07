<!DOCTYPE HTML SYSTEM>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <title>NMDC landing page</title>   

    <link rel="stylesheet" href="https://openlayers.org/en/v3.19.1/css/ol.css" type="text/css">
    <link rel="stylesheet" href="/metadata-api/css/metadata-details.css" type="text/css">

    <script src="https://openlayers.org/en/v3.19.1/build/ol.js" type="text/javascript"></script>
        
    </head>
    <body> 
	<div>
		<div id="nmdcLogo">
			<img src="../css/nmdclogo.png">
		</div>
		<div id="map"></div>
    </div>
    
	<div class="metadata">
		<c:out value="${page}" escapeXml="false"/>
	</div>

    <div id="map" class="map"></div>
    <script type="text/javascript">
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
    
      var format = new ol.format.WKT();
      var bbox = document.getElementById('bbox');
      if ( bbox.getAttribute("wkt") != null ) {
	      var feature = format.readFeature( bbox.getAttribute("wkt") );
	      feature.getGeometry().transform('EPSG:4326', 'EPSG:3857');
	
	      var vector = new ol.layer.Vector({
	        source: new ol.source.Vector({
	          features: [feature]
	        })
	      });

    	  map.addLayer( vector )
      	  var extent = vector.getSource().getExtent();
      	  map.getView().fit(extent, map.getSize());
      }
    </script>   	
    </body>
</html>