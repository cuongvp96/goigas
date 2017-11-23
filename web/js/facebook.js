//<script>
// This is called with the results from from FB.getLoginStatus().
function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    if (response.status === 'connected') {
        getProfile();
    } else {
        document.getElementById('status').innerHTML = 'Please log ' +
                'into this app.';
    }
}
function checkLoginState() {
    FB.getLoginStatus(function (response) {
        statusChangeCallback(response);
    });
}

window.fbAsyncInit = function () {
    FB.init({
        appId: '862806550562332',
        cookie: true, // enable cookies to allow the server to access 
        // the session
        xfbml: true, // parse social plugins on this page
        version: 'v2.8' // use graph api version 2.8
    });
    FB.getLoginStatus(function (response) {
        statusChangeCallback(response);
    });

};
(function (d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id))
        return;
    js = d.createElement(s);
    js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

function login() {
    FB.login(function (response) {
        if (response.authResponse) {
            // connected
            getProfile();
        } else {
            // cancelled
        }
    }, {scope: 'email,user_birthday,user_location,user_hometown'});
}
function getProfile() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', {fields: 'name,email,id'}, function (response) {
        console.log('Good to see you, ' + response.name + '.' + ' Email: ' + response.email + ' Facebook ID: ' + response.id);
        //console.log('Good to see you, ' + response.name + '.');

        var userName = response.name;


        document.getElementById('status').innerHTML =
                userfirstName + ' f:' + lastName + ' ' + useremail + ' ' + usersex + ' ' + userbithday;
        alert(response.name);
    });
}
//function testAPI() {
//    console.log('Welcome!  Fetching your information.... ');
//    FB.api('/me', {fields: 'name,email,id'}, function (response) {
//        console.log('Successful login for: ' + response.name);
//        document.getElementById('status').innerHTML =
//                'Thanks for logging in, ' + response.name + '!' + response.email + ' ' + response.id;
//    });
//}
function logout() {
    FB.logout('/me', {fields: 'name,email,id'}, function (response) {
        // user is now logged out
        document.location.reload();
        console.log('Successful logout for: ' + response.name);
        document.getElementById('status').innerHTML =
                'Thanks for logout in, ' + response.name + '!' + response.email + ' ' + response.id;
    });
}
function fbLogoutUser() {
    FB.getLoginStatus(function (response) {
        if (response && response.status === 'connected') {
            FB.logout(function (response) {
                document.location.reload();
            });
        }
    });
}
//    < /script>

