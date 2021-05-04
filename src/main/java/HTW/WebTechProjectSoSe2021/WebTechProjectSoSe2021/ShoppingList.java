package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;

@Entity
public class ShoppingList {

    private long id;
    private final String listName;
    private final String author;
    private final List<String> listItems = new ArrayList<String>();

    public ShoppingList(String listName, String author) {
        this.listName = listName;
        this.author = author;

    }

    //getter and setter for Name. It is possible to set a new name for the list.
    public String getListName() {
        return listName;
    }

    public void setListName(String name) {
        this.name = listName;
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
    public long getId() {
        return id;
    }

    //Getter for the author. It is not possible to set a new author for the list after its constructed.
    public String getAuthor() {
        return author;
    }
}
