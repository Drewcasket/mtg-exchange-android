package detroitlabs.mtgexchange.service;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ExchangeService {

    @GET("/filters")
    void getFilters(Callback<FiltersResponse> callback);

    @GET("/cards")
    void getCards(@Query("changeInPriceGreaterThan") Double changeInPriceGreaterThan,
                  @Query("changeInPriceLessThan") Double changeInPriceLessThan,
                  @Query("set") String set,
                  @Query("rarity") String rarity,
                  @Query("colors") String colors,
                  @Query("sortFields") String sortFields,
                  @Query("sortOrders") String sortOrders,
                  @Query("start") Long start,
                  @Query("limit") Long limit,
                  Callback<CardsResponse> callback);
}
