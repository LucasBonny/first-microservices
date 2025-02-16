package com.ms.user.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ms.user.models.UserModel;
import com.ms.user.models.dto.EmailDTO;

@Component
public class UserProducer {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value(value = "${broker.queue.email.name}")
	private String routingKey;
	
	public void publishMessageEmail(UserModel userModel) {
		
		var emailDto = new EmailDTO();
		emailDto.setUserId(userModel.getId());
		emailDto.setEmailTo(userModel.getEmail());
		emailDto.setSubject("Cadastro realizado com sucesso!");
		emailDto.setSubject(userModel.getName() + ", seja bem vindo(a)! \nAgradecemos o seu cadastro, aproveite agora!");
		
		rabbitTemplate.convertAndSend("", routingKey, emailDto);
		
	}

}
