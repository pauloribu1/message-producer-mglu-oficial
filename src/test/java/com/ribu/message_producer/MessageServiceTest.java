package com.ribu.message_producer;

import com.ribu.message_producer.dto.ScheduleMessageDTO;
import com.ribu.message_producer.entity.Channel;
import com.ribu.message_producer.entity.Message;
import com.ribu.message_producer.entity.Status;
import com.ribu.message_producer.repository.MessageRepository;
import com.ribu.message_producer.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class MessageServiceTest {

	@Mock
	private MessageRepository messageRepository;

	@InjectMocks
	private MessageService messageService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testScheduleMessage() {
		// Given
		ScheduleMessageDTO dto = new ScheduleMessageDTO(1L, "content", "2024-11-11T10:00:00", "destination", Channel.Options.EMAIL);
		Message message = dto.toMessage();
		message.setMessageId(1L);

		when(messageRepository.save(any(Message.class))).thenReturn(message);

		// When
		Long messageId = messageService.scheduleMessage(dto);

		// Then
		assertEquals(1L, messageId);
		verify(messageRepository, times(1)).save(any(Message.class));
	}

	@Test
	void testFindById() {
		// Given
		Message message = new Message();
		message.setMessageId(1L);
		when(messageRepository.findById(1L)).thenReturn(Optional.of(message));

		// When
		Optional<Message> result = messageService.findById(1L);

		// Then
		assertTrue(result.isPresent());
		assertEquals(1L, result.get().getMessageId());
		verify(messageRepository, times(1)).findById(1L);
	}

	@Test
	void testCancelMessage() {
		// Given
		Message message = new Message();
		message.setMessageId(1L);
		message.setStatus(Status.Options.PENDING.toStatus());
		when(messageRepository.findById(1L)).thenReturn(Optional.of(message));

		// When
		messageService.cancelMessage(1L);

		// Then
		assertEquals("canceled", message.getStatus().getDescription());
		verify(messageRepository, times(1)).save(message);
	}

	@Test
	void testMarkAsSent() throws InterruptedException {
		// Given
		Message message = new Message();
		message.setMessageId(1L);
		message.setStatus(Status.Options.PENDING.toStatus());
		when(messageRepository.findById(1L)).thenReturn(Optional.of(message));

		// When
		messageService.markAsSent(1L);


		// Then
		Thread.sleep(100);
		assertEquals("sucess", message.getStatus().getDescription());
		verify(messageRepository, times(1)).save(message);
	}
}
