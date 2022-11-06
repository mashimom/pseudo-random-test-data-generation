package org.shimomoto.blog.pttdg.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder(toBuilder = true)
@Data
public class Book {
    String title;
    String author;
    LocalDate release;
    double price;
    double margin;
    int stockQuantity;

    public double calculateAskingPrice() {
        return price * (1+margin);
    }
}
