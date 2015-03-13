package detroitlabs.mtgexchange.service;

import java.util.List;

import detroitlabs.mtgexchange.Card;

public class CardsResponse extends CardsParams {

    private List<Card> cards;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
