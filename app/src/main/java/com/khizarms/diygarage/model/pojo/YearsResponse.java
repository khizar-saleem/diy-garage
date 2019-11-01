package com.khizarms.diygarage.model.pojo;

import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "menuItems")
public class YearsResponse {

  @ElementList(inline = true)
  private List<Year> years;

  public List<Year> getYears() {
    return years;
  }

  public void setYears(List<Year> years) {
    this.years = years;
  }

}
