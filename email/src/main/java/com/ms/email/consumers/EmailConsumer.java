package com.ms.email.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ms.email.models.dto.EmailRecordDTO;

@Component
public class EmailConsumer {

	@RabbitListener(queues = "${broker.queue.email.name}")
	public void listenEmailQueue(@Payload EmailRecordDTO emailRecordDTO) {
		System.out.println(emailRecordDTO);
	}
}
