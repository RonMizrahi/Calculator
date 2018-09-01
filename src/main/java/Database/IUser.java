package Database;

import Database.User.*;

public interface IUser {
	public interface IpAddress {
		public Port IpAddress(String IpAddress);
	}

	public interface Port {
		public Build Port(String Port);
	}


	public interface Build {
		public Builder Port(String UserName);
		public Builder UserName(String UserName);
		public Builder Password(String Password);
		public User build();
	}
}