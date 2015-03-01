package com.intuit.elevator.control;

public enum DirectionEnum {
	UP("up"),
	DOWN("down");
	  private String value;
	   private DirectionEnum(String value) {
	      this.value = value;
	   }
	   public String getValue() {
	      return value;
	   }
}
