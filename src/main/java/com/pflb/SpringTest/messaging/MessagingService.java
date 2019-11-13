package com.pflb.SpringTest.messaging;

import com.pflb.SpringTest.data.entities.HistoryFile;

public interface MessagingService {
    void sendMessage(String message);
}
