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
This requires phonegap/cordova CLI 5.0+ ( current stable v1.3.0 ).

Now it is only possible to install via repo url directly

    cordova plugin add https://github.com/albertoaboytia/scadabi-plugin-pushsender

or

    phonegap plugin add https://github.com/albertoaboytia/scadabi-plugin-pushsender


## Use the plugin into the project (Example)
To initialize the object PushNotification.
```javascript
   var push = PushNotification.init({ 
         "android": {"senderID": "project number", "SenderApplication": "here your appid", "SenderGroup": "here your group"},
         "ios": {"applicationid":"here your appid", "group-name":"here your group", "alert": "true", "badge": "true", "sound": "true"}, 
         "windows": {} } 
   );
```
##### Parameters for Android

| Parameter  | Description |
| ------------- | ------------- |
| senderID  | Number of the project created on Google Developer Console  |
| SenderApplication  | Number of the application created on SCADA BI PushSender  |
| SenderGroup  | You can make differents groups for device, on this parameter you define which group will be for the current device  |

##### Parameters for IOS

| Parameter  | Description |
| ------------- | ------------- |
| applicationid  | Number of the application created on SCADA BI PushSender  |
| group-name  | You can make differents groups for device, on this parameter you define which group will be for the current device  |

To receive your device ID notification.

```javascript
   push.on('registration', function(data) {
         // data.registrationId
   });
```

| Callback Parameter  | Description |
| ------------- | ------------- |
| data.registrationId  | This variable contains the value returned for SCADA BI PushSender  |

To receive notification

```javascript
   push.on('notification', function(data) {
         // data.message,
         // data.title,
         // data.count,
         // data.sound,
         // data.image,
         // data.additionalData
         // data.groupName
         // data.messageInformation
   });
```

| Callback Parameter  | Description |
| ------------- | ------------- |
| data.message  | This variable contains the message of the received notification  |
| data.title  | This variable contains the title of the received notification  |
| data.count  | This variable contains the number of the received notification  |
| data.sound  | This variable contains the sound of the received notification  |
| data.image  | This variable contains the image of the received notification  |
| data.additionalData  | This variable contains the addiotional data of the received notification  |
| data.groupName  | This variable contains the group name if it was used in the received notification. If it was not used, you are going to receive the text "No data"  |
| data.messageInformation  | This variable contains additional information that you want to receive from the application server.  |

To catch any error in every notification process

```javascript
   push.on('error', function(e) {
         // e.message
   });
```

| Callback Parameter  | Description |
| ------------- | ------------- |
| e.message  | This variable contains the value returned for any issue occurred on the notification process  |


## Web Service to send notifications
Through any language, connect to the next server

    https://mobile.scadabi.com.mx/cloud/messaging/

Send infomation through POST method.

    HTTP Method: POST

Send JSON parameters.

##### Parameters for single notification

```javascript
 {	
	  "action":"send-push-group-devices", //Do not change this parameter
	  "appid":"here your project number SCADA BI",
	  "group-name":"Group name of receiver devices",
	  "message":"Text for notification",
	  "title":"Title for notification",
	  
	  "badge":"3",
	  "message-information":"additional information to send to devices"
 }
```

Android

![2015-11-03 07 42 00](http://blog.azoft.com/images/2014/06/Basic.png)

##### Parameters for notification with Application Image

The parameters needs to be the same of single notification and add the news. For example.

```javascript
 {	
	  "action":"send-push-group-devices", //Do not change this parameter
	  "appid":"here your project number SCADA BI",
	  "group-name":"Group name of receiver devices",
	  "message":"Text for notification",
	  "title":"Title for notification",
	  
	  "image-app":"URL of image",
	  
	  "badge":"3",
	  "message-information":"additional information to send to devices"
 }
```
Android

![2015-11-03 07 42 00](http://i.stack.imgur.com/pk6Mc.png)

##### Parameters for notification with Picture style

The parameters needs to be the same of single notification and add the news. For example.

```javascript
 {	
	  "action":"send-push-group-devices", //Do not change this parameter
	  "appid":"here your project number SCADA BI",
	  "group-name":"Group name of receiver devices",
	  "message":"Text for notification",
	  "title":"Title for notification",
	  
	  "push-style":"picture", //Do not change this parameter
	  "push-picture":"URL of picture",
	  
	  "badge":"3",
	  "message-information":"additional information to send to devices"
 }
```

##### Description for each parameter to send notifications.

| Parameter  | Description |
| ------------- | ------------- |
| action  | It is to identify the type of parameter to send. To send notification to a group, you need to use 'send-push-group-devices'  |
| appid  | Number of the application created on SCADA BI PushSender  |
| group-name  | Name of group that devices were registered  |
| message  | Text to display in the notification  |
| title  | Title to display in the notification  |
| image-app  | URL for image to display in application icon  |
| push-style  | Identify the style for notification, for picture it needs to be 'picture'  |
| push-picture  | URL fot picture to display in notification  |
| message-information  | You can use this parameter in order to send information to devices, this is used to send additional information  |
| badge  | You can use this parameter to send the number of pending notifications to user, THIS PARAMETER NEEDS TO BE INTEGER  |
| sound-file | Name of the file without extension, for example the file is: example.wav, the value is going to be example  |

Android

All the message that the plugin receive are with the max priority, for that reason all message appear in front of whatever application that you have open.

 ![2015-11-03 07 42 00](http://core0.staticworld.net/images/article/2015/06/lollipop-heads-up-notifications-100591800-large.idge.jpg)

## Pushsender Badges

This plugin also support Badges on Android and IOS. This function gives you additional information in your application, you can watch the number of pending notifications on the left top corner of the app icon.

## Supported Platforms

The badges only support the next launchers.
* Android 5.0.0 or above
  * Samsung devices.
  * Sony devices.
  * HTC devices.
  * LG devices.
* iOS 4.0.0 or above
  * All devices from Apple.

## Android devices

On Android devices the implementation looks like the next image.

![2015-11-03 07 42 00](http://i.stack.imgur.com/JCULE.png)

## IOS devices

On IOS devices the implementation looks like the next image.

![2015-11-03 07 42 00](http://media.idownloadblog.com/wp-content/uploads/2014/02/ClassicBadges-Dock.png)

## Custom Sound Implementation

In order to assign custom sounds in your proyect you have to include the parameter "sound-file" in JSON code. The next javascript code
shows an example that you have to use in your server code.

```javascript
 {	
	  "sound-file":"name of the file without extension, for example the file is: example.wav, the value is going to be example"
 }
```

In the Mobile proyect side you have to attach your file. It is depending of the operative system.

### Android Devices

In order to attach you file in the project you need to copy the file sound in the location "res/raw". The example is showing in the next image. Note: if the folder "raw" does not exist you have to create it.

![2015-12-14 10 30 00](http://mobile.scadabi.com.mx/cloud/messaging/img/androidsoundexample.png)

### IOS Devices

For remote notifications in iOS, you can specify a custom sound that iOS plays when it presents a local or remote notification for an app. The sound files can be in the main bundle (A bundle is a directory in the file system that groups executable code and related resources such as images and sounds together in one place.) of the client app or in the Library/Sounds folder of the app’s data container.

Custom alert sounds are played by the iOS system-sound facility, so they must be in one of the following audio data formats:

* Linear PCM
* MA4 (IMA/ADPCM)
* µLaw
* aLaw

You can package the audio data in an aiff, wav, or caf file. Then, in Xcode, add the sound file to your project as a nonlocalized resource of the app bundle or to the Library/Sounds folder of your data container.

You can use the afconvert tool to convert sounds. For example, to convert the 16-bit linear PCM system sound Submarine.aiff to IMA4 audio in a CAF file, use the following command in the Terminal app:

    afconvert /System/Library/Sounds/Submarine.aiff ~/Desktop/sub.caf -d ima4 -f caff -v

You can inspect a sound to determine its data format by opening it in QuickTime Player and choosing Show Movie Inspector from the Movie menu.

Custom sounds must be under 30 seconds when played. If a custom sound is over that limit, the default system sound is played instead.

## UML Documentation

The use case diagram for UML represents the form that the plugin works with devices and webservices.

![2015-11-12 11 33 00](http://mobile.scadabi.com.mx/cloud/messaging/img/pushsender-use-case-uml.png)
