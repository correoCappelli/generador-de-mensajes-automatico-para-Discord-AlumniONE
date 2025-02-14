
# URL BASE

> URL ----  /localhost:PORT/api/

> PORT ----  por defecto 8080


## Introduccion

Es un generador de mensajes automaticos para Discord. Cada 5 minutos chequea el timestamp de los mensajes en la base de datos y los envia. Tambien posee una panel web servida en la carpeta **static** que muestra los proximos mensajes a enviar

*Comite-ONE proyecto ALUMNIONE Backend*

## Instalacion

*  Crear la base de datos en MySQL u otro workbench de SQL
   
 > puede realizarse en la terminal ingresando

 > mysql -u root -p
 
 > create database ***mensajeria*** 

* Setear las variables de usuario / contrasenia de la base de datos MySQL en 
> application.properties
* Crear el Bot de Discord :

Paso 1: Accede al Portal de Desarrolladores de Discord
Inicia sesión en Discord: Abre Discord e inicia sesión con tu cuenta.

Accede al Portal de Desarrolladores: Ve a https://discord.com/developers y inicia sesión con la misma cuenta de Discord.

Paso 2: Crea una nueva aplicación
Crear una nueva aplicación: Haz clic en "New Application" y dale un nombre a tu aplicación. Luego, haz clic en "Create Application".

Configura tu aplicación: En la pestaña "OAuth2", marca la casilla "Bot" y luego haz clic en "Add Bot". Esto creará tu bot y te dará un token de bot.

Paso 3: Obtén el token del bot
Copiar el token: En la pestaña "Bot", verás el token de tu bot. Haz clic en "Copy" para copiarlo.

Guardar el token: Guarda el token en un lugar seguro, ya que lo necesitarás para autenticar tu bot.

* Crear un servidor de Discord para utilizar el Bot y obtener un **channel id**

Paso 1: Crear un nuevo servidor
Inicia sesión en Discord: Abre Discord e inicia sesión con tu cuenta.

Crear un nuevo servidor: Haz clic en el ícono "+" en el panel izquierdo y selecciona "Crear un servidor". Elige una plantilla o crea una personalizada, da un nombre al servidor y haz clic en "Crear".

Paso 2: Habilitar el modo de desarrollador
Accede a la configuración de usuario: Haz clic en el ícono de engranaje junto a tu nombre de usuario en la parte superior derecha.

Activa el modo de desarrollador: En la sección "Ajustes de la aplicación", busca la opción "Modo de desarrollador" y actívalo.

Paso 3: Obtener el ID del canal
Crear un canal: En tu servidor, haz clic en el ícono "+" junto a "Texto" o "Voz" para crear un nuevo canal.

Copiar el ID del canal: Haz clic derecho sobre el nombre del canal y selecciona "Copiar ID". El ID del canal se copiará al portapapeles.


* Registrar el BOT con permisos de Administrador

Paso 1: Accede al Portal de Desarrolladores de Discord
Inicia sesión en Discord: Abre Discord e inicia sesión con tu cuenta.

Accede al Portal de Desarrolladores: Ve a https://discord.com/developers y accede con la misma cuenta de Discord.

Paso 2: Configurar tu aplicación de bot
Crear una nueva aplicación: Haz clic en "New Application" y dale un nombre a tu aplicación. Luego, haz clic en "Create".

Configurar el bot: En la barra lateral, selecciona "Bot" y haz clic en "Add Bot". Sigue las instrucciones para crear tu bot y copia el token del bot para más tarde.

Paso 3: Generar el enlace de invitación del bot
Ir a OAuth2: En la barra lateral, selecciona "OAuth2" y luego "URL Generator".

Seleccionar permisos:

En la sección "SCOPES", selecciona "bot".

En la sección "BOT PERMISSIONS", selecciona "Administrator". Esto permitirá que el bot tenga todos los permisos necesarios.

Generar URL: Copia el enlace generado en la parte inferior.

Paso 4: Invitar el bot a tu servidor
Usar el enlace: Pega el enlace en tu navegador y accede a él.

Seleccionar servidor: Elige el servidor al que deseas añadir el bot (necesitas tener permisos de administrador en ese servidor).

Autorizar el bot: Haz clic en "Authorize" para darle permisos de administrador a tu bot.

Paso 5: Verificar el bot en el servidor
Abrir Discord: Ve a tu servidor de Discord y verifica que el bot ha sido añadido correctamente.

Asignar permisos adicionales si es necesario: Aunque ya debería tener permisos de administrador, puedes verificar y ajustar permisos específicos del bot en los ajustes de rol de tu servidor.
 
### Agregar el token del Bot y el channel id de mi canal a los servicios de Spring

* TOKEN BOT

```java
public DiscordBotServicio() throws Exception {
//el token se obtiene de Discord Developer Portal creando una nueva app

        this.jda = JDABuilder.createDefault("aqui poner el token del bot de discord")
                .setActivity(Activity.watching("mensajes programados"))
                .build();
    }
```
    
* CHANNEL ID

```java
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
```
    
## EndPoints

### catalogo topicos y respuestas y autores

|PETICION | URL                                    | DESCRIPCION                       |
--- |----------------------------------------|-----------------------------------|
|POST| http://localhost:8080/api/mensajes     | ruta para postear nuevos mensajes |
|GET| http://localhost:8080/api/mensajes     | ruta para listar mensajes         |
|DELETE| http://localhost:8080/api/mensajes/:id | borrar un mensaje por su ID       |

### swagger

|PETICION | URL                                            | DESCRIPCION                                     |
--- |------------------------------------------------|-------------------------------------------------|
|GET| http://localhost:8080/swagger-ui/index.html | ruta para visualizar la Swagger UI de SpringDoc |

### panel web servido estaticamente (/resources/static/index.html)

|PETICION | URL                                            | DESCRIPCION                                           |
--- |------------------------------------------------|-------------------------------------------------------|
|GET| http://localhost:8080/ | muestra los mensajes a enviar en los proximos minutos |

formato JSON para POST de nuevos mensajes :

```json
{
  "texto": "texto del mensaje de Discord",
  "fechaYHora": "2025-02-14T13:22:50.267Z"
}
```


## Dependencias instaladas

```XML
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
  </dependency>
  <!-- https://mvnrepository.com/artifact/net.dv8tion/JDA -->
  <dependency>
    <groupId>net.dv8tion</groupId>
    <artifactId>JDA</artifactId>
    <version>5.2.2</version>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
  </dependency>

  <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
  </dependency>

  <dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
  </dependency>
  <dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-mysql</artifactId>
  </dependency>
  <dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
  </dependency>
  <dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
  </dependency>
  <dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.8.4</version>
  </dependency>
</dependencies>
```

### Validaciones SOLID

```java
@Component
public class ValidadorFechaDelMensaje implements IValidacionesMensajes {


    @Override
    public void validar(DatosRegistroMensaje mensaje) {
        if(!(mensaje.fechaYHora() instanceof LocalDateTime)){
            throw new ValidacionExcepcion("revisar fecha y hora ingresada");
        }
    }
}
```

```java
@Component
public class ValidadoTextoDelMensaje implements IValidacionesMensajes{
    @Override
    public void validar(DatosRegistroMensaje mensaje) {
        String textoMensaje= mensaje.texto();
        if(textoMensaje.length()>300){
            throw new ValidacionExcepcion("no se pueden superar los 300 chars");
        }

    }
}
```