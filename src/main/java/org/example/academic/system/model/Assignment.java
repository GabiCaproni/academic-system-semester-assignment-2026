package org.example.academic.system.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Assignment extends Assessment {

public Assignment(
        String name,
        double weight,
        double value) {

    super(name, weight, value);
}
}
