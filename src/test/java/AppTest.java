import App.App;
import Builder.Director;
import Builder.SnacksBuilder;
import Card.GetCardInfo;
import GUI.Add_roles;
import Login.Login;
import RecentFiveItems.Items;
import Roles.Cashier;
import Roles.Customer;
import Roles.Roles;
import Roles.Seller;
import  Roles.Owner;
import ShoppingCart.ShoppingCart;
import Snacks.Snacks;
import Store.Store;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SNIServerName;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class AppTest {
    public static Store store;

    @Test
    public void testBuilder() {
        SnacksBuilder sb = new SnacksBuilder();
        Director d = new Director(sb);
        Snacks s = d.construct("Smiths", 5, 5, 1, "Chips");

        assertEquals("Smiths", s.getName());
        assertEquals(5, s.getPrice());
        assertEquals(5, s.getAmount());
        assertEquals(1, s.getId());
        assertEquals("Chips", s.getType());
        assertThrows(IndexOutOfBoundsException.class, ()-> {

        });
    }


    /*   Testing of customer and shoppingCart */

    @Test
    public void TestAddToShoppingCart() {

        Customer c = new Customer(100, 1, "zechao", "sunzechao2000");

        Snacks sn = new Snacks("cola", 5, 10, 1, "drinks");
        // test normal
        int real = c.addToShoppingCart(sn, 5);
        int real4 = c.getShoppingCart().get_amount("cola");
        assertEquals(5,real4);

        int real7 = c.getShoppingCart().get_amount("cc");
        assertEquals(0,real7);
        boolean real5 = c.getShoppingCart().hasName("cola");
        assertTrue(real5);

        boolean real8 = c.getShoppingCart().isEmpty();
        assertFalse(real8);
        boolean real6 = c.getShoppingCart().hasName("ccc");
        assertFalse(real6);
        assertEquals(0, real);

        Snacks real_key = null;
        int real_value = 0;
        for (Map.Entry<Snacks, Integer> m : c.getShoppingCart().getShoppingCart().entrySet()) {
            real_key = m.getKey();
            real_value = m.getValue();
        }
        // case shoppingCart do not have this snack
        assertEquals(sn, real_key); // test object
        assertEquals(5, real_value); // test amount

        // case shoppingCart already have this snack and update amount

        c.addToShoppingCart(sn, 3);
        for (Map.Entry<Snacks, Integer> m : c.getShoppingCart().getShoppingCart().entrySet()) {
            real_key = m.getKey();
            real_value = m.getValue();
        }

        assertEquals(sn, real_key); // test object
        assertEquals(8, real_value); // test amount

        //edge case
        // test input amount <= 0
        int real1 = c.addToShoppingCart(sn, -2);
        int real2 = c.addToShoppingCart(sn, 0);
        assertEquals(1, real1);
        assertEquals(1, real2);

        // test input amount > amount of snack

        int real3 = c.addToShoppingCart(sn, 11);
        assertEquals(2, real3);
        c.getShoppingCart().remove(sn);

    }

    @Test
    public void testRemoveFromSC() {
        Customer c = new Customer(100, 1, "zechao", "sunzechao2000");
        Snacks sn = new Snacks("cola", 5, 10, 1, "drinks");
        c.addToShoppingCart(sn, 9);
        //test snack does not exist in shoppingCart
        Snacks test_sn = new Snacks("lemon juice", 5, 10, 1, "drinks");
        int real = c.removeFromShoppingCart(test_sn, 5);
        assertEquals(-2, real);

        //test input amount <= 0
        int real1 = c.removeFromShoppingCart(sn, 0);
        int real2 = c.removeFromShoppingCart(sn, -1);
        assertEquals(real1, -1);
        assertEquals(real2, -1);


        // test input amount > snack's amount
        int real3 = c.removeFromShoppingCart(sn, 10);
        assertEquals(-3, real3);

        //test remove someS

        c.removeFromShoppingCart(sn, 3);
        Snacks real_key = null;
        int real_value = 0;
        for (Map.Entry<Snacks, Integer> m : c.getShoppingCart().getShoppingCart().entrySet()) {
            real_key = m.getKey();
            real_value = m.getValue();
        }
        assertEquals(sn, real_key);
        assertEquals(6, real_value);

        //test remove all of this snack
        c.removeFromShoppingCart(sn, 6);
        assertFalse(c.getShoppingCart().containsKey(sn));
    }

    @Test
    public void TestClearShoppingCart() {

        Customer c = new Customer(100, 1, "zechao", "sunzechao2000");
        Snacks sn = new Snacks("cola", 5, 10, 1, "drinks");
        Snacks sn1 = new Snacks("fake cola", 5, 10, 1, "drinks");
        c.addToShoppingCart(sn, 9);
        c.addToShoppingCart(sn1, 9);
        c.clearShoppingCart();
        assertFalse(c.getShoppingCart().containsKey(sn));
        assertFalse(c.getShoppingCart().containsKey(sn1));
    }

    @Test
    public void TestCheckout() {
        Customer c = new Customer(100, 1, "zechao", "sunzechao2000");
        Snacks sn = new Snacks("cola", 5, 10, 1, "drinks");
        Snacks sn1 = new Snacks("fake cola", 5, 10, 1, "drinks");
        c.addToShoppingCart(sn, 9);
        c.addToShoppingCart(sn1, 9);
    }


    /* Test Seller */


    @Test
    public void TestFillItem() {
        App.setup();
        Seller s = new Seller("zechao", "sunzechao2000");
        // edge case amount > 15
        Snacks sn = new Snacks("cola", 5, 16, 1, "drinks");
        int real = s.fillItem(sn);
        assertEquals(1, real);
    }
//    @Test
//    public void TestModifyItem(){
//
//
//        Seller s = new Seller("zechao","sunzechao2000");
//        // edge case amount > 15
//        Snacks sn = new Snacks("cola",5,10,1,"drinks");
//        App.store.addItem(sn);
//        assertTrue(App.store.get_items(sn.getType()).contains(sn));
//    }


    @Test
    public void TestGetSnackType() {
        Snacks sn = new Snacks("cola", 5, 10, 1, "drinks");
        assertEquals("drinks", sn.getType());
    }

    @Test

    public void TestConfigList() {
        store = new Store();
        store.configList("src/main/resources/Test_Candies", "src/main/resources/Test_Chips"
                , "src/main/resources/Test_chocolate", "src/main/resources/Test_Drinks");
        int count = 0;
        for (Snacks s : store.candies) {
            if (count == 0) {

                assertEquals("Candy", s.getName());
                assertEquals(1.5, s.getPrice());
                assertEquals(10, s.getAmount());
            } else if (count == 1) {
                assertEquals("Sour Patch", s.getName());
                assertEquals(5.0, s.getPrice());
                assertEquals(10, s.getAmount());
            } else if (count == 2) {
                assertEquals("Skittles", s.getName());
                assertEquals(6.0, s.getPrice());
                assertEquals(10, s.getAmount());

            } else if (count == 3) {
                assertEquals("Reeses", s.getName());
                assertEquals(1.5, s.getPrice());
                assertEquals(10, s.getAmount());
            } else if (count == 4) {
                assertEquals("Kit Kat", s.getName());
                assertEquals(1.5, s.getPrice());
                assertEquals(10, s.getAmount());
            }
            count++;
        }

    }

    @Test
    public void TestUpdatedLs() {
        List<Snacks> ls = new ArrayList<>();
        ls.add(new Snacks("cola", 5, 10, 1, "drinks"));
        ls.add(new Snacks("col", 5, 8, 1, "drinks"));
        Store s = new Store();
        s.writeFile(new File("src/main/resources/Test_Write"), ls);

        List<Snacks> receiveLs = new ArrayList<>();
        s.readFile(new File("src/main/resources/Test_Write"), "drinks", receiveLs);
        int count = 0;
        for (Snacks snack : receiveLs) {
            if (count == 0) {

                assertEquals("cola", snack.getName());
                assertEquals(5, snack.getPrice());
                assertEquals(10, snack.getAmount());
            } else if (count == 1) {
                assertEquals("col", snack.getName());
                assertEquals(5, snack.getPrice());
                assertEquals(8, snack.getAmount());
            }
            count++;
        }

    }

    @Test
    public void TestFindItemAccordingType(){
        store = new Store();
        store.configList("src/main/resources/Test_Candies", "src/main/resources/Test_Chips"
                , "src/main/resources/Test_chocolate", "src/main/resources/Test_Drinks");
        assertEquals("Candy", store.candies.get(0).getName());
    }

    // test calculate change
    @Test
    public void TestGiveChange(){
        Cashier c = new Cashier("szc","szc2002");
        Map<Double,Integer> realMap = new HashMap<>();
        realMap = c.the_change_amount(10,3.6);
        Map<Double,Integer> ExpectMap = new HashMap<>();
        ExpectMap.put(2.0,0);
        ExpectMap.put(1.0,1);
        ExpectMap.put(0.5,0);
        ExpectMap.put(0.2,2);
        ExpectMap.put(0.1,0);
        ExpectMap.put(0.05,0);
        ExpectMap.put(20.0,0);
        ExpectMap.put(10.0,0);
        ExpectMap.put(5.0,1);
        ExpectMap.put(100.0,0);
        ExpectMap.put(50.0,0);
        assertEquals(ExpectMap,realMap);
        Map<Double, Integer> expect = new HashMap<>();
        expect.put(1.0,1);
        expect.put(0.2,2);
        expect.put(5.0,1);
        Map<Double,Integer> real1 = c.change_give_to_customer(realMap);
        assertEquals(expect, real1);

        Map<Double,Integer> testMap = new HashMap<>();
        Map<Double, Integer> real2 = c.change_give_to_customer(testMap);
        assertNull(real2);

        //test change amount to map
        Map<Double,Integer> testMap1 = new HashMap<>();
        testMap1.put(2.0,0);
        testMap1.put(1.0,0);
        testMap1.put(0.5,0);
        testMap1.put(0.2,0);
        testMap1.put(0.1,0);
        testMap1.put(0.05,0);
        testMap1.put(20.0,0);
        testMap1.put(10.0,0);
        testMap1.put(5.0,0);
        testMap1.put(100.0,1);
        testMap1.put(50.00,0);
        Map<Double,Integer> real3 = c.change_amount_to_map(100);
        assertEquals(testMap1,real3);

    }
    @Test
    // update the money inside of vending machine
    public void TestUpdateMoney(){
        Cashier c = new Cashier("szc","szc2002");
        Map<Double,Integer> origin = new HashMap<>();
        origin.put(100.0,20);
        origin.put(50.0,30);
        origin.put(20.0,10);
        origin.put(10.0,50);
        origin.put(5.0,60);
        origin.put(2.0, 40);
        origin.put(1.0,35);
        origin.put(0.5,58);
        origin.put(0.2,45);
        origin.put(0.1,23);
        origin.put(0.05,49);
        //c.update("src/main/resources/TestCash");
        Map<Double,Integer> TestInput = new HashMap<>();
        TestInput.put(100.0,0);
        TestInput.put(50.0,0);
        TestInput.put(20.0,0);
        TestInput.put(10.0,0);
        TestInput.put(5.0,1);
        TestInput.put(2.0,0);
        TestInput.put(1.0,1);
        TestInput.put(0.5,0);
        TestInput.put(0.2,2);
        TestInput.put(0.1,0);
        TestInput.put(0.05,0);
        Map<Double,Integer> real = c.updateCash(TestInput,"src/main/resources/TestCash");
        Map<Double,Integer> ExpectMap = new HashMap<>();
        ExpectMap.put(100.0,20);
        ExpectMap.put(50.0,30);
        ExpectMap.put(20.0,10);
        ExpectMap.put(10.0,50);
        ExpectMap.put(5.0,59);
        ExpectMap.put(2.0,40);
        ExpectMap.put(1.0,34);
        ExpectMap.put(0.5,58);
        ExpectMap.put(0.2, 43);
        ExpectMap.put(0.1,23);
        ExpectMap.put(0.05,49);
        c.setCash(origin);
        c.update("src/main/resources/TestCash");
        assertEquals(ExpectMap,real);


    }

    //Test how to change the Cash file
    @Test
    public void TestModifyCash(){
        Map<Double,Integer> origin = new HashMap<>();
        origin.put(100.0,20);
        origin.put(50.0,30);
        origin.put(20.0,10);
        origin.put(10.0,50);
        origin.put(5.0,60);
        origin.put(2.0, 40);
        origin.put(1.0,35);
        origin.put(0.5,58);
        origin.put(0.2,45);
        origin.put(0.1,23);
        origin.put(0.05,49);
        Cashier c = new Cashier("szc","szc2002");
        c.setCash(origin);
        c.modifyCash(2.0,2, "src/main/resources/TestModifyCash");
        Map<Double,Integer> real = c.getCash("src/main/resources/TestModifyCash");
        int realAmount = real.get(2.0);
        assertEquals(2,realAmount);

        //Test Add cash
        Map<Double,Integer> addedCash = new HashMap<>();
        addedCash.put(100.0,1);
        addedCash.put(50.0,0);
        addedCash.put(20.0,0);
        addedCash.put(10.0,0);
        addedCash.put(5.0,0);
        addedCash.put(2.0,0);
        addedCash.put(1.0,0);
        addedCash.put(0.5,0);
        addedCash.put(0.2,0);
        addedCash.put(0.1,0);
        addedCash.put(0.05,0);
        c.addCash(addedCash);
        c.update("src/main/resources/TestModifyCash");
        Map<Double,Integer> real1 = c.getCash("src/main/resources/TestModifyCash");
        Map<Double,Integer> expect = new HashMap<>();
        expect.put(100.0,21);
        expect.put(50.0,30);
        expect.put(20.0,10);
        expect.put(10.0,50);
        expect.put(5.0,60);
        expect.put(2.0, 2);
        expect.put(1.0,35);
        expect.put(0.5,58);
        expect.put(0.2,45);
        expect.put(0.1,23);
        expect.put(0.05,49);
        assertEquals(expect,real1);
        c.setCash(origin);
        c.update("src/main/resources/TestModifyCash");

    }

    // test login SOF-62, SOF-63 SOF - 65
    @Test
    public void TestLOGIN(){

        Login L = new Login("src/main/resources/Account");
        String real = L.checkLogin("L","1");
        assertEquals("Owner", real);


        //edge case, file not found
        Login L1 = new Login("src/main/resources/Account1");
        String real1 = L1.checkLogin("L","1");
        assertNull(real1);


        // edge case, username or password does not exist
        Login L2 = new Login("src/main/resources/Account");
        String real2 = L2.checkLogin("LeoYang1","1");
        assertNull(real2);
    }




    //SOF - 64  // SOF - 66
    @Test
    public void TestModifyAccountFileAndIdentifyUser(){
        int real = Owner.addRole("s","h","Seller","src/main/resources/TestAccount");
        int real2 = Owner.addRole("s1","h","Customer","src/main/resources/TestAccount");
        int real3 = Owner.addRole("s2","h","Owner","src/main/resources/TestAccount");
        int real4 = Owner.addRole("s3","h","Cashier","src/main/resources/TestAccount");
        assertEquals(1,real);
        assertEquals(1,real2);
        assertEquals(1,real3);
        assertEquals(1,real4);
        int real5 = Owner.addRole("s","h","Cashier","src/main/resources/TestAccount");
        assertEquals(0,real5);
        List<String> result = new ArrayList<>();
        File file = new File("src/main/resources/TestAccount");
        Scanner scan;
        int count = 0;
        try {
            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                if (count > 0) {
                    String[] tmp = scan.nextLine().split(",");
                    switch (tmp[2]) {
                        case "Seller" -> result.add(tmp[0]);
                    }
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        assertEquals("B",result.get(1));

        //Test identify user
        Customer c = new Customer(1000000,1,"H","1");

        Roles r = new Roles("L", "1");
        String real1 = r.getUsername();
        assertEquals("L", real1);

        String realPassWord = r.getPassword();
        assertEquals("1",realPassWord);


        assertEquals(c.getUsername(), Roles.roles.get(0).getUsername());
        assertEquals(c.getPassword(), Roles.roles.get(0).getPassword());

        //TestRemoveRole
        Owner n = new Owner("H", "1");
        n.removeRole("H", "src/main/resources/TestAccount");
        Owner.addRole("H", "1","Customer","src/main/resources/TestAccount");
        assertEquals("H",Owner.roles.get(9).getUsername());

        n.removeRole("ss","src/main/resources/TestAccount");

    }


    @Test
    public void TestViewCancelReport(){
        Owner O = new Owner("H","1");

        String[] s1 = {"2022/11/01 21:47:27","anonymous","TIMEOUT"};
        String[] s2 = {"2022/11/01 21:48:12","H","TIMEOUT"};

        List<String[]> result = new ArrayList<>(O.getCanceled("src/main/resources/TestCancel"));


        assertArrayEquals(s1,result.get(0));
        assertArrayEquals(s2,result.get(1));

    }

    @Test
    public void recentFiveItems(){
        List<String[]> result = Items.show("anonymous","src/main/resources/TestTransactionHist");
        String[] expect1 = {"1","Mineral Water"};
        assertArrayEquals(expect1,result.get(0));

    }

    @Test
    public void TestCheckCard(){
        GetCardInfo getCardInfo = new GetCardInfo();
        getCardInfo.parse("src/main/resources/credit_cards.json");
        Customer c = new Customer(100000,1,"H","sss");
        boolean real = c.checkCard("Charles","40691");
        assertTrue(real);
        boolean real2 = c.checkCard("Charles","40692");
        assertFalse(real2);

    }


    @Test
    public void TestGetCardInfo(){
        GetCardInfo getCardInfo = new GetCardInfo();
        getCardInfo.parse("src/main/resources/credit_cards.json");
        Customer c = new Customer(100000,1,"Q","sss");
        String[] result = c.getCardInfo("src/main/resources/CustomerInfo");
        String[] expect = {"Patricia","30690"};
        assertArrayEquals(expect,result);
        Customer c1 = new Customer(100000,1,"l","sss");
        String[] result1 = c1.getCardInfo("src/main/resources/CustomerInfo");
        assertNull(result1);
    }



    @Test
    public void TestSnack(){
        Snacks s = new Snacks("coke",10,10,1,"drink");
        s.setName("coke1");
        s.setPrice(11);
        s.setAmount(10);
        s.setId(2);
        s.setType("drink1");

        assertEquals("coke1",s.getName());
    }


    @Test
    public void TestCustomer(){
        Customer c = new Customer(100,1,"szc","sss");
        c.setBalance(1000);
        c.setId(2);
        assertEquals(2,c.getId());
        assertEquals(1000,c.getBalance());
    }


    @Test
    public void TestSeller(){
        App.store = new Store();
        Snacks sn = new Snacks("Mineral Water",4.0,1,1,"Drink");
        Seller s = new Seller("sss","111");
        Seller.modifyItem("Mineral Water","Drinks",4.0,3, 1);

    }


    @Test
    public void TestReport(){
        String[] expect1 = {"2022/10/27 07:29:43!1 Sprite!10.0![1.0=1]!Cash!anonymous!0"};
        List<String[]> result = Seller.generateReport("src/main/resources/TestTransactionHist");
        assertArrayEquals(expect1,result.get(0));
    }


    @Test
    public void testStore(){
        Store s = new Store();
        Snacks n = s.get_snacks().get(0);
        assertEquals("Mineral Water", n.getName());
        String[] expect = {"Drinks", "Chocolates", "Chips", "Candies"};
        String[] result = s.get_item_name();
        assertArrayEquals(expect,result);
        Snacks S = Store.get_item("Mineral Water");
        assert S != null;
        assertEquals("Mineral Water", S.getName());
        int result1 = s.checkItem("Mineral Water");
        assertEquals(0,result1);

        int result2 = s.checkItem("11");
        assertEquals(-1,result2);
    }
}
