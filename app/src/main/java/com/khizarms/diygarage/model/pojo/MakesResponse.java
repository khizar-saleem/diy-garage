package com.khizarms.diygarage.model.pojo;

import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "menuItems")
public class MakesResponse {

  @ElementList(inline = true)
  private List<Make> makes;

  public List<Make> getMakes() {
    return makes;
  }

  public void setMakes(List<Make> makes) {
    this.makes = makes;
  }
}
