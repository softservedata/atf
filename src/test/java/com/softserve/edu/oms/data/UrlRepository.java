package com.softserve.edu.oms.data;

public final class UrlRepository {

	private UrlRepository() {
	}

	public static enum LocalUrls {
		LOGIN("http://localhost:8080/OMS/login.htm"),
		LOGOUT("http://localhost:8080/OMS/logout.htm");
		//
		private String field;

		private LocalUrls(String field) {
			this.field = field;
		}

		@Override
		public String toString() {
			return this.field;
		}
	}

	public static enum SsuUrls {
		LOGIN("http://ssu-oms:8180/OMS/login.htm"),
		LOGOUT("http://ssu-oms:8180/OMS/logout.htm");
		//
		private String field;

		private SsuUrls(String field) {
			this.field = field;
		}

		@Override
		public String toString() {
			return this.field;
		}
	}

}
