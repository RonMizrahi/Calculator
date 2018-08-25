package Database;

import Database.IUser.*;

public final class User {

	private final String ipaddress;
	private final String port;
	private final String username;
	private final String password;

	private User(Builder builder) {
		ipaddress = builder.ipaddress;
		port = builder.port;
		username = builder.username;
		password = builder.password;

	}

	public String getIpaddress() {
		return ipaddress;
	}

	public String getPassword() {
		return password;
	}

	public String getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	public String toString() {
		return "ipaddress Name: " + ipaddress + ", port: " + port + ", username: " + username + ", password: "
				+ password;
	}

	public static IpAddress builder() {
		return new Builder();
	}

	public static class Builder implements IpAddress, Port, UserName, Password, Build {

		private String ipaddress;
		private String port;
		private String username;
		private String password;

		@Override
		public Port IpAddress(String ipaddress) {
			this.ipaddress = ipaddress;
			return this;
		}

		@Override
		public UserName Port(String port) {
			this.port = port;
			return this;
		}

		@Override
		public Password UserName(String username) {
			this.username = username;
			return this;

		}

		@Override
		public Build Password(String password) {
			this.password = password;
			return this;
		}

		@Override
		public User build() {
			return new User(this);
		}

	}

}
