package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String listName;
    private String author;
    @ElementCollection
    private final List<String> listItems = new ArrayList<String>();


    public ShoppingListEntity(){}

    public ShoppingListEntity(String listName, String author) {
        this.listName = listName;
        this.author = author;

    }

    //getter and setter for Name. It is possible to set a new name for the list.
    public String getListName() {
        return listName;
    }

    public void setListName(String name) {
        this.listName = listName;
    }

    //gets all the Items from the list
    public List<String> getListItems() {
        return listItems;
    }

    //adds an item to the list
    public void addListItem(String listItem) {
        listItems.add(listItem);
    }

    //removes an item from the list
    public void removeListItem(String listItem) {
        listItems.remove(listItem);
    }

    //Getter for the id. It is not possible to set a new id for the list after its constructed.
    public Long getId() {
        return id;
    }

    public void setId(Long id){}

    //Getter for the author. It is not possible to set a new author for the list after its constructed.
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author){ this.author = author;}
}
