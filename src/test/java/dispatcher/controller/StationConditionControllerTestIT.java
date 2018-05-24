package dispatcher.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Sovalov.AV on 24.05.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StationConditionControllerTestIT {

    @Autowired
    private StationConditionController controller;

    @Test
    public void controllerInitializedController(){
        assertThat(controller).isNotNull();
    }
}
