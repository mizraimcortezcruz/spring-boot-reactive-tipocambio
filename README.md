# spring-boot-reactive-tipocambio
Backend Challengue tipo de cambio Autor Mizraim Cortez Cruz

**Pasos previos a la creación de la imagen y el contenedor**

1. En el archivo \spring-boot-reactive-tipocambio\src\main\resources\application.properties en la propiedad spring.r2dbc.url en el nombre del servidor actualizarlo por mi-mysql8bcp quedando de la siguiente manera:
   - **spring.r2dbc.url=r2dbc:pool:mysql://mi-mysql8bcp:3306/bdtipocambio**
2. En la raíz del proyecto spring-boot-reactive-tipocambio ejecutar el comando de maven:
   - comando: **mvn clean package -DskipTests**
3. Crear el archivo Dockerfile en la raíz del proyecto spring-boot-reactive-tipocambio con el siguiente contenido:
     FROM openjdk:11
	 EXPOSE 6004
	 ADD ./target/spring-boot-reactive-tipocambio*.jar micro-tipocambio.jar
	 ENTRYPOINT ["java","-jar","/micro-tipocambio.jar"]
	 <pre><code>chunk1
	 chunk2
	 chunk3
	 </code></pre>

**Pasos para la creación de la imagen y el contenedor**

1. Creamos la red usando el comando.
   - comando: **docker network create miredbcp**
2. Creamos el contenedor mi-mysql8bcp apartir de la imagen de mysql versión 8, el nombre de nuestro contenedor será mi-mysql8bcp.
   - comando: **docker run -p 3306:3306 --name mi-mysql8bcp --network miredbcp -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=bdtipocambio -d mysql:8**
3. Creamos la imagen de nuestro microservicio para ello tenemos que ubicarnos en la raíz de nuestro proyecto spring-boot-reactive-tipocambio, nuestra imagen tendrá el nombre ms-tipocambio:v1 y se creará apartir del jar referenciado en el archivo Dockerfile ubicado en la raíz de nuestro proyecto spring-boot-reactive-tipocambio.
   - comando: **docker image build -t ms-tipocambio:v1 -f Dockerfile .**
4. Creamos el contenedor micro-tipocambio apartir de la imagen ms-tipocambio:v1
   - comando: **docker run -p 6004:6004 --name micro-tipocambio --network miredbcp ms-tipocambio:v1**
   