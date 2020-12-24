package io.swagger.petstore.testing;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.swagger.petstore.testing.models.pet.Pet;
import io.swagger.petstore.testing.steps.PetSteps;
import io.swagger.petstore.testing.utils.TestDataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Pet store")
@Feature("Pet")
@Story("Delete")
@DisplayName("Удаление pet")
public class DeletePetTest extends BaseTest {

    private final PetSteps petSteps = new PetSteps();
    private final Pet fullDataPet = TestDataGenerator.generateFullDataPet();
    private final String notFoundId = "-1";

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Удаление pet")
    public void deletePet() {
        petSteps.createPetSuccessfully(fullDataPet)
            .deletePetById(fullDataPet.getId().toString())
            .getNotFoundPetById(fullDataPet.getId().toString());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Попытка удаления pet по несуществующему Id")
    public void deleteNotFoundPetTest() {
        petSteps.deleteNotFoundPetById(notFoundId);
    }
}
