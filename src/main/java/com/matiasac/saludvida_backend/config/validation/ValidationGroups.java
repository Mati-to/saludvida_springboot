package com.matiasac.saludvida_backend.config.validation;

import jakarta.validation.GroupSequence;
import lombok.Builder;

public interface ValidationGroups {

    interface Primero {}
    interface Segundo {}
    interface Tercero {}

    @GroupSequence({Primero.class, Segundo.class, Tercero.class, Builder.Default.class})
    interface Orden {}
}
