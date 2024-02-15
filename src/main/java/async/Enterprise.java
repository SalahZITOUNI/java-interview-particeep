package async;

class Enterprise {
  String id;
  String name;
  String ceo_id;

  public Enterprise(String id, String name, String ceo_id) {
    this.id = id;
    this.name = name;
    this.ceo_id = ceo_id;
  }
  public String toString() {
    return "Enterprise_" + name;
  }
  // Getter for the CEO ID
  public String getCeoId() {
    return this.ceo_id; // Ensure this matches the variable declaration
  }
  // Additional getters for id and name
  public String getId() {
      return this.id;
  }
  public String getName() {
      return this.name;
  }
}
