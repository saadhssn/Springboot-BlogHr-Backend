package com.example.BlogHR.payload;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;
    @NotBlank
    @Size(min = 5, max = 100, message = "Comment size must be min 5 chars and max is 100 chars")
    private String content;

    @NotNull
    private String userName;

    @NotNull
    private Long userId;
}
