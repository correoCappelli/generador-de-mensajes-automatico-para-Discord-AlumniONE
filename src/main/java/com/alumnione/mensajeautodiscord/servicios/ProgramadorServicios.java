package com.alumnione.mensajeautodiscord.servicios;

import com.alumnione.mensajeautodiscord.modelos.Mensaje;
import com.alumnione.mensajeautodiscord.repositorios.MensajeRepositorio;
import com.alumnione.mensajeautodiscord.validaciones.IValidacionesMensajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ProgramadorServicios {
@Autowired
    DiscordBotServicio discordBotServicio;

    @Autowired
    private MensajeRepositorio mensajeRepositorio;


    //channel id bot-test 1338970896563441684
    @Scheduled(cron = "0 0/5 * * * ?") //cada cinco minutos chequea los mensajes
    public void enviarMensajesProgramados() {

        LocalDateTime ahoraMenosCincoMinutos = LocalDateTime.now().minusMinutes(5);
        //List<Mensaje> mensajes = mensajeRepositorio.findAllByFechaYHora(LocalDateTime.now());
        List<Mensaje> mensajes = mensajeRepositorio.findAll();
        for (Mensaje mensaje : mensajes) {
            if(mensaje.getFechaYHora().isAfter(ahoraMenosCincoMinutos) && mensaje.getFechaYHora().isBefore(LocalDateTime.now())) {
                System.out.println("mensaje enviado" + mensaje.getTexto());
                //discordBotServicio.mandarMensaje(mensaje.getChannelId(), mensaje.getTexto());
                discordBotServicio.mandarMensaje("<aqui poner el channel id de discord>", mensaje.getTexto());
            }
        }
    }

}
