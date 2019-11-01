package com.khizarms.diygarage.model.pojo;

import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "menuItems")
public class ModelsRespnose {

  @ElementList(inline = true)
  private List<Model> models;

  public List<Model> getModels() {
    return models;
  }

  public void setModels(List<Model> models) {
    this.models = models;
  }
}
