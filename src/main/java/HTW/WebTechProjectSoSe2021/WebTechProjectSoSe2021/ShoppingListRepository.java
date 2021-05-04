package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ShoppingListRepository extends CrudRepository<ShoppingList, Long> {


}
