package com.saga.auth.nats.subject;

public class NatsSubjects {

	private NatsSubjects() {}

	public static final String USER_DELETION_REQUEST = "user.deletion.request";
	public static final String USER_DELETION_COMPLETED = "user.deletion.completed";
	public static final String USER_DELETION_FAILED = "user.deletion.failed";
}
