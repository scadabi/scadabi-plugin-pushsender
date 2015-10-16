<!---
 license: Licensed to the Apache Software Foundation (ASF) under one
         or more contributor license agreements.  See the NOTICE file
         distributed with this work for additional information
         regarding copyright ownership.  The ASF licenses this file
         to you under the Apache License, Version 2.0 (the
         "License"); you may not use this file except in compliance
         with the License.  You may obtain a copy of the License at

           http://www.apache.org/licenses/LICENSE-2.0

         Unless required by applicable law or agreed to in writing,
         software distributed under the License is distributed on an
         "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
         KIND, either express or implied.  See the License for the
         specific language governing permissions and limitations
         under the License.
-->

# scadabi-plugin-pushsender

This plugin allows register, send and receive Push Notificarions for Android, IOS and Windows mobile devices in simple way, at the same time, SCADA BI offers the necessary Webservices in order to send notifications from other instances different to the mobile devices. This makes your solution in an integrated system.

## Supported Platforms

* Android 4.0.0 or above
* iOS 4.0.0 or above

## Plugin Installation
This requires phonegap/cordova CLI 5.0+ ( current stable v1.3.0 )
Now it is onlyo possible to install via repo url directly

    cordova plugin add https://github.com/albertoaboytia/scadabi-plugin-pushsender

or

    phonegap plugin add https://github.com/albertoaboytia/scadabi-plugin-pushsender


## Use the plugin into the project (Example)
To initialize the object PushNotification.

   var push = PushNotification.init({ "android": {"senderID": "12345679"},
            "ios": {"applicationid":"24181704688140304", "group-name":"broadcast", "alert": "true", "badge": "true", "sound": "true"}, "windows": {} } );

       push.on('registration', function(data) {
          // To receive your token: data.registrationId
       });


On Android, this equates to sending an intent of type BROWSEABLE.

This whitelist does not apply to plugins, only hyperlinks and calls to `window.open()`.

In `config.xml`, add `<allow-intent>` tags, like this:

    <!-- Allow links to web pages to open in a browser -->
    <allow-intent href="http://*/*" />
    <allow-intent href="https://*/*" />

    <!-- Allow links to example.com to open in a browser -->
    <allow-intent href="http://example.com/*" />

    <!-- Wildcards are allowed for the protocol, as a prefix
         to the host, or as a suffix to the path -->
    <allow-intent href="*://*.example.com/*" />

    <!-- Allow SMS links to open messaging app -->
    <allow-intent href="sms:*" />

    <!-- Allow tel: links to open the dialer -->
    <allow-intent href="tel:*" />

    <!-- Allow geo: links to open maps -->
    <allow-intent href="geo:*" />

    <!-- Allow all unrecognized URLs to open installed apps
         *NOT RECOMMENDED* -->
    <allow-intent href="*" />

## Network Request Whitelist
Controls which network requests (images, XHRs, etc) are allowed to be made (via cordova native hooks).

Note: We suggest you use a Content Security Policy (see below), which is more secure.  This whitelist is mostly historical for webviews which do not support CSP.

In `config.xml`, add `<access>` tags, like this:

    <!-- Allow images, xhrs, etc. to google.com -->
    <access origin="http://google.com" />
    <access origin="https://google.com" />

    <!-- Access to the subdomain maps.google.com -->
    <access origin="http://maps.google.com" />

    <!-- Access to all the subdomains on google.com -->
    <access origin="http://*.google.com" />

    <!-- Enable requests to content: URLs -->
    <access origin="content:///*" />

    <!-- Don't block any requests -->
    <access origin="*" />

Without any `<access>` tags, only requests to `file://` URLs are allowed. However, the default Cordova application includes `<access origin="*">` by default.

Quirk: Android also allows requests to https://ssl.gstatic.com/accessibility/javascript/android/ by default, since this is required for TalkBack to function properly.

### Content Security Policy
Controls which network requests (images, XHRs, etc) are allowed to be made (via webview directly).

On Android and iOS, the network request whitelist (see above) is not able to filter all types of requests (e.g. `<video>` & WebSockets are not blocked). So, in addition to the whitelist, you should use a [Content Security Policy](http://content-security-policy.com/) `<meta>` tag on all of your pages.

On Android, support for CSP within the system webview starts with KitKat (but is available on all versions using Crosswalk WebView).

Here are some example CSP declarations for your `.html` pages:

    <!-- Good default declaration:
        * gap: is required only on iOS (when using UIWebView) and is needed for JS->native communication
        * https://ssl.gstatic.com is required only on Android and is needed for TalkBack to function properly
        * Disables use of eval() and inline scripts in order to mitigate risk of XSS vulnerabilities. To change this:
            * Enable inline JS: add 'unsafe-inline' to default-src
            * Enable eval(): add 'unsafe-eval' to default-src
    -->
    <meta http-equiv="Content-Security-Policy" content="default-src 'self' data: gap: https://ssl.gstatic.com; style-src 'self' 'unsafe-inline'; media-src *">

    <!-- Allow requests to foo.com -->
    <meta http-equiv="Content-Security-Policy" content="default-src 'self' foo.com">

    <!-- Enable all requests, inline styles, and eval() -->
    <meta http-equiv="Content-Security-Policy" content="default-src *; style-src 'self' 'unsafe-inline'; script-src: 'self' 'unsafe-inline' 'unsafe-eval'">

    <!-- Allow XHRs via https only -->
    <meta http-equiv="Content-Security-Policy" content="default-src 'self' https:">

    <!-- Allow iframe to https://cordova.apache.org/ -->
    <meta http-equiv="Content-Security-Policy" content="default-src 'self'; frame-src 'self' https://cordova.apache.org">
