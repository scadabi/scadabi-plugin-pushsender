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

To catch any error in every notification process

```javascript
   push.on('error', function(e) {
         // e.message
   });
```

| Callback Parameter  | Description |
| ------------- | ------------- |
| e.message  | This variable contains the value returned for any issue occurred on the notification process  |

