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
package com.khizarms.diygarage.service;

import android.app.Application;
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
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * The type Diy garage database.
 */
@Database(
    entities = {Action.class, Service.class, Car.class, AvailableCar.class},
    version = 3, exportSchema = true
)
@TypeConverters(DiyGarageDatabase.Converters.class)
public abstract class DiyGarageDatabase extends RoomDatabase {

  /**
   * Instantiates a new Diy garage database.
   */
  protected DiyGarageDatabase() {}

  private static Application applicationContext;

  /**
   * Sets application context.
   *
   * @param applicationContext the application context
   */
  public static void setApplicationContext(Application applicationContext) {
    DiyGarageDatabase.applicationContext = applicationContext;
  }

  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static DiyGarageDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Gets car dao.
   *
   * @return the car dao
   */
  public abstract CarDao getCarDao();

  /**
   * Gets service dao.
   *
   * @return the service dao
   */
  public abstract ServiceDao getServiceDao();

  /**
   * Gets action dao.
   *
   * @return the action dao
   */
  public abstract ActionDao getActionDao();

  /**
   * Gets available car dao.
   *
   * @return the available car dao
   */
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

  /**
   * The type Converters.
   */
  public static class Converters {

    /**
     * Date to long long.
     *
     * @param date the date
     * @return the long
     */
    @TypeConverter
    public Long dateToLong(Date date) {
      return (date != null) ? date.getTime() : null;
    }

    /**
     * Long to date date.
     *
     * @param milliseconds the milliseconds
     * @return the date
     */
    @TypeConverter
    public Date longToDate(Long milliseconds) {
      return (milliseconds != null) ? new Date(milliseconds) : null;
    }

    /**
     * Enum to string string.
     *
     * @param value the value
     * @return the string
     */
    @TypeConverter
    public String enumToString(Enum value) {
      return (value != null) ? value.toString() : null;
    }

    /**
     * String to rank service type.
     *
     * @param name the name
     * @return the service type
     */
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
