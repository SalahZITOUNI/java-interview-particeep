package async;

class Ceo {
  String id;
  String name;


  public Ceo(String id, String name) {
    this.id = id;
    this.name = name;
  }
  public String toString() {
    return "Ceo_" + name;
  }
    // getId method
    public String getId() {
      return this.id;
  }
  // Additional getter for name, if needed
  public String getName() {
      return this.name;
  }
}
