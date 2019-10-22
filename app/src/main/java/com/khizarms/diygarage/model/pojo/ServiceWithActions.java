package com.khizarms.diygarage.model.pojo;

import androidx.room.Relation;
import com.khizarms.diygarage.model.entity.Action;
import com.khizarms.diygarage.model.entity.Service;
import java.util.List;

public class ServiceWithActions extends Service {

  @Relation(entity = Action.class, entityColumn = "service_id", parentColumn = "service_id")
  private List<Action> actions;

  public List<Action> getActions() {
    return actions;
  }

  public void setActions(List<Action> actions) {
    this.actions = actions;
  }
}
