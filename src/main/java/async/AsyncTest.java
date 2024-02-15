package async;

import io.vavr.collection.List;
import io.vavr.*;
import io.vavr.control.Option;

import java.util.concurrent.CompletableFuture;

/**
 * You should complete the function in this class
 */
class AsyncTest {

  private static List<Enterprise> enterprises = List.of(
      new Enterprise("ent_1", "Google", "ceo_2"),
      new Enterprise("ent_2", "Facebook", "ceo_1")
  );

  private static List<Ceo> ceos = List.of(
      new Ceo("ceo_1", "Mark"),
      new Ceo("ceo_2", "Sundar"),
      new Ceo("ceo_3", "Bill")
  );

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

}
