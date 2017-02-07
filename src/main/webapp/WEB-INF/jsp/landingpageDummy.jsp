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

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<script   src="https://code.jquery.com/jquery-2.2.3.min.js" integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo=" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
       
    </head>
    <body> 
	<div>
		<div id="nmdcLogo">
			<img src="css/nmdclogo.png">
		</div>
		<div id="map"></div>
    </div>
    
	<div class="metadata">

<p>
<div style="text-align: left;margin-left: 20%; margin-right:10%;line-height: 1.5;">
	<div id="isThisAHeadingOrMetadata">Norwegian Marine Data Center repository, 2017 <br/><br/></div>
	<div id="doi"></div>
	<div id="originalDataset"></div>
   <div id="title"><h4><b>South western part of the Barents Sea - Fugløya-Bear Island Section - Atlantic Water</b></h4></div>
   <div id="creator">Ingvaldsen Randi</div>
   <div id="originatingCenter">Institue of Marine Research (IMR)</div>
   <div style="align:left"><br/><b>Abstract</b></div>
   <div id="abstract">The dataset includes Institute of Marine Research data from mooring IMR_1989001 situated below the current Askøy bridge prior to it being build. Instrument type Aanderaa RCM 4, serial number 247, measuring at 6m depth below surface.</div>
</div>
</p>

  <div class="panel-group" id="accordionMeta" >
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
            <span class="glyphicon glyphicon-minus"></span>
            Metadata
          </a>
        </h4>
      </div>
      <div id="collapseOne" class="panel-collapse collapse in">
        <div class="panel-body">

<div class="box" id="title"><span class="metadataLabel">Title</span><span class="value">Fram Strait/ACOBAR: XBT measurements and derived values - Fram Strait - September 2010</span></div>
<div class="box" id="license"><span class="metadataLabel">License</span><span class="value"></span></div>
<div class="box" id="doi"><span class="metadataLabel">DOI</span><span class="value"></span></div>
<div id="dataset_citation">
   <div class="loop"><span class="metadataLabel">Data Set Citation</span></div>
   <div class="box" id="creator"><span class="metadataLabel">Creator</span><span class="value">Ingvaldsen Randi</span></div>
   <div class="box" id="title"><span class="metadataLabel">Title</span><span class="value">South western part of the Barents Sea - Fugløya-Bear Island Section - Atlantic Water</span></div>
   <div class="box" id="releaseData"><span class="metadataLabel">Release Data</span><span class="value">2016-10-11T00:00:00:00Z</span></div>
   <div class="box" id="releasePlace"><span class="metadataLabel">Release Place</span><span class="value"></span></div>
   <div class="box" id="publisher"><span class="metadataLabel">Publisher</span><span class="value"></span></div>
   <div class="box" id="version"><span class="metadataLabel">Version</span><span class="value"></span></div>
</div>
<div id="personnel">
   <div class="loop"><span class="metadataLabel">Personnel</span></div>
   <div class="box" id="role"><span class="metadataLabel">Role</span><span class="value">Investigator</span></div>
   <div class="box" id="firstName"><span class="metadataLabel">First Name</span><span class="value">Cecilie</span></div>
   <div class="box" id="lastName"><span class="metadataLabel">Last Name</span><span class="value">Broms</span></div>
   <div class="box" id="email"><span class="metadataLabel">Email</span><span class="value"></span></div>
   <div class="box" id="phone"><span class="metadataLabel">Phone</span><span class="value"></span></div>
   <div class="box" id="address"><span class="metadataLabel">Contact Address</span><span class="value"></span></div>
   <div class="box" id="city"><span class="metadataLabel">Contact City</span><span class="value"></span></div>
   <div class="box" id="phone"><span class="metadataLabel">Contact Phone</span><span class="value"></span></div>
   <div class="box" id="country"><span class="metadataLabel">Contact Country</span><span class="value"></span></div>
</div>
<div id="parameters">
   <div class="loop"><span class="metadataLabel">Parameters</div>
   <div class="loop" id="path"><span class="metadataLabel">Parameter</span><span class="value">EARTH SCIENCE/BIOSPHERE/AQUATIC ECOSYSTEMS/PLANKTON/ZOOPLANKTON/&gt;</span></div>
</div>
<div id="temporal_coverage">
   <div class="box" id="start"><span class="metadataLabel">Start Date</span><span class="value"></span></div>
   <div class="box" id="stop"><span class="metadataLabel">Stop Date</span><span class="value"></span></div>
</div>
<div class="box" id="datasetProgress"><span class="metadataLabel">Data Set Progress</span><span class="value">Finished</span></div>
<div class="box" id="location"><span class="metadataLabel">Detailed Location</span><span class="value"></span></div>
<div class="box" id="projectShortname"><span class="metadataLabel">Project Short Name</span><span class="value"></span></div>
<div class="box" id="projectLongname"><span class="metadataLabel">Project Long Name</span><span class="value"></span></div>
<div class="box" id="location"><span class="metadataLabel">Detailed Location</span><span class="value"></span></div>
<div id="datacenter">
   <div class="box" id="datacenterShortname"><span class="metadataLabel">Data Center Short Name</span><span class="value"></span></div>
   <div class="box" id="datacenterLongname"><span class="metadataLabel">Data Center Long Name</span><span class="value"></span></div>
   <div class="box" id="datacenterUrl"><span class="metadataLabel">Data Center URL</span><span class="value"></span></div>
   <div id="personnel">
      <div class="loop"><span class="metadataLabel">Data Center Personnel</span></div>
   </div>
</div>
<div class="box" id="summary"><span class="metadataLabel">Abstract</span><span class="value">The dataset includes Institute of Marine Research data from mooring IMR_1989001 situated below the current Askøy bridge prior to it being build. Instrument type Aanderaa RCM 4, serial number 247, measuring at 6m depth below surface.</span></div>
<div id="relatedUrl">
   <div class="loop"></div>
   <div class="box" id="url"><span class="metadataLabel">Url</span><span class="value">http://thredds.nersc.no/thredds/dodsC/arcticData/framStrait/NERSC_ARC_PHYS_OBS_XBT_2010_v1.nc</span></div>
   <div class="box" id="url"><span class="metadataLabel">Url</span><span class="value">http://thredds.nersc.no/thredds/arcticData/fram-strait.html?dataset=NERSC_ARC_PHYS_OBS_XBT_2010_v1</span></div>
   <div class="box" id="url"><span class="metadataLabel">Url</span><span class="value">http://thredds.nersc.no/thredds/fileServer/arcticData/framStrait/NERSC_ARC_PHYS_OBS_XBT_2010_v1.nc</span></div>
</div>
<div class="box" id="boundingbox"><span class="metadataLabel">Bounding Box</span><span class="value"></span></div>
<div id="boundingbox">
   <div class="box" id="southernmostLatitude"><span class="metadataLabel">Southernmost Latitude</span><span class="value">78.15633</span></div>
   <div class="box" id="NorthernmostLatitude"><span class="metadataLabel">Northernmost Latitude</span><span class="value">79.66165</span></div>
   <div class="box" id="WesternmostLongitude"><span class="metadataLabel">Westernmost Longitude</span><span class="value">-5.50927</span></div>
   <div class="box" id="EasternmostLongitude"><span class="metadataLabel">Easternmost Longitude</span><span class="value">4.62515</span></div>
</div>


<div class="box" id="boundingboxWKT"><span class="metadataLabel">Bounding Box as WKT</span><span class="value">POLYGON((-5.50927 78.15633,4.62515 78.15633,4.62515 79.66165,-5.50927 79.66165,-5.50927
      78.15633))</span></div>
<div id="bbox" wkt="POLYGON((-5.50927 78.15633,4.62515 78.15633,4.62515 79.66165,-5.50927 79.66165,-5.50927 78.15633))"></div>

	</div>
	
        </div>
      </div>
    </div>	

    
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