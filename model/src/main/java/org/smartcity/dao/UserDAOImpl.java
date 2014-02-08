package org.smartcity.dao;


import org.smartcity.entity.Email;
import org.smartcity.entity.User;

import java.math.BigInteger;

public class UserDAOImpl
		extends AbstractEmbeddableEntityDAO
		implements UserDAO,
				   EmbeddableEntityDAO {

	@Override
	public User createUser(
			String lastName,
			String firstName,
			String middleName,
			String nickName,
			String password ) {
		return null;
	}

	@Override
	public void removeUser( User user ) {

	}

	@Override
	public Email createUserEmail(
			String lastUserName,
			String firstUserName,
			String middleUserName,
			String nickUserName,
			String userPassword,
			String emailAddress,
			Boolean mainEmail ) {
		return null;
	}

	@Override
	public Email createUserEmail( String emailAddress, User user ) {
		return null;
	}

	@Override
	public Email createMainUserEmail( String emailAddress, User user ) {
		return null;
	}

	@Override
	public void removeUserEmail( Email email ) {

	}

	@Override
	public User findUser( BigInteger userID ) {
		return null;
	}

	@Override
	public Email findEmail( BigInteger emailID ) {
		return null;
	}

}
