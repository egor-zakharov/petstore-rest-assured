package io.swagger.petstore.testing;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.swagger.petstore.testing.data.IncorrectData;
import io.swagger.petstore.testing.models.pet.Pet;
import io.swagger.petstore.testing.steps.PetSteps;
import io.swagger.petstore.testing.utils.TestDataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Pet store")
@Feature("Pet")
@Story("Post")
@DisplayName("Добавление pet")
public class AddPetTest extends BaseTest {

    private final PetSteps petSteps = new PetSteps();
    private final Pet minDataPet = TestDataGenerator.generateMinDataPet();
    private final Pet fullDataPet = TestDataGenerator.generateFullDataPet();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Добавление pet с минимальным набором полей")
    public void createMinDataPet() {
        petSteps.createPetSuccessfully(minDataPet);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Добавление pet с максимальным набором полей")
    public void createFullDataPet() {
        petSteps.createPetSuccessfully(fullDataPet);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Обработка некорректного тела запроса методом post")
    public void postIncorrectJson() {
        petSteps.postBadRequest(IncorrectData.INCORRECT_JSON);
    }
}
