package com.ms.email.models.dto;

import java.util.UUID;

public record EmailRecordDTO(UUID userId, String emailTo, String subject, String text) {

	@Override
	public String toString() {
		return "EmailRecordDTO [userId=" + userId + ", emailTo=" + emailTo + ", subject=" + subject + ", text=" + text
				+ "]";
	}

}
