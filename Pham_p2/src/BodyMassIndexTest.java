import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BodyMassIndexTest {

    @Test
    void BMICategoryOverweightTest() {
        String actual = BodyMassIndex.BMICategory(62, 130);

        Assertions.assertEquals("You are Normal Weight. Nice!", actual);
    }
        @Test
    void BMIScoreTest() {
        double actual = BodyMassIndex.BMIScore(70, 160);

        Assertions.assertEquals(22.955102040816326530612244897959, actual);
        }

        @Test
    void ConstructorBMITest() {
        BodyMassIndex actual = new BodyMassIndex(71,200);

        Assertions.assertEquals(27.8912914104344376115850029756, actual.GetBMIScore());
        Assertions.assertEquals("You are overweight. Do an exercise!", actual.GetBMICategory());
        }
    }



