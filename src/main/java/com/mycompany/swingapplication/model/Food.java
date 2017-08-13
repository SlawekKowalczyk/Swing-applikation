package com.mycompany.swingapplication.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Sławomir Kowalczyk
 */
public class Food {

    private int id;
    private String name;
    private String scientificName;
    private List<Food> foods;
    private List<String> tagsList;
    private List<String> allTagsList;
    private List<String> iconList;

    /**
     * Fill out an example list of food and tags.
     */
    public Food() {
        this.foods = new ArrayList<>();
        this.allTagsList = new ArrayList<>();
        this.iconList = new ArrayList<>();

        Food food_1 = new Food(1, "Tomato", "Solanum L. section Lycopersicon");
        Food food_2 = new Food(2, "Cucumber", "Cucumis");
        Food food_3 = new Food(3, "Pepper", "Capsicum");
        Food food_4 = new Food(4, "Radish", "Raphanus sativus var. sativus");
        Food food_5 = new Food(5, "Marrow", "Cucurbita pepo convar. giromontiina");
        Food food_6 = new Food(6, "Kohlrabi", "Brassica oleracea var. gongylodes L");
        Food food_7 = new Food(7, "Garlic common", "Allium sativum");
        Food food_8 = new Food(8, "Lettuce", "Lactuca ");
        Food food_9 = new Food(9, "Cabbage", "Brassica oleracea L. var. sabauda L.");
        Food food_10 = new Food(10, "Spinach", "Spinacia");
        Food food_11 = new Food(11, "Carrot", "Daucus carota L.");
        Food food_12 = new Food(12, "Pea", "Pisum sativum L.");
        Food food_13 = new Food(13, "Cauliflower", "Brassica oleracea L. var.botrytis L.");
        Food food_14 = new Food(14, "Potato", "Solanum tuberosum L.");
        foods.add(food_1);
        foods.add(food_2);
        foods.add(food_3);
        foods.add(food_4);
        foods.add(food_5);
        foods.add(food_6);
        foods.add(food_7);
        foods.add(food_8);
        foods.add(food_9);
        foods.add(food_10);
        foods.add(food_11);
        foods.add(food_12);
        foods.add(food_13);
        foods.add(food_14);

        allTagsList.add("carbohydrate");
        allTagsList.add("vitamin E");
        allTagsList.add("protein");
        allTagsList.add("vitamin A");
        allTagsList.add("vitamin D");
        allTagsList.add("vitamin K");
        allTagsList.add("iron");
        allTagsList.add("vitamin B");
        allTagsList.add("magnesium");
        allTagsList.add("calcium");
        allTagsList.add("vitamin C");
        sortList(allTagsList);

        for (int i = 0; i < images.length; i++) {
            iconList.add(images[i]);
        }
    }

    public List<String> getIconList() {
        return iconList;
    }

    /**
     * Creates a Food object.
     *
     * @param id Id number.
     * @param name Food name.
     * @param scientificName Scientific name.
     */
    public Food(int id, String name, String scientificName) {
        this.id = id;
        this.name = name;
        this.scientificName = scientificName;
        this.tagsList = new ArrayList<>();
    }
    /**
     * List of vegetable names.
     */
    private String[] images = {"Tomato", "Cucumber", "Pepper", "Radish", "Marrow",
        "Kohlrabi", "Garlic common", "Lettuce", "Cabbage", "Spinach", "Carrot",
        "Pea", "Cauliflower", "Potato"};

    /**
     * The method sorts the list of Strings alphabetically.
     *
     * @param list A list to be sorted.
     */
    private void sortList(List<String> list) {
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
    }

    /**
     *
     * @return Returns id number.
     */
    public int getId() {
        return id;
    }

    /**
     * The method sets the id number.
     *
     * @param id Id number.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return Returns food name.
     */
    public String getName() {
        return name;
    }

    /**
     * The method sets the name.
     *
     * @param name Food name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return Return scientific name.
     */
    public String getScientificName() {
        return scientificName;
    }

    /**
     * The method sets the scientific name.
     *
     * @param scientificName Scientific name.
     */
    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    /**
     * The method download a list of all available tags.
     *
     * @return Returns all list of tags.
     */
    public List<String> getAllTagsList() {
        return allTagsList;
    }

    /**
     * Adds a String to the tag list.
     *
     * @param tag Tag name.
     */
    public void addToTagsList(String tag) {
        tagsList.add(tag);
    }

    /**
     * Sets a list of tags.
     *
     * @param tagsList List of tags.
     */
    public void setTagsList(List<String> tagsList) {
        this.tagsList = tagsList;
    }

    /**
     *
     * @return Returns tags list.
     */
    public List<String> getTagsList() {
        return tagsList;
    }

    /**
     * The method gets a list of food.
     *
     * @return Returns foods list.
     */
    public List<Food> getFoods() {
        return foods;
    }

    /**
     *
     * @param allTagsList List of all tags.
     */
    public void setAllTagsList(List<String> allTagsList) {
        this.allTagsList = allTagsList;
    }

    /**
     *
     * @return Returns the image name.
     */
    public String[] getImages() {
        return images;
    }

    @Override
    public String toString() {
        return id + ". " + name + ", " + scientificName;
    }

}
