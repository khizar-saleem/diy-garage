package com.khizarms.diygarage.service;

import android.app.Application;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.khizarms.diygarage.model.dao.ActionDao;
import com.khizarms.diygarage.model.dao.AvailableCarDao;
import com.khizarms.diygarage.model.dao.CarDao;
import com.khizarms.diygarage.model.dao.ServiceDao;
import com.khizarms.diygarage.model.entity.Action;
import com.khizarms.diygarage.model.entity.Action.ServiceType;
import com.khizarms.diygarage.model.entity.AvailableCar;
import com.khizarms.diygarage.model.entity.Car;
import com.khizarms.diygarage.model.entity.Service;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

@Database(
    entities = {Action.class, Service.class, Car.class, AvailableCar.class},
    version = 3, exportSchema = true
)
@TypeConverters(DiyGarageDatabase.Converters.class)
public abstract class DiyGarageDatabase extends RoomDatabase {

  protected DiyGarageDatabase() {}

  private static Application applicationContext;

  public static void setApplicationContext(Application applicationContext) {
    DiyGarageDatabase.applicationContext = applicationContext;
  }

  public static DiyGarageDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public abstract CarDao getCarDao();

  public abstract ServiceDao getServiceDao();

  public abstract ActionDao getActionDao();

  public abstract AvailableCarDao getAvailableCarDao();

  private static class InstanceHolder {

    private static final DiyGarageDatabase INSTANCE;

    static {
      INSTANCE =
          Room.databaseBuilder(applicationContext, DiyGarageDatabase.class, "diygarage_db")
              .addCallback(new PreloadCallback())
              .build();

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

  private static class PreloadCallback extends Callback {

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      new Thread(() -> {

        Resources res = applicationContext.getResources();
        int id = res.getIdentifier("vehicles_edited", "raw", applicationContext.getPackageName());
        try (
            InputStream in = res.openRawResource(id);
            CSVParser parser = CSVParser.parse(in, Charset.defaultCharset(), CSVFormat.EXCEL
                .withHeader("cylinders","displ","drive","make","model","trany","year")
                .withSkipHeaderRecord())
        ) {
          List<AvailableCar> cars = new LinkedList<>();
          for (CSVRecord record : parser) {
            AvailableCar car = new AvailableCar();
            car.setMake(record.get("make"));
            car.setModel(record.get("model"));
            car.setYear(Integer.parseInt(record.get("year")));
            cars.add(car);
          }
          DiyGarageDatabase.getInstance().getAvailableCarDao().insert(cars);
        } catch (Exception e) {
          Log.d("preload", e.getMessage(), e);
        }

      }).start();
    }
  }
}
