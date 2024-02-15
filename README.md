# Java Interview Test Preparation

# System Setup:
Before running the tests, please ensure you are using the most current versions of Maven and SBT. The SBT version specified in the provided build files is outdated (1.5.5), and it's recommended to upgrade to the latest version, 1.9.8. The build.properties file has been updated accordingly to reflect this change:

  ```bash
    sbt.version=1.9.8
```

Additionally, the original build.sbt file listed an obsolete scalaVersion. It has been updated to a more recent version for compatibility and performance improvements:

  ```bash
    ThisBuild / scalaVersion     := "2.13.12"
```

# Managing Dependencies:
To incorporate the Vavr library into our project, Maven is used to handle the necessary dependencies. This step involves creating a pom.xml file with the following configuration:

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

# Code Modifications :
In order to complete the assignement i modified the the codes provided and i tested and my code passed all the tests.
you can find the code the repository.

# 1. Async Modifications :
   
The AsyncTest class required updates to fulfill its intended functionality. The modifications include implementing asynchronous operations for retrieving CEO and Enterprise details:

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

Enhancements were also applied to the Ceo and Enterprise classes to introduce functions that facilitate accessing class members and other functionalities:

# 1`. Ceo Class Enhancements: 

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

# 2`.Enterprise Class Enhancements: 


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

# 2.Modification on BasicTest : 

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
# 3. Modification on CollectionTest :
   
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
# Testing results:
As we can see that our code has passed all the tests:

![image](https://github.com/SalahZITOUNI/java-interview-particeep/assets/157633302/e6468b76-23e8-4500-a338-b0686c38ad43)



# Repository Cloning : 
To clone the repository for accessing the modified code, use the following command:

```bash
git clone https://github.com/SalahZITOUNI/java-interview-particeep.git
```

# Running the Code :
Ensure your environment is correctly set up prior to executing the code. The following commands will guide you through cleaning, updating, compiling, and testing the project:

```
cd /some_path/java-interview
sbt clean update compile
sbt test
```

# Troubleshooting :
Should you encounter any issues with SBT, executing the following command may resolve them:

```bash
sbt clean
```
