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
import static org.hamcrest.Matchers.stringContainsInOrder;

// 3 -classe

public class Pet {

// 3.1- atributos
    String uri = "https://petstore.swagger.io/v2/pet"; //endereço entidade pet

    // 3.2- metodos e funções


public String lerJson(String caminhoJson) throws IOException {

    return  new String(Files.readAllBytes(Paths.get(caminhoJson)));


}


   // incluir -  criar - post

    @Test(priority = 1) // identifica função como um teste para o testng
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
               .body( "tags.name", contains("token") )


       ;



    }

    //novo metodo

@Test(priority = 2)
 public void consultarPet(){
     String petId = "9223372036854060687";
     String token =

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

     .extract()
             .path("category.name")
     ;

    System.out.println(" Seu soken gerado è: "+ token );


 }
    //Alterar
    @Test(priority = 3)

    public void alterarPet () throws IOException   {
        String JsonBody = lerJson (  "db/pet2.json");

        given() // alterar
                .contentType("application/Json" )
                .log().all()
                .body(JsonBody)
        .when()
                .put(uri)

        .then()
                .log().all()
                .statusCode(200)
                .body("name", is ("Jymy"))
                .body("status", is("Vendido"))

                .extract()
                .path("category.name")
        ;

        System.out.println("Este Pet esta:" + "Vendido" );



    }

    @Test(priority = 4)
    public void excluirPet(){
        String petId = "9223372036854060687";

        given()
                .contentType("application/Json" )
                .log().all()

        .when()
                .delete(uri+ "/" + petId )

        .then()

                .log().all()
                .statusCode(200)
                .body("code", is (200))
                .body("type",is ("unknown"))
                .body("message",is (petId))
        ;






    }


}
