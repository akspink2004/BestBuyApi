package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;

public class ProductsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = RestAssured.given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }
    @Test
    public void verifyTotalProducts() {
        response.body("total",equalTo(51957));
    }

    //Verify the if the stores of limit is equal to 10
    @Test
    public void verifyStoresLimit() {
        response.body("limit", equalTo(10));
    }
    //13. Check the single ‘Name’ in the Array list (Duracell - AAA Batteries (4-Pack))
    @Test
    public void test13() {
        response.body("data.name", hasItem("Duracell - AAA Batteries (4-Pack)"));
    }

    //14. Check the multiple ‘Names’ in the ArrayList (Duracell - AA 1.5V CopperTop Batteries (4-
    //Pack), Duracell - AA Batteries (8-Pack), Energizer - MAX Batteries AA (4-Pack))
    @Test
    public void test14(){
        response.body("data.name",hasItems("Duracell - AA 1.5V CopperTop Batteries (4-Pack)","Duracell - AA Batteries (8-Pack)","Energizer - MAX Batteries AA (4-Pack)"));
    }
    @Test
    public void test15(){
        response.body("data[3].categories[2]", Matchers.hasEntry("name","Household Batteries"));
    }
    @Test
    public void test16(){
        response.body("data[8].categories[1]",Matchers.hasEntry("name","Housewares"));
    }

    @Test
    public void test18(){
        response.body("data[5]",Matchers.hasEntry("name","Duracell - D Batteries (4-Pack)"));
    }
    //19. Verify the ProductId = 333179 for the 9th product
    @Test
    public void test19(){
        response.body("data[8].id",equalTo(333179));
    }
    //20. Verify the prodctId = 346575 have 5 categories
    @Test
    public void test20(){
        response.body("data[9].categories",Matchers.hasSize(5));
    }



}
