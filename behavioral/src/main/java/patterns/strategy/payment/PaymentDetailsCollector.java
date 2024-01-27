package patterns.strategy.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Getter
public class PaymentDetailsCollector<T> {

    private T details;

    public void collectPaymentDetails(
            Class<T> detailsClass,
            String jsonFileName) {
        try (InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream(jsonFileName)) {
            if (inputStream != null) {
                this.details =
                        new ObjectMapper().readValue(inputStream, detailsClass);
                log.info("{} details collected: {}",
                         detailsClass.getSimpleName(),
                         this.details);
            } else {
                log.error("Error: {} not found or is empty", jsonFileName);
            }
        } catch (IOException e) {
            log.error("Error reading {} details: {}",
                      detailsClass.getSimpleName(), e.getMessage());
        }
    }
}
