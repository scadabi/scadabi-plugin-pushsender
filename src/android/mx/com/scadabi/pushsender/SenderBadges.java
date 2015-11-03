/*
 * Copyright (C) 2014 Arturo Gutiérrez Díaz-Guerra.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mx.com.scadabi.pushsender;
import android.content.Context;
import mx.com.scadabi.pushsender.providers.BadgeProvider;
import mx.com.scadabi.pushsender.providers.BadgeProviderFactory;

/**
 * Object to ser badge value on current app icon.
 */
public class SenderBadges {

    public static void setBadge(Context context, int count) throws BadgesNotSupportedException {
        if (context == null) {
            throw new BadgesNotSupportedException();
        }
		
        BadgeProviderFactory badgeFactory = new BadgeProviderFactory(context);
        BadgeProvider badgeProvider = badgeFactory.getBadgeProvider();
        try {
            badgeProvider.setBadge(count);
        } catch (UnsupportedOperationException exception) {
            throw new BadgesNotSupportedException();
        }
    }

    public static void removeBadge(Context context) throws BadgesNotSupportedException {
        SenderBadges.setBadge(context, 0);
    }
}
