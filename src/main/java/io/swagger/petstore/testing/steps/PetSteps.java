package io.swagger.petstore.testing.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.swagger.petstore.testing.controllers.pet.PetController;
import io.swagger.petstore.testing.models.apiResponse.ApiResponse;
import io.swagger.petstore.testing.models.pet.Pet;
import org.apache.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PetSteps extends PetController {

    @Step("Успешный post нового pet {pet}")
    public PetSteps createPetSuccessfully(Pet pet) {
        Response response = postPet(pet);
        assertStatusCode(HttpStatus.SC_OK, response);
        return this;
    }

    @Step("Post невалидного объекта {pet}")
    public PetSteps postBadRequest(Object pet) {
        Response response = postPet(pet);
        assertStatusCode(HttpStatus.SC_BAD_REQUEST, response);
        return this;
    }

    @Step("Успешный get pet по Id {petId}")
    public Pet getPetById(String petId) {
        Response response = getPet(petId);
        assertStatusCode(HttpStatus.SC_OK, response);
        return response.as(Pet.class);
    }

    @Step("Pet not found на get pet по id {petId}")
    public PetSteps getNotFoundPetById(String petId) {
        Response response = getPet(petId);
        assertStatusCode(HttpStatus.SC_NOT_FOUND, response);
        assertErrorMessage("Pet not found", response.as(ApiResponse.class));
        return this;
    }

    @Step("Проверка полей pet {expectedPet}")
    public PetSteps assertPetData(Pet expectedPet) {
        Pet pet = getPetById(expectedPet.getId().toString());
        assertThat(pet, equalTo(expectedPet));
        return this;
    }

    @Step("Успешный delete существующего pet по id {petId}")
    public PetSteps deletePetById(String petId) {
        Response response = deletePet(petId);
        assertStatusCode(HttpStatus.SC_OK, response);
        return this;
    }

    @Step("Попытка delete несуществующего pet по id {petId}")
    public PetSteps deleteNotFoundPetById(String petId) {
        Response response = deletePet(petId);
        assertStatusCode(HttpStatus.SC_NOT_FOUND, response);
        return this;
    }

    @Step("Успешное обновление данных pet {pet}")
    public PetSteps putPetSuccessfully(Pet pet) {
        Response response = putPet(pet);
        assertStatusCode(HttpStatus.SC_OK, response);
        assertPetData(pet);
        return this;
    }

    @Step("Put невалидного объекта {pet}")
    public PetSteps putBadRequest(Object pet) {
        Response response = putPet(pet);
        assertStatusCode(HttpStatus.SC_BAD_REQUEST, response);
        return this;
    }


    @Step("Проверка status code {ExpectedStatus}")
    private void assertStatusCode(int ExpectedStatus, Response response) {
        assertThat(response.statusCode(), equalTo(ExpectedStatus));
    }

    @Step("Проверка message {expectedMessage}")
    public void assertErrorMessage(String expectedMessage, ApiResponse apiResponse) {
        assertThat(apiResponse.getMessage(), equalTo(expectedMessage));
    }

}
