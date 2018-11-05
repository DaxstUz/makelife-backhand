//package com.makelife.server.service;
//
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @author sunyz
// * @desc
// * @create 2018-10-09 下午4:28
// */
//@Service(value = "userService")
//public class UserServiceImpl implements UserDetailsService {
//
////    @Autowired
////    private UserDao userDao;
//
//    @Override
//    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
////        User user = userDao.findByUsername(userId);
////        if(user == null){
////            throw new UsernameNotFoundException("Invalid username or password.");
////        }
//        return new org.springframework.security.core.userdetails.User("a", "a", getAuthority());
//    }
//
//    private List getAuthority() {
//        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
//    }
//
////    public List findAll() {
////        List list = new ArrayList<>();
//////        userDao.findAll().iterator().forEachRemaining(list::add);
////        return list;
////    }
//}
