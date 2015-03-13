package detroitlabs.mtgexchange.service;

import java.util.List;

public class CardsParams {

    private Double changeInPriceGreaterThan;
    private Double changeInPriceLessThan;
    private String set;
    private String rarity;
    private List<String> colors;
    private List<String> sortFields;
    private List<String> sortOrders;
    private Long start;
    private Long limit;

    public Double getChangeInPriceGreaterThan() {
        return changeInPriceGreaterThan;
    }

    public void setChangeInPriceGreaterThan(Double changeInPriceGreaterThan) {
        this.changeInPriceGreaterThan = changeInPriceGreaterThan;
    }

    public Double getChangeInPriceLessThan() {
        return changeInPriceLessThan;
    }

    public void setChangeInPriceLessThan(Double changeInPriceLessThan) {
        this.changeInPriceLessThan = changeInPriceLessThan;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public List<String> getSortFields() {
        return sortFields;
    }

    public void setSortFields(List<String> sortFields) {
        this.sortFields = sortFields;
    }

    public List<String> getSortOrders() {
        return sortOrders;
    }

    public void setSortOrders(List<String> sortOrders) {
        this.sortOrders = sortOrders;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }
}
