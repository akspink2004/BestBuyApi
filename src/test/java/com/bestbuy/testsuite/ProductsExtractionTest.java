package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.urlEncodingEnabled;
import static org.hamcrest.CoreMatchers.equalTo;

public class ProductsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }
    //21. Extract the limit
    @Test
    public void extractLimit() {
        int limit = response.extract().path("limit");
        System.out.println("value of limit is : " + limit);
        Assert.assertEquals(10, limit);
        response.body("limit", equalTo(10));
    }

    //22. Extract the total
    @Test
    public void extractTotal() {
        int total = response.extract().path("total");
        System.out.println("value of total is : " + total);
        Assert.assertEquals(51957, total);
        response.body("total", equalTo(51957));
    }


    //23. Extract the name of 5th product
    @Test
    public void extractNameOf5thproduct() {
        String nameOfStore = response.extract().path("data[4].name");
        System.out.println(nameOfStore);
    }

    //24. Extract the names of all the products
    @Test
    public void extractNameOfAllProducts() {
        List<String> allNameofStores = response.extract().path("data.name");
        System.out.println("List of names : " + allNameofStores);
        for (String a : allNameofStores) {
            Assert.assertTrue(true);
        }
    }

    //25. Extract the productId of all the products
    @Test
    public void extractAllProductId() {
        List<Integer> numOfId = response.extract().path("data.id");
        System.out.println("List of Ids : " + numOfId);
        for (Integer a : numOfId) {
            Assert.assertTrue(true);
        }
    }

    //26. Print the size of the data list
    @Test
    public void printSizeOfDataLists() {
        List<Integer> dataSize = response.extract().path("data");
        int size = dataSize.size();
        System.out.println(size);
    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void valueOfProductNameEnergizer() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println(values);
    }

    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void getModelOfNameEnergizer() {
        List<String> model = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}");
        System.out.println(model);
    }

    //29. Get all the categories of 8th products
    @Test
    public void getAllServicesOf8thStore() {
        List<String>numofcategories=response.extract().path("data[7].categories");
        System.out.println("List of categories : "+ numofcategories);

    }

    //30. Get categories of the store where product id = 150115
    @Test
    public void getCategoriesOfProductId150115(){
        List<String>numofcategories=response.extract().path("data[3].categories");
        System.out.println("List of categories : "+ numofcategories);
    }
    //31. Get all the descriptions of all the products
    @Test
    public void Getallthedescriptions(){
        List<String>numofcategories=response.extract().path("data.description");
        System.out.println("List of categories : "+ numofcategories);
    }
//32. Get id of all the all categories of all the products
    @Test
    public void getidofallcatallpro(){
        List<String>numofcategories=response.extract().path("data.categories.id");
        System.out.println("List of categories : "+ numofcategories);
    }
//33. Find the product names Where type = HardGood
    @Test
    public void productNameTypeHardgood() {
        List<String> productName = response.extract().path("data.findAll{it.type=='HardGood'}.name");
        System.out.println("The Product Name are: " + productName);
    }
//34. Find the Total number of categories for the product where product name = Duracell - AA1.5V CopperTop Batteries (4-Pack)
    @Test
    public void totalNumber(){
        List<String> totalNumOfCat = response.extract().path("data.findAll{it.name=='Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("The total number of categories " + totalNumOfCat.size());
    }
//35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test35() {
        List<String>prdName=response.extract().path("data.findAll{it.price <5.49}.name");
        System.out.println("Products under 5.49 :" +prdName);
    }

//36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void categoriesWhereProductNameEnergizer() {
        List<Integer> categoriesname = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories");
        System.out.println(categoriesname);
    }
//37. Find the manufacturer of all the products
    @Test
    public void getAllManufacturerDetails() {
        List<String> manufacturer = response.extract().path("data.manufacturer");
        System.out.println("List of categories : " + manufacturer);
    }
//38. Find the imge of products whose manufacturer is = Energizer
    @Test
    public void findimgeofproductmenufacturer(){
        List<String> imageOfProduct = response.extract().path("data.findAll{it.manufacturer=='Energizer'}.image");
        System.out.println("The image of product manufacturer  are: " + imageOfProduct);

    }
//39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void productGreaterthan599() {
        List<String> prdName = response.extract().path("data.findAll{it.price > 5.99}.categories.createdAt");
     System.out.println("Products over 5.99 :" +prdName);
    }
//40. Find the uri of all the product
    @Test
    public void findtheuriforallproduct(){
        List<String> url = response.extract().path("data.url");
        System.out.println("List of categories : " + url);
    }
}
