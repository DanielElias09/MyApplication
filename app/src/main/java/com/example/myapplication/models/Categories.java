package com.example.myapplication.models;

import java.util.ArrayList;
import java.util.List;

public class Categories {

    List<Category> categories;

    public Categories() {
        categories = new ArrayList<>();
        categories.add(new Category("Pies", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ3MBtzVTD80EW6BCl6OIpu8SEIWr2YCCulWw&usqp=CAU"));
        categories.add(new Category("Pasta", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTLw_PSW34qAhOSBDOnQG-wT5jOAhL_kBHEEg&usqp=CAU"));
        categories.add(new Category("Meats", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQJKSekUt627CpwwCLMZRXbb9tWBFs4Vzpuzg&usqp=CAU"));
        categories.add(new Category("Salads", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTiLiFZb5sVONAPMWWYhiwp8BWwy2_spN98Fg&usqp=CAU"));
        categories.add(new Category("Drinks", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTNMmg_q6YPD2ILctdX_1G46WmBqn8WcdgZzg&usqp=CAU"));
        categories.add(new Category("Desserts", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSftqgfIU4MEY18IdBW5Zf1WTXLtvcBKAQAAA&usqp=CAU"));
    }

    public List<String> getAllImages(){
        ArrayList<String> allImages = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++)
            allImages.add(categories.get(i).getImagePath());
        return allImages;
    }

    public List<String> getAllNames(){
        ArrayList<String> allnames = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++)
            allnames.add(categories.get(i).getName());
        return allnames;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    class Category{
        private String name;
        private String imagePath;

        public Category(String name, String imagePath) {
            this.name = name;
            this.imagePath = imagePath;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }
    }
}
