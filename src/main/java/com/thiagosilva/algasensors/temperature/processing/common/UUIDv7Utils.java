package com.thiagosilva.algasensors.temperature.processing.common;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UUIDv7Utils {

    public static OffsetDateTime extractOffsetDateTime(UUID uuid) {

        if (uuid == null) {
            return null;
        }

        // bytewise opperator (remove os bits da versão)
        long timestamp = uuid.getMostSignificantBits() >>> 16;

        return OffsetDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

}
