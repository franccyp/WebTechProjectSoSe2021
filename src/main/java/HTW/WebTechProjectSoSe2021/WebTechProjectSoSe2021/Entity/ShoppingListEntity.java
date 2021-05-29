package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shopping_list")
public class ShoppingListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long list_id;

    @Column(name = "list_name", nullable = false)
    private String list_name;

    @Column(name = "author", nullable = false)
    private String author;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "list_id")
    private final List<ItemEntity> listItems = new ArrayList<ItemEntity>();


    public ShoppingListEntity(String list_name, String author) {
        this.list_name = list_name;
        this.author = author;
    }

    public ShoppingListEntity() {

    }

    //Getter for the id. It is not possible to set a new id for the list after its constructed.
    public Long getList_id() {
        return list_id;
    }

    //getter and setter for Name. It is possible to set a new name for the list.
    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    //Getter for the author. It is not possible to set a new author for the list after its constructed.
    public String getAuthor() {
        return author;
    }

    //we dont need this???
    public void setAuthor(String author) {
        this.author = author;
    }

    //get all items in a list
    public List<ItemEntity> getListItems() {
        return listItems;
    }

    //adds a list Item only through name
    public void addListItem(String itemName) {
        ItemEntity listItem = new ItemEntity(itemName);
        listItems.add(listItem);
    }

    //adds a list Item
    public void addListItem(ItemEntity item) {
        listItems.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingListEntity shoppinglist = (ShoppingListEntity) o;
        return Objects.equals(list_id, shoppinglist.list_id) &&
                Objects.equals(list_name, shoppinglist.list_name) &&
                Objects.equals(author, shoppinglist.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list_id, list_name, author);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("shopping_list{");
        sb.append("ID=").append(list_id);
        sb.append(", list_name='").append(list_name).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append('}');
        return sb.toString();
    }
}