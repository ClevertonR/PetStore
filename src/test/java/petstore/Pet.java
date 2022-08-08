// 1- pacote

package petstore;
// 2- bibliotecas

import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

// 3 -classe

public class Pet {

// 3.1- atributos
    String uri = "https://petstore.swagger.io/v2/pet"; //endereço entidade pet

    // 3.2- metodos e funções


public String lerJson(String caminhoJson) throws IOException {

    return  new String(Files.readAllBytes(Paths.get(caminhoJson)));


}


   // incluir -  criar - post

    @Test // identifica função como um teste para o testng
    public void incluirPet () throws IOException   {
     String JsonBody = lerJson (  "db/pet1.json");

     //Sintax gherkin
     // dado - quando - entao
     // given - when - then

       given()
              .contentType("application/Json" )
              .log().all()
              .body(JsonBody)

       .when()
              .post(uri)

       .then()
               .log().all()
               .statusCode(200)

       ;



    }



}
