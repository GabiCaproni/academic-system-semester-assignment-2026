package org.example.academic.system.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PracticalAssignment extends Assessment {

private String technology;

public PracticalAssignment(String name,double weight,double value, String technology) {
    super(name, weight, value);
    this.technology = technology;
}

}
