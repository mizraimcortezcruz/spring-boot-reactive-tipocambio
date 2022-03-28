# spring-boot-reactive-tipocambio
Backend Challengue tipo de cambio Autor Mizraim Cortez Cruz

**Pasos para la creación de la imagen y el contenedor**

1. Creamos la red usando el comando
   - docker network create miredbcp
2. Creamos el contenedor mi-mysql8 apartir de la imagen de mysql, el nombre de nuestro contenedor será mi-mysql8
   - docker run -p 3306:3306 --name mi-mysql8 --network miredbcp -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=bdtipocambio -d mysql:8