package detroitlabs.mtgexchange.service;

import org.androidannotations.annotations.EBean;

import retrofit.Callback;
import retrofit.RestAdapter;

@EBean
public class ExchangeServiceClient {

    private final ExchangeService service;

    public ExchangeServiceClient() {

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://mtg-exchange.heroku.com/api").build();
        this.service = restAdapter.create(ExchangeService.class);
    }

    public void getFilters(Callback<FiltersResponse> callback) {
        this.service.getFilters(callback);
    }

    public void getCards(CardsParams params, Callback<CardsResponse> callback) {
        this.service.getCards(
                params.getChangeInPriceGreaterThan(),
                params.getChangeInPriceLessThan(),
                params.getSet(),
                params.getRarity(),
                ParamFormatter.formatList(params.getColors()),
                ParamFormatter.formatList(params.getSortFields()),
                ParamFormatter.formatList(params.getSortOrders()),
                params.getStart(),
                params.getLimit(),
                callback);
    }
}
