
window.fbAsyncInit = function () {
    FB.init({
        appId: '862806550562332',
        cookie: true,
        xfbml: true,
        version: 'v2.8'
    });
    FB.AppEvents.logPageView();
};


(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = 'https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.10&appId=862806550562332';
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

FB.getLoginStatus(function (response) {
    statusChangeCallback(response);
});
function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    if (response.status === 'connected') {
//                    testAPI();
        FB.api('/me', {fields: 'name,email,id'}, function (response) {
            console.log('Successful login for: ' + response.name);
            var iduserfb = response.id;
            document.getElementById('idFb').value = iduserfb;
            document.getElementById("fb_form").submit();
        });
    } else {
//        document.getElementById('status').innerHTML = 'Please log ' +
//                'into this app.';
    }
}
function checkLoginState() {
    FB.getLoginStatus(function (response) {
        statusChangeCallback(response);
    });
}
function fbLogoutUser() {
//    document.getElementById('status1').innerHTML = 'ok2';

    FB.getLoginStatus(function (response) {
        if (response && response.status === 'connected') {
            FB.logout(function (response) {
                console.log('Successful logout for: ' + response.name);
//                document.getElementById('status1').innerHTML = 'logout';
                document.location.reload();

            });
        }
    });
}
function checkLogin() {
    FB.getLoginStatus(function (response) {
        if (response.status === 'connected') {
//                    testAPI();
            FB.api('/me', {fields: 'name,email,id'}, function (response) {
                console.log('Successful login for: ' + response.name);
                var iduserfb = response.id;
                document.getElementById('idFb').value = iduserfb;

            });
        }
    });
}