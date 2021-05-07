//package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public List<UserEntity> findAll() {
//
//        var it = userRepository.findAll();
//
//        var users = new ArrayList<UserEntity>();
//        it.forEach(e -> users.add(e));
//
//        return users;
//    }
//
//    public Long count() {
//
//        return userRepository.count();
//    }
//
//    public void deleteById(Long userId) {
//
//        userRepository.deleteById(userId);
//    }
//}
