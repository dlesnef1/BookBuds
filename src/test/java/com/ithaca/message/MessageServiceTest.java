package com.ithaca.message;

import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * Created by David on 4/5/16.
 */
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;
    @InjectMocks
    private MessageServiceImpl messageService;
}
