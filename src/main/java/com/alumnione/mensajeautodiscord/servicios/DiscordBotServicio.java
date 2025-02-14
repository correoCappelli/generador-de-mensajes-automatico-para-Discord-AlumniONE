package com.alumnione.mensajeautodiscord.servicios;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DiscordBotServicio {

    private final JDA jda;

    public DiscordBotServicio() throws Exception {
        //el token se obtiene de Discord Developer Portal creando una nueva app

        this.jda = JDABuilder.createDefault("aqui poner el token del bot de discord")
                .setActivity(Activity.watching("mensajes programados"))
                .build();
    }

    public void mandarMensaje(String channelId, String mensaje) {
        TextChannel channel = jda.getTextChannelById(channelId);
        if (channel != null) {
            channel.sendMessage(mensaje).queue();
        }
    }
}
