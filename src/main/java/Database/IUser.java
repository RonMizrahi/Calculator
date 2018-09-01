package Database;

import Database.User.*;

public interface IUser {
	public interface IpAddress {
		public Port IpAddress(String IpAddress);
	}

	public interface Port {
		public UserName Port(String Port);
	}

	public interface UserName {
		public Password UserName(String UserName);
	}

	public interface Password {
		public Build Password(String Password);
	}

	public interface Build {
		public Builder UserName(String UserName);

		public Builder Password(String Password);

		public User build();
	}
}