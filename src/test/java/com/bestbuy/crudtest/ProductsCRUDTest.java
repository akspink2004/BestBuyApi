package com.bestbuy.crudtest;

import com.bestbuy.model.ProductsPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductsCRUDTest {
    static int idNumber;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/products";
    }

    @Test // get all list
    public void test001() {

        given()
                .when()
                .log().all()
                .get()
                .then().log().all().statusCode(200);
    }

    @Test // post new and retrive id
    public void test002() {

        ProductsPojo pojo = new ProductsPojo();
        pojo.setName("anki");
        pojo.setType("patel");
        pojo.setPrice(172);
        pojo.setShipping(232);
        pojo.setUpc("galaxy");
        pojo.setDescription("desp2");
        pojo.setManufacturer("ford3");
        pojo.setModel("kent");
        pojo.setUrl("bro");
        pojo.setImage("londis");


        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(pojo)
                .post();
        response.then().statusCode(201);
         idNumber = response.then().extract().path("id");

        System.out.println(idNumber);


    }

    @Test //update id
    public void test003() {
        ProductsPojo pojo = new ProductsPojo();
        pojo.setPrice(170);
        pojo.setShipping(2300);
        pojo.setManufacturer("pinky");
        pojo.setModel("patel");

        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id",idNumber)
                .when()
                .body(pojo)
                .patch("/{id}");
        response.then().statusCode(200);


    }

    @Test// delete it
    public void test004() {
        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", idNumber)
                .when()
                .delete("/{id}");
        response.then().statusCode(200);



    }

    @Test// retrive id and validate
    public void test005() {
        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", idNumber)
                .when()
                .get("/{id}");
        response.then().statusCode(404);


    }

}
