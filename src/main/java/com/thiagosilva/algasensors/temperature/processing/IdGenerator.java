package com.thiagosilva.algasensors.temperature.processing;

import java.util.UUID;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochRandomGenerator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerator {
    private static final TimeBasedEpochRandomGenerator uuidV7Generator = Generators.timeBasedEpochRandomGenerator();

    public static UUID generateTimeBasedUUID() {
        return uuidV7Generator.generate();
    }
    
}
