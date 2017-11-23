var map, infoWindow, myLocation = {
    lat: 30.0,
    lng: 150.0
};
var latl;
var lngl;
var markers = [];
function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
            'Error: The Geolocation service failed.' :
            'Error: Your browser doesn\'t support geolocation.');
    infoWindow.open(map);
}
//search

function initAutocomplete() {
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
            myLocation = pos;
            latl = pos.lat;
            lngl = pos.lng;
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
            markers.push(marker);
            geocoder.geocode({'location': pos}, function (results, status) {
                if (status === 'OK') {
                    if (results[0]) {
                        address = results[0].formatted_address;
                        document.getElementById('iddiadiem').value = address;
                        document.getElementById('idlatlng').value = position.coords.latitude + "," + position.coords.longitude;
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


    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: latl, lng: lngl},
        zoom: 13,
        mapTypeId: 'roadmap'
    });

    // Create the search box and link it to the UI element.
    var input = document.getElementById('pac-input');
    var searchBox = new google.maps.places.SearchBox(input);
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

    // Bias the SearchBox results towards current map's viewport.
    map.addListener('bounds_changed', function () {
        searchBox.setBounds(map.getBounds());
    });


    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    searchBox.addListener('places_changed', function () {
        var places = searchBox.getPlaces();

        if (places.length == 0) {
            return;
        }

        // Clear out the old markers.

        markers.forEach(function (marker) {
            marker.setMap(null);
        });
        markers = [];

        // For each place, get the icon, name and location.
        var bounds = new google.maps.LatLngBounds();
        places.forEach(function (place) {
            if (!place.geometry) {
                console.log("Returned place contains no geometry");
                return;
            }
//            var icon = {
//                url: place.icon,
//                size: new google.maps.Size(71, 71),
//                origin: new google.maps.Point(0, 0),
//                anchor: new google.maps.Point(17, 34),
//                scaledSize: new google.maps.Size(25, 25)
//            };

            // Create a marker for each place.
//            markers.push(new google.maps.Marker({
//                map: map,
//                icon: icon,
//                title: place.name,
//                position: place.geometry.location
//            }));
            marker = new google.maps.Marker({
                position: place.geometry.location,
                map: map,
                title: "<div style = 'height:60px;width:200px'><b>Your location:</b><br />Latitude: " +
                        place.geometry.location.lat() + "<br />Longitude: " + place.geometry.location.lng() + "</div>"
            });
            markers.push(marker);
            addYourLocationButton(map, marker);
            geocoder.geocode({'location': place.geometry.location}, function (results, status) {
                if (status === 'OK') {
                    if (results[0]) {
                        address = results[0].formatted_address;
                        document.getElementById('iddiadiem').value = address;
                        document.getElementById('idlatlng').value = place.geometry.location.lat()
                                + "," + place.geometry.location.lng();
                    }
                }
            });
            google.maps.event.addListener(marker, "click", function () {
                var infoWindow = new google.maps.InfoWindow();
                infoWindow.setContent(marker.title + address);
                infoWindow.open(map, marker);
            });
            if (place.geometry.viewport) {
                // Only geocodes have viewport.
                bounds.union(place.geometry.viewport);
            } else {
                bounds.extend(place.geometry.location);
            }
        });
        map.fitBounds(bounds);
    });
}
function addYourLocationButton(map, marker)
{
    
    firstChild.addEventListener('click', function () {
        goToMyLocation();
    });
    function goToMyLocation() {
        
        markers.forEach(function (marker) {
            marker.setMap(null);
        });
        markers = [];
        map.setCenter(myLocation);
        marker = new google.maps.Marker({
            position: myLocation,
            map: map,
            title: "<div style = 'height:60px;width:200px'><b>Your location:</b><br />Latitude: " +
                    myLocation.lat + "<br />Longitude: " + myLocation.lng + "</div>"
        });
        markers.push(marker);
        eocoder.geocode({'location': place.geometry.location}, function (results, status) {
            if (status === 'OK') {
                if (results[0]) {
                    address = results[0].formatted_address;
                    document.getElementById('iddiadiem').value = address;
                    document.getElementById('idlatlng').value = myLocation.lat
                            + "," + myLocation.lng;
                }
            }
        });
        google.maps.event.addListener(marker, "click", function () {
            var infoWindow = new google.maps.InfoWindow();
            infoWindow.setContent(marker.title + address);
            infoWindow.open(map, marker);
        });
        
    }
    google.maps.event.addListener(map, 'dragend', function () {
        $('#you_location_img').css('background-position', '0px 0px');
    });
    map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(controlDiv);
}
var controlDiv = document.createElement('div');

var firstChild = document.createElement('btn-location');
firstChild.style.backgroundColor = '#fff';

firstChild.style.borderRadius = '2px';
firstChild.style.boxShadow = '0 1px 4px rgba(0,0,0,0.3)';
firstChild.style.cursor = 'pointer';
firstChild.style.marginRight = '35px';
firstChild.style.padding = '0px';
firstChild.title = 'Your Location';
controlDiv.appendChild(firstChild);

var secondChild = document.createElement('div');

secondChild.style.width = '18px';
secondChild.style.height = '18px';
secondChild.style.backgroundImage = 'url(https://maps.gstatic.com/tactile/mylocation/mylocation-sprite-1x.png)';
secondChild.style.backgroundSize = '180px 18px';
secondChild.style.backgroundPosition = '0px 0px';
secondChild.style.backgroundRepeat = 'no-repeat';
secondChild.id = 'you_location_img';
firstChild.appendChild(secondChild);

controlDiv.index = 1;