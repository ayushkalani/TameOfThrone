package solution;

public enum Kingdoms {
  LAND("panda"), WATER("octopus"), SPACE("gorilla"), AIR("owl"), FIRE("dragon"), ICE("mammoth");

  private String emblem;

  Kingdoms(String emblem) {
    this.emblem = emblem;
  }

  public String emblem() {
    return emblem;
  }
}
