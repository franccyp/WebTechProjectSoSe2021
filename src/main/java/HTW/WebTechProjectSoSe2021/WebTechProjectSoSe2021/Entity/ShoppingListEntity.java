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
    private Long id;

    @Column(name = "list_name", nullable = false)
    private String list_name;

    @Column(name = "author", nullable = false)
    private String author;


    @OneToMany
    private final List<ListItemEntity> listItems = new ArrayList<ListItemEntity>();


    public ShoppingListEntity(String list_name, String author) {
        this.list_name = list_name;
        this.author = author;
    }

    public ShoppingListEntity() {

    }

    //Getter for the id. It is not possible to set a new id for the list after its constructed.
    public Long getId() {
        return id;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingListEntity shoppinglist = (ShoppingListEntity) o;
        return Objects.equals(id, shoppinglist.id) &&
                Objects.equals(list_name, shoppinglist.list_name) &&
                Objects.equals(author, shoppinglist.author);
    }

    public List<ListItemEntity> getListItems() {
        return listItems;
    }

    //adds a list Item
    public void addListItem(String itemName) {
        ListItemEntity listItem = new ListItemEntity(itemName, id);
        listItems.add(listItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, list_name, author);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("shopping_list{");
        sb.append("ID=").append(id);
        sb.append(", list_name='").append(list_name).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
