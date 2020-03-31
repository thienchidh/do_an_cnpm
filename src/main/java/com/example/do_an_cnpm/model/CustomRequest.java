package com.example.do_an_cnpm.model;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@NonNull
public class CustomRequest<T> {
    private String token;
    private T data;
}
