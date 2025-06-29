package com.example.driedfruitstore.model.dto.product;

import lombok.*;

import java.time.LocalDateTime;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductAdminDTO extends ProductDTO {
    private LocalDateTime created;
    private LocalDateTime modified;
}
