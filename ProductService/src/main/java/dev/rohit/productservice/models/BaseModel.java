package dev.rohit.productservice.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BaseModel {

    Long id;

    LocalDate createdAt;

    LocalDate updatedAt;

}
