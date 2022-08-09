// 1- pacote

package petstore;
// 2- bibliotecas

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.Matchers.contains;

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

       given() // dado
              .contentType("application/Json" )
              .log().all()
              .body(JsonBody)

       .when() // quando
              .post(uri)

       .then()//entao
               .log().all()
               .statusCode(200)
               .body("name", is ("Jymy"))
               .body("status", is("available"))
               .body("category.name", is ("cat"))
               .body( "tags.name", contains("sta") )


       ;



    }

    //novo metodo

@Test
 public void consultarPet(){
     String petId = "9223372036854060687";

     given()
             .contentType(("aplication/json"))
             .log().all()
     .when()
             .get(uri + "/" + petId )

     .then()
             .log().all()
             .statusCode(200)
             .body("name", is ("Jymy"))
             .body("category.name", is ("cat"))
             .body("status", is("available"))


 ;




 }

}
