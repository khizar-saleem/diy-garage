package com.khizarms.diygarage.model.pojo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "menuItem")
public class Year {

  @Element(name = "value")
  private int value;

}
