package com.example.BlogHR.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CategoryDto {

    private Long categoryId;

    @NotBlank
    @Size(min = 5, max = 50, message = "Title size must be min 5 chars and max is 50 chars")
    private String categoryTitle;

    @NotBlank
    @Size(min = 5, max = 100, message = "Description size must be min 5 chars and max is 100 chars")
    private String categoryDescription;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}

