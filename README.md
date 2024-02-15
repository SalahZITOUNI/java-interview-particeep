# Test in java for interview

# System preperation:
To run the test make assure that you are using the latest version of maven and sbt.
the provided version of sbt in the building files dosn't work becouse it is using the version 1.5.5.
currently the latest version is 1.9.8.
I have modified the version the build.properties file to 1.9.8

  ```bash
    sbt.version=1.9.8
```

The original file contain an old version of scalaVersion in the file build.sbt , so  i  needed to update it to use a higher version:

  ```bash
    ThisBuild / scalaVersion     := "2.13.12"
```

# Dependancies
To run the librery vavr i need to use maven to download and add the dependancies so i created the file pom.xml :

  ```bash
    <project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>your.group.id</groupId>
    <artifactId>your-artifact-id</artifactId>
    <version>1.0-SNAPSHOT</version>
    <dependencies>
        <dependency>
            <groupId>io.vavr</groupId>
            <artifactId>vavr</artifactId>
            <version>0.10.3</version> <!-- Check for the latest version -->
        </dependency>
    </dependencies>
</project>
```

# Modification of the code :
In order to complete the assignement i modified the the codes provided and i tested and my code passed all the tests.
you can find the code the repository.

1. ** Modification on async : **
In order to realise the fonction of the class AsyncTest , we made the following modification on the original code :

```bash
  public static CompletableFuture<Option<Ceo>> getCeoById(String ceoId) {
    return CompletableFuture.supplyAsync(() -> 
        ceos.find(ceo -> ceo.getId().equals(ceoId))
    );
}

public static CompletableFuture<Option<Enterprise>> getEnterpriseByCeoId(String ceoId) {
  return CompletableFuture.supplyAsync(() -> 
      enterprises.find(enterprise -> enterprise.getCeoId().equals(ceoId))
  );
}

public static CompletableFuture<Tuple2<Option<Ceo>, Option<Enterprise>>> getCEOAndEnterprise(String ceoId) {
  return getCeoById(ceoId).thenCombine(getEnterpriseByCeoId(ceoId), Tuple::of);
}
```

we also made modifications on the Ceo class and the Enterprise class by adding the necessry fonction whish will allow the class to return the member of to other classes and other fonctons.

1`. ** Ceo class : **

  ```bash
        // getId method
    public String getId() {
      return this.id;
  }
  // Additional getter for name, if needed
  public String getName() {
      return this.name;
  }
```

2`. ** Enterprise class : **

  ```bash
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
```

2. ** Modification on BasicTest : **
In order to realise the fonction of the class BasicTest , we made the following modification on the original code :

```bash
  public static Option<Integer> power(Integer i, Integer n) {
      // Check for null inputs, negative exponent, or potential overflow conditions
      if (i == null || n == null || n < 0 || (i != 0 && i != 1 && n > 30)) { // Example overflow condition
          return Option.none();
      }

      if (n == 0) {
          return Option.of(1); // Any number to the power of 0 is 1
      }
      if (i == 0) {
          return Option.of(0); // 0 to any positive power is 0
      }
      if (i == 1) {
          return Option.of(1); // 1 to any power is 1
      }

      long result = 1; // Use long to check for overflow
      for (int count = 0; count < n; count++) {
          result *= i;
          if (result > Integer.MAX_VALUE) {
              return Option.none(); // Return None in case of overflow
          }
      }
      return Option.of((int) result); // Safe cast since we checked for overflow
  }
```
3. ** Modification on CollectionTest : **
In order to realise the fonction of the class CollectionTest , we made the following modification on the original code :

```bash
 public static List<Double> compute1(List<Integer> input) {
    return input.stream()
            .map(x -> Math.pow((x * 2) + 3, 5))
            .collect(Collectors.toList());
  }
  /**
   * Alternative implementation for compute2 with a slight variation in style.
   */
  public static List<String> compute2(List<String> input) {
    return input.stream()
            .map(s -> {
              if (s.isEmpty()) return s;
              String processed = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
              return processed + processed;
            })
            .collect(Collectors.toList());
  }
```

# clone the repository : 
To clone the repository use the following code:

```bash
git clone https://github.com/SalahZITOUNI/java-interview-particeep.git
```

# How to run the code :
After you assure the your computer is ready :

```
cd /some_path/java-interview
sbt clean update compile
sbt test
```

# Petentiol problemes :
you may have problemes with sbt you can try to clean sbt:

```bash
sbt clean
```
