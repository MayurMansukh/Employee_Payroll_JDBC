package com.mypackage;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class JSONServerEmployeeDataTest {


        @BeforeAll
        static void setup(){
            RestAssured.baseURI="http://localhost";
            RestAssured.port=4000;
        }

        public JSONServerEmployeeData[] getEmployeelist(){
            Response response= RestAssured.get("/employees");
            System.out.println("employee entries in json server: \n"+response.asString());
            JSONServerEmployeeData[] jsonServerEmployeeData=new Gson().fromJson(response.asString(),JSONServerEmployeeData[].class);
            return jsonServerEmployeeData;
        }

        public Response addEmployeeDataToJsonServer(JSONServerEmployeeData restAssureEmpData){
            String employee=new Gson().toJson(restAssureEmpData);
            RequestSpecification requestSpecification=RestAssured.given();
            requestSpecification.header("Content-Type","application/json");
            requestSpecification.body(employee);
            return requestSpecification.post("/employees");
        }

        @Test
        public void givenEmployeeDateToGsonServer_shouldRetrieveServerData(){
            JSONServerEmployeeData[] restAssureEmployeeData=getEmployeelist();
            System.out.println(restAssureEmployeeData);
            Assertions.assertEquals(4,restAssureEmployeeData.length);
        }

    @Test
    public void addNewEmployee_shouldReturn_201ResponseCode(){
        JSONServerEmployeeData[] restAssureEmployeeData=getEmployeelist();
        JSONServerEmployeeData jsonServerEmployeeData1=new JSONServerEmployeeData(5,"mayur",3000);
        Response response=addEmployeeDataToJsonServer(jsonServerEmployeeData1);
        int statusCode= response.statusCode();
        Assertions.assertEquals(201,statusCode);
        Assertions.assertEquals(4,restAssureEmployeeData.length);
    }

}