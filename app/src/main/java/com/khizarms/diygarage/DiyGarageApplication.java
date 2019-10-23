package com.khizarms.diygarage;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.khizarms.diygarage.service.DiyGarageDatabase;

public class DiyGarageApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
      DiyGarageDatabase.setApplicationContext(this);
    final DiyGarageDatabase database = DiyGarageDatabase.getInstance();
    new Thread(new Runnable() {
      @Override
      public void run() {
        database.getActionDao().delete();
      }
    }).start();
  }

}