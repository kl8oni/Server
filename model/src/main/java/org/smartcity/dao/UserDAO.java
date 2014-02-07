package org.smartcity.dao;


import org.smartcity.entity.Email;
import org.smartcity.entity.User;

public interface UserDAO {

	User createUser( String lastName, String firstName, String middleName, String nickName, String password );

	void removeUser( User user );

	Email createUserEmail(
			String lastUserName,
			String firstUserName,
			String middleUserName,
			String nickUserName,
			String userPassword,
			String emailAddress,
			Boolean mainEmail );

	Email createUserEmail( String emailAddress, User user );

	Email createMainUserEmail( String emailAddress, User user );

	void removeUserEmail( Email email );

}
