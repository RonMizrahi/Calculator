package Database;

import Database.User.*;

public interface IUser {

	public interface Build {
		public Builder UserName(String UserName);
		public Builder Password(String Password);
		public User build();
	}
}