package com.chinexboroja.core.model.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    // Representation is what holds th data and is serialized into JSON. It's a model for RESTful applications

    @NotNull
    private Integer id;

    @NotBlank @Length(min = 2, max = 255)
    private String firstName;

    @NotBlank @Length(min=2, max = 255)
    private String lastName;

    @Pattern(regexp = ".+@.+\\.[a-z]+")
    private String email;

    // if required in some cases, we can prevent a property from being a part of the JSON representation by adding the
    // @JsonIgnore annotation to its getter

}
