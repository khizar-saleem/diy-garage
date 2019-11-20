/*
Copyright 2019 Khizar Saleem

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package com.khizarms.diygarage;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.khizarms.diygarage.service.DiyGarageDatabase;
import com.khizarms.diygarage.service.GoogleSignInService;

/**
 * Class containing main (non-UI) entry point for this app.
 */
public class DiyGarageApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    DiyGarageDatabase.setApplicationContext(this);
    DiyGarageDatabase database = DiyGarageDatabase.getInstance();
    new Thread(new Runnable() {
      @Override
      public void run() {
        database.getActionDao().delete();
      }
    }).start();
    GoogleSignInService.setApplicationContext(this);
  }

}
