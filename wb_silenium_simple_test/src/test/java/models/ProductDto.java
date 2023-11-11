package models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String name;

    private String brand;

    private String price;

    private String fakePrice;
}
