package in.sp.main.services;

import in.sp.main.entities.User;

public interface UserService
{
  public boolean registerUser(User user);
  public User LoginUser(String email,String Password);

}
