package com.khizarms.diygarage.service;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import com.khizarms.diygarage.model.dao.ActionDao;
import com.khizarms.diygarage.model.entity.Action;
import com.khizarms.diygarage.model.entity.Action.ServiceType;
import com.khizarms.diygarage.model.entity.Car;
import com.khizarms.diygarage.model.entity.Service;
import java.util.Date;

@Database(
    entities = {Action.class, Service.class, Car.class},
    version = 3, exportSchema = true
)
@TypeConverters(DiygarageDatabase.Converters.class)
public abstract class DiygarageDatabase extends RoomDatabase {

  protected DiygarageDatabase() {}

  private static Application applicationContext;

  public static void setApplicationContext(Application applicationContext) {
    DiygarageDatabase.applicationContext = applicationContext;
  }

  public static DiygarageDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public abstract ActionDao getActionDao();

  private static class InstanceHolder {

    private static final DiygarageDatabase INSTANCE;

    static {
      INSTANCE =
          Room.databaseBuilder(applicationContext, DiygarageDatabase.class, "diygarage_db").build();

    }
  }

  public static class Converters {

    @TypeConverter
    public Long dateToLong(Date date) {
      return (date != null) ? date.getTime() : null;
    }

    @TypeConverter
    public Date longToDate(Long milliseconds) {
      return (milliseconds != null) ? new Date(milliseconds) : null;
    }

    @TypeConverter
    public String enumToString(Enum value) {
      return (value != null) ? value.toString() : null;
    }

    @TypeConverter
    public ServiceType stringToRank(String name) {
      return (name != null) ? ServiceType.valueOf(name) : null;
    }

  }

}
