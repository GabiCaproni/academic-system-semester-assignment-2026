package org.example.academic.system.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Exam extends Assessment {

public Exam(String name, double weight, double value) {
    super(name, weight, value);
}

}
