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
package com.khizarms.diygarage.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.io.Serializable;

/**
 * Entity class encapsulating the basic properties of an action.
 */
@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = Service.class,
            childColumns = "service_id",
            parentColumns = "service_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class Action implements Serializable {

  private static final long serialVersionUID = -8522256879590658546L;

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "action_id")
  private long id;

  @ColumnInfo(name = "service_id", index = true)
  private long serviceId;

  private String summary;

  private String description;

  @NonNull
  private ServiceType serviceType;

  /**
   * Gets id.
   *
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets summary.
   *
   * @return the summary
   */
  public String getSummary() {
    return summary;
  }

  /**
   * Sets summary.
   *
   * @param summary the summary
   */
  public void setSummary(String summary) {
    this.summary = summary;
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets description.
   *
   * @param description the description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets service id.
   *
   * @return the service id
   */
  public long getServiceId() {
    return serviceId;
  }

  /**
   * Sets service id.
   *
   * @param serviceId the service id
   */
  public void setServiceId(long serviceId) {
    this.serviceId = serviceId;
  }

  /**
   * Gets service type.
   *
   * @return the service type
   */
  @NonNull
  public ServiceType getServiceType() {
    return serviceType;
  }

  /**
   * Sets service type.
   *
   * @param serviceType the service type
   */
  public void setServiceType(@NonNull ServiceType serviceType) {
    this.serviceType = serviceType;
  }

  /**
   * The enum Service type.
   */
  public enum ServiceType {

    /**
     * Motor service type.
     */
    MOTOR,
    /**
     * Transmission service type.
     */
    TRANSMISSION,
    /**
     * Cosmetic service type.
     */
    COSMETIC,

  }
}
