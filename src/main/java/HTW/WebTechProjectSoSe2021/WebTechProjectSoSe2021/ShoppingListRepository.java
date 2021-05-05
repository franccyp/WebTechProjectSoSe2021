package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends CrudRepository<ShoppingListEntity, Long> {


}
