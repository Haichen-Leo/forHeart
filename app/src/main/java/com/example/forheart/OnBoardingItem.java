package com.example.forheart;

/**
 * Class set items on welcome page
 */
public class OnBoardingItem {

    private int image;
    private String title;
    private String description;

    int getImage() {
        return image;
    }

    void setImage(int image) {
        this.image = image;
    }

    String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }
}
