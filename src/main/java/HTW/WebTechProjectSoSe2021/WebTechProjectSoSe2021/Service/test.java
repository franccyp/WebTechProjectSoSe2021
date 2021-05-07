//package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import javax.transaction.Transactional;
//
//@Component
//public class test implements CommandLineRunner {
//
//    private static final Logger logger = LoggerFactory.getLogger(test.class);
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//
//        logger.info("initializing users");
//
//        var u1 = new UserEntity("Paul", "Smith", "paul.smith@gmail.com", "paulSmith");
//        userRepository.save(u1);
//
//        var u2 = new UserEntity("Robert", "Black", "rb34@gmail.com", "rBblack");
//        userRepository.save(u2);
//
//        var u3 = new UserEntity("John", "Doe", "jdoe@gmail.com", "jdOE");
//        userRepository.save(u3);
//    }
//}
