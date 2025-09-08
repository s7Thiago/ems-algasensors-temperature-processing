package com.thiagosilva.algasensors.temperature.processing;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.thiagosilva.algasensors.temperature.processing.common.IdGenerator;
import com.thiagosilva.algasensors.temperature.processing.common.UUIDv7Utils;

public class UUIDv7Test {

    @Test
    void shouldGenerateUUIDv7() {
        UUID uuid1 = IdGenerator.generateTimeBasedUUID();
        UUID uuid2 = IdGenerator.generateTimeBasedUUID();
        UUID uuid3 = IdGenerator.generateTimeBasedUUID();
        UUID uuid4 = IdGenerator.generateTimeBasedUUID();

        List<UUID> ids = List.of(uuid1, uuid2, uuid3, uuid4);

        int index = 1;
        for (UUID id : ids) {
            System.out.printf("UUID gerado %d - %s (   time:  %s )\n", index, id,
                    UUIDv7Utils.extractOffsetDateTime(id));
            index++;
        }
    }

    @Test
    void shouldVerifyUUIDv7CorrectTimestamp() {
        UUID uuid = IdGenerator.generateTimeBasedUUID();

        // Gera o timestamp truncando de forma que ignora a parte de segundos e
        // milissegundos, considerando apenas escala de minutos
        OffsetDateTime uuidDateTime = UUIDv7Utils.extractOffsetDateTime(uuid).truncatedTo(ChronoUnit.MINUTES);
        OffsetDateTime currenOffsetDateTime = OffsetDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        Assertions.assertThat(uuidDateTime).isEqualTo(currenOffsetDateTime);
    }
}
