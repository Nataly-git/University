package com.thesoftwarepartner.thesoftwarepartnertesttask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {
    private String category;
    private String message;
}
