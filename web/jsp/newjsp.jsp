<%-- 
    Document   : newjsp
    Created on : 07-10-2017, 13:51:22
    Author     : vancu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <title>add</title>
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
        <meta charset="utf-8">
    </head>
    <body>
        <div id="map" style = 'height:300px;
                width:500px'></div>
        <style>
            /* Always set the map height explicitly to define the size of the div
             * element that contains the map. */
            #map {
                height: 100%;
                
            }
            /* Optional: Makes the sample page fill the window. */
            html, body {
                height: 100%;
                margin: 0px;
                padding: 0px;
            }
        </style>
        <!--    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBcryatp7ZW9NUdqjQM23Ig-5xozAuZNec"></script>
            <script>
              // In the following example, markers appear when the user clicks on the map.
              // Each marker is labeled with a single alphabetical character.
              var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
              var labelIndex = 0;
        
              function initialize() {
                var bangalore = { lat: 12.97, lng: 77.59 };
                var map = new google.maps.Map(document.getElementById('map'), {
                  zoom: 12,
                  center: bangalore
                });
        
                // This event listener calls addMarker() when the map is clicked.
                google.maps.event.addListener(map, 'click', function(event) {
                  addMarker(event.latLng, map);
                });
        
                // Add a marker at the center of the map.
                addMarker(bangalore, map);
              }
        
              // Adds a marker to the map.
              function addMarker(location, map) {
                // Add the marker at the clicked location, and add the next-available label
                // from the array of alphabetical characters.
                var marker = new google.maps.Marker({
                  position: location,
                  label: labels[labelIndex++ % labels.length],
                  map: map
                });
              }
        
              google.maps.event.addDomListener(window, 'load', initialize);
            </script>
           <body>
            <div id="map"></div>
          </body>-->
        <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBcryatp7ZW9NUdqjQM23Ig-5xozAuZNec&callback=initMap">
        </script>
        <script>
            var map, infoWindow;
            function initMap() {
                map = new google.maps.Map(document.getElementById('map'), {
                    center: {lat: -34.397, lng: 150.644},
                    zoom: 13
                });
                var geocoder = new google.maps.Geocoder;
                infoWindow = new google.maps.InfoWindow;
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(function (position) {
                        var pos = {
                            lat: position.coords.latitude,
                            lng: position.coords.longitude
                        };

                        //   infoWindow.setPosition(pos);
                        //infoWindow.setContent('Location found.');
                        // infoWindow.open(map);
                        map.setCenter(pos);
                        var marker = new google.maps.Marker({
                            position: pos,
                            map: map,
                            title: "<div style = 'height:60px;width:200px'><b>Your location:</b><br />Latitude: " +
                                    position.coords.latitude + "<br />Longitude: " + position.coords.longitude + "</div>"
                        });
                        geocoder.geocode({'location': pos}, function (results, status) {
                            if (status === 'OK') {
                                if (results[0]) {
                                    address = results[0].formatted_address;
                                }
                            }
                        });
                        google.maps.event.addListener(marker, "click", function () {
                            var infoWindow = new google.maps.InfoWindow();
                            infoWindow.setContent(marker.title + address);
                            infoWindow.open(map, marker);
                        });

                    }, function () {
                        handleLocationError(true, infoWindow, map.getCenter());
                    });
                } else {
                    // Browser doesn't support Geolocation
                    handleLocationError(false, infoWindow, map.getCenter());
                }
            }

            function handleLocationError(browserHasGeolocation, infoWindow, pos) {
                infoWindow.setPosition(pos);
                infoWindow.setContent(browserHasGeolocation ?
                        'Error: The Geolocation service failed.' :
                        'Error: Your browser doesn\'t support geolocation.');
                infoWindow.open(map);
            }
        </script>
       
        
    </body>
</html>
