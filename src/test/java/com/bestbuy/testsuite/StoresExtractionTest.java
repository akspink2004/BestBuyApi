package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class StoresExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    // 1. Extract the limit
    @Test
    public void extractLimit() {

        int limit = response.extract().path("limit");
        System.out.println("value of limit is : " + limit);
        Assert.assertEquals(10, limit);
        response.body("limit", equalTo(10));
    }

    // 2. Extract the total
    @Test
    public void extractTotal() {
        int total = response.extract().path("total");
        System.out.println("value of total is : " + total);
        Assert.assertEquals(1561, total);
        response.body("total", equalTo(1561));
    }

    //3. Extract the name of 5th store
    @Test
    public void extractNameOf5thStore() {
        String nameOfStore = response.extract().path("data[4].name");
        System.out.println(nameOfStore);
    }

    //4. Extract the names of all the store
    @Test
    public void extractNameOfAllStores() {
        List<String> allNameofStores = response.extract().path("data.name");
        System.out.println("List of names : " + allNameofStores);
        for (String a : allNameofStores) {
            Assert.assertTrue(true);
        }
    }

    //5.Extract the storeId of all the store
    @Test
    public void extractAllStoresId() {
        List<Integer> numOfId = response.extract().path("data.id");
        System.out.println("List of Ids : " + numOfId);
        for (Integer a : numOfId) {
            Assert.assertTrue(true);
        }
    }

    //6.Print the size of the data list
    @Test
    public void printSizeOfLists() {
        List<Integer> dataSize = response.extract().path("data");
        int size = dataSize.size();
        System.out.println(size);
    }

    //7.Get all the value of the store where store name = St Cloud
    @Test
    public void valueOfStoreNameStCloud() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println(values);
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void getAdressOfNameRochester() {
        List<String> address = response.extract().path("data.findAll{it.name == 'Rochester'}");
        System.out.println(address);
    }

    //9. Get all the services of 8th store
    @Test
    public void getAllServicesOf8thStore() {
        List<Integer> serviceSize = response.extract().path("data[7].services");
        int size = serviceSize.size();
        System.out.println(size);
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void storeServicesOfWindowsStore() {
        List<String> storeservice = response.extract().path("data.findAll{it.name == 'Windows Store'}");
        System.out.println(storeservice);
    }

    //11. Get all the storeId of all the store
    @Test
    public void getAllStoreIdOfAllStores() {
        List<Integer> allStoresId = response.extract().path("data.services.storeservices.storeId");
        int size1 = allStoresId.size();
        System.out.println(size1);
    }

    //12. Get id of all the store
    @Test
    public void getIdOfAllStores() {
        List<Integer> allStoresIds = response.extract().path("data.id");
        int size2 = allStoresIds.size();
        System.out.println(size2);
    }

    //13. Find the store names Where state = ND
    @Test
    public void getStateNdStoreName() {
        List<String> nameOfStoreStateNd = response.extract().path("data.findAll{it.state == 'ND'}");
        System.out.println(nameOfStoreStateNd);
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test0014() {
        int total = response.extract().path("data[8].services.size");
        List<String> services = response.extract().path("data.findAll{it.name=='Rochester'}.services");
        System.out.println("List of services are : " + services);
        System.out.println("List of Total services are : " + total);
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void allwindowsstore() {
        List<HashMap<String, Object>> created = response.extract().path("data.services[5].createdAt");
        System.out.println("The createdAt services are: " + created);
    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void fargo() {
        List<String> services = response.extract().path("data.findAll{it.name=='Fargo'}.services.name");
        System.out.println("The store name are: " + services);
    }
    //17. Find the zip of all the store
    @Test
    public void zipallstore() {
        List<Integer> zip = response.extract().path("data.zip");
        System.out.println("Zip list are: " + zip);

    }
    //18. Find the zip of store name = Roseville
    @Test
    public void ziproseville() {

        List<Integer> zip = response.extract().path("data.findAll{it.name=='Roseville'}.zip");
        System.out.println("Zip for the store are: " + zip);
    }
    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void storeservice() {
        List<HashMap<String, ?>> services = response.extract().path("data.findAll{it.name=='Magnolia Home Theater'}.services");
        System.out.println("services detail are: " + services);
    }
    //20. Find the lat of all the stores
    @Test
    public void latofstore() {
        List<Double> lat = response.extract().path("data.lat");
        System.out.println("The store lat are: " + lat);
    }
}
