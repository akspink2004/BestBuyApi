package com.bestbuy.crudtest;

import com.bestbuy.model.ProductsPojo;
import com.bestbuy.model.StorePojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCURDTest {
        static int id;
        @BeforeClass
        public static void inIt() {
            RestAssured.baseURI = "http://localhost";
            RestAssured.port = 3030;
            RestAssured.basePath = "/stores";
        }

        @Test // get all Stores
        public void test001() {

            given()
                    .when()
                    .log().all()
                    .get()
                    .then().log().all().statusCode(200);
        }

        @Test // post new and retrive id
        public void test002() {
            StorePojo pojo = new StorePojo();
            pojo.setName("Besst course");
            pojo.setType("training");
            pojo.setAddress("10 dorchester");
            pojo.setAddress2("ha39rf");
            pojo.setCity("harrow");
            pojo.setState("fyfcy");
            pojo.setZip("1234");
            pojo.setLat(1);
            pojo.setLng(6);
            pojo.setHours("12:00");

            Response response = given()
                    .log().all()
                    .header("Content-Type", "application/json")
                    .when()
                    .body(pojo)
                    .post();
            response.then().statusCode(201);
            id = response.then().extract().path("id");
            System.out.println(id);
        }

        @Test //update id
        public void test003() {
            StorePojo pojo = new StorePojo();
            pojo.setName("Boss part");
            pojo.setType("testing");
            pojo.setAddress("11 dorchester");
            pojo.setAddress2("ha39rf");


            Response response = given()
                    .log().all()
                    .header("Content-Type", "application/json")
                    .pathParam("id", id)
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
                    .pathParam("id", id)
                    .when()
                    .delete("/{id}");
            response.then().statusCode(200);
        }

        @Test// retrive id and validate
        public void test005() {
            Response response = given()
                    .log().all()
                    .header("Content-Type", "application/json")
                    .pathParam("id", id)
                    .when()
                    .get("/{id}");
            response.then().statusCode(404);
        }
    }