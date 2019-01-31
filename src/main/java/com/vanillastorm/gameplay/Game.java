package com.vanillastorm.gameplay;

import com.vanillastorm.telegram.TransilvaniaProjectBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Game {

    public Game() {
        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new TransilvaniaProjectBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}