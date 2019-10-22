package com.khizarms.diygarage;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.khizarms.diygarage.service.DiygarageDatabase;

public class DiyGarageApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
      DiygarageDatabase.setApplicationContext(this);
    final DiygarageDatabase database = DiygarageDatabase.getInstance();
    new Thread(new Runnable() {
      @Override
      public void run() {
        database.getActionDao().delete();
      }
    }).start();
  }

}
