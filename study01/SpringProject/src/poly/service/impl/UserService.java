package poly.service.impl;

import org.springframework.stereotype.Service;

import poly.service.IUserService;

// 이 클래스가 서비스로 사용 될 것임을 알려주는 것과 동시에 이 클래스의 서비스 별칭을 UserService로 정해줬다.
@Service("UserService")
public class UserService implements IUserService {

}
