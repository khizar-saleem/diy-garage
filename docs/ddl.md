## Data Definition Language (DDL) for data model


```sql
CREATE TABLE IF NOT EXISTS `Action`
(
    `action_id`   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `summary`     TEXT,
    `description` TEXT,
    `service_id`  INTEGER                           NOT NULL,
    `serviceType` TEXT                              NOT NULL,
    FOREIGN KEY (`service_id`) REFERENCES `Service` (`service_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX `index_Action_service_id` ON `Action` (`service_id`);

CREATE TABLE IF NOT EXISTS `Service`
(
    `service_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `date`       INTEGER                           NOT NULL,
    `mileage`    INTEGER                           NOT NULL,
    `car_id`     INTEGER                           NOT NULL,
    FOREIGN KEY (`car_id`) REFERENCES `Car` (`car_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX `index_Service_date` ON `Service` (`date`);

CREATE INDEX `index_Service_car_id` ON `Service` (`car_id`);

CREATE TABLE IF NOT EXISTS `Car`
(
    `car_id`      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `make`        TEXT                              NOT NULL,
    `model`       TEXT                              NOT NULL,
    `year`        INTEGER                           NOT NULL,
    `acquisition` INTEGER                           NOT NULL
);

CREATE INDEX `index_Car_make` ON `Car` (`make`);

CREATE INDEX `index_Car_model` ON `Car` (`model`);

CREATE INDEX `index_Car_year` ON `Car` (`year`);

CREATE INDEX `index_Car_acquisition` ON `Car` (`acquisition`);

CREATE TABLE IF NOT EXISTS `AvailableCar`
(
    `available_car_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `make`             TEXT                              NOT NULL,
    `model`            TEXT                              NOT NULL,
    `year`             INTEGER                           NOT NULL
);

CREATE INDEX IF NOT EXISTS `index_AvailableCar_make` ON `AvailableCar` (`make`);

CREATE INDEX IF NOT EXISTS `index_AvailableCar_model` ON `AvailableCar` (`model`);

CREATE INDEX IF NOT EXISTS `index_AvailableCar_year` ON `AvailableCar` (`year`);
```

[`ddl.sql`](ddl.sql)