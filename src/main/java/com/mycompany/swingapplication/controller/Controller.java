package com.mycompany.swingapplication.controller;

import com.mycompany.swingapplication.model.Food;
import com.mycompany.swingapplication.view.MainFrame;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Responsible for application logic.
 *
 * @author Sławomir Kowalczyk
 */
public class Controller implements ActionListener {

    private MainFrame mainFrame;
    private Map<JButton, Food> buttonFoodMap;
    private Food food;
    private List<String> auxiliaryListIcon;
    private int number = 0;

    /**
     * Creates a new controller witch main view (mainFrame).
     *
     * @param mainFrame Main window instance.
     */
    public Controller(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.auxiliaryListIcon = new ArrayList<>();
        auxiliaryListIcon.add("");
        buttonFoodMap = new HashMap<>();
    }

    /**
     * The method is responsible for receiving information from the main window
     * (mainFrame) and communicating with user.
     *
     * @param e Event derived from view.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton b : buttonFoodMap.keySet()) {
            if (b == e.getSource()) {
                food = buttonFoodMap.get(b);
            }
        }
    }

    /**
     * Creates a map to associate JButton and Food objects.
     *
     * @param b Jbutton object.
     * @param f Food object.
     */
    public void putButtonFoodMap(JButton b, Food f) {
        buttonFoodMap.put(b, f);
    }

    /**
     * Method responsible for searching in a list of foods.
     *
     * @param searchString Search term.
     */
    public void searchFood(String searchString) {
        mainFrame.removeAllFromPanelWithFoods();
        for (JButton b : buttonFoodMap.keySet()) {
            if (b.getText().toLowerCase().contains(searchString.toLowerCase())) {
                mainFrame.addToPanelWithFoods(b);
            }
        }
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    /**
     * The Method responsible for setting which object (with Food) is to be
     * edited.
     *
     * @param food Object to be edited.
     */
    public void setEditFood(Food food) {
        this.food = food;
    }

    /**
     * The Method responsible for editing the selected food.
     */
    public void editFood() {
        if (food != null) {
            mainFrame.showOptionWindow();
            mainFrame.setIdTF(String.valueOf(food.getId()));
            mainFrame.setNameTF(String.valueOf(food.getName()));
            mainFrame.setScientificNameTF(String.valueOf(food.getScientificName()));
        }
    }

    /**
     * This method removes the selected food from the list.
     *
     */
    public void delete() {
        for (JButton b : buttonFoodMap.keySet()) {
            if (food == buttonFoodMap.get(b)) {
                mainFrame.removeFromPanelWithFoods(b);
                buttonFoodMap.remove(b);
                food = null;
                break;
            }
        }
    }

    /**
     * The method responsible for saving Food objects to the list. If there is
     * an object it edits it, and if it does not create a new one. The elements
     * are compared by id.
     *
     * @param id Food id number.
     * @param name Food name.
     * @param scientificName Food scientific name.
     */
    public void save(int id, String name, String scientificName) {
        try {
            for (JButton b : buttonFoodMap.keySet()) {
                if (buttonFoodMap.get(b).getId() == id) {
                    int result = mainFrame.showMessageYesOrNo("Do you want to edit this food?");
                    if ((buttonFoodMap.get(b).getId() == id) && (result == 0)) {
                        Food f = buttonFoodMap.get(b);
                        f.setName(name);
                        f.setScientificName(scientificName);
                        b.setText(f.toString());
                        String pathImage = "src/main/resources/images/" + auxiliaryListIcon.get(number) + ".png/";
                        ImageIcon icon = new ImageIcon(pathImage);
                        b.setIcon(icon);
                        return;
                    }
                }
            }
            if (mainFrame.getIdTF() > 0) {
                Food food = new Food();
                food.setId(id);
                food.setName(name);
                food.setScientificName(scientificName);
                String pathImage = "src/main/resources/images/" + auxiliaryListIcon.get(number) + ".png/";
                ImageIcon icon = new ImageIcon(pathImage);

                JButton b = new JButton(food.toString(), icon);

                mainFrame.addToPanelWithFoods(b);
                buttonFoodMap.put(b, food);
            } else {
                mainFrame.showMessage("Enter valid data.");
            }
        } catch (Exception ex) {
            mainFrame.showMessage("An error occurred while saving. Restart the program.");
        }
    }

    /**
     * The method adds an icon to the buttons.
     */
    public void addIcon() {
        mainFrame.showMessage("Recommended icon parameters:\n"
                + "- size: 50x50px\n"
                + "- file format: .png");
        try {
            Frame f = new Frame();
            FileDialog fileDialog = new FileDialog(f, "Load", FileDialog.LOAD);
            fileDialog.setVisible(true);
            String pathToFile = fileDialog.getDirectory() + fileDialog.getFile();
            String savePath = "src/main/resources/images/";
            String fileName = ++number + " " + fileDialog.getFile();
            String[] array = fileName.split("\\.");
            Files.copy(Paths.get(pathToFile), Paths.get(savePath + "/" + fileName), StandardCopyOption.REPLACE_EXISTING);
            auxiliaryListIcon.add(array[0]);
        } catch (Exception ex) {
            mainFrame.showMessage("There was an error copying the file.");
        }
    }
}
