package Database;

public interface IUser {
	public interface IpAddress {
		public Port IpAddress(String IpAddress); // first
	}

	public interface Port {
		public UserName Port(String Port); // last
	}

	public interface UserName {
		public Password UserName(String UserName); // age
	}

	public interface Password {
		public Build Password(String Password); // age
	}
	
	public interface Build {
		public User build();
	}
}