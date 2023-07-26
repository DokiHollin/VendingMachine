package App;

import Card.GetCardInfo;
import GUI.MainPages.Default_page;
import GUI.Useful_functions.Music.Sound;
import Login.Login;
import Roles.Cashier;
import Roles.Customer;
import Roles.Roles;
import Roles.Seller;
import Snacks.Snacks;
import Store.Store;

import java.util.*;


public class App {

    public static Store store;
    public static Map<String, String> cardInfo = new HashMap<>();
    public static Login login;

    public static void main(String[] args) {
        Sound.playMusic();
        setup();


        Seller seller = new Seller("DokiHollin", "12345");
        Cashier cashier = new Cashier("XiaoqianHu", "12345");
        Customer customer = new Customer(100, 1, "XiaoqianHu", "12345");
        Default_page.default_page();
        GetCardInfo getCard = new GetCardInfo();
        getCard.parse("src/main/resources/credit_cards.json");

        System.out.println(cardInfo.toString());

    }

    public static void setup() {
        store = new Store();
        //System.out.println(store.store.get("Candies"));
        login = new Login("src/main/resources/Account");
        Roles.setupRoles("src/main/resources/Account");
//        store.addItem(new Builder.Director(new CandiesBuilder()).construct("Mantos", 1, 1, 1));
    }
}