# Support App

Support App es una aplicación de soporte técnico diseñada para gestionar solicitudes de soporte de manera eficiente. Esta aplicación consta de dos repositorios: uno para el frontend y otro para el backend.
###Funcionalidades
- Autenticación de usuarios: Los usuarios pueden registrarse e iniciar sesión en la aplicación.
- Gestión de solicitudes de soporte:
    - Crear solicitudes: Los usuarios pueden crear nuevas solicitudes de soporte detallando el problema o consulta.
    -  Editar solicitudes: Las solicitudes existentes pueden ser modificadas para actualizar información o corregir detalles.
    -  Eliminar solicitudes: Las solicitudes pueden ser eliminadas cuando ya no son necesarias.

##Repositorios

###Frontend
Repositorio: Support App Frontend

- Tecnologías utilizadas: React, Redux, Axios.
- Descripción: Interfaz de usuario donde los usuarios pueden interactuar con la aplicación. Incluye páginas de registro, inicio de sesión y gestión de solicitudes.
- Estructura del proyecto:
  -  src/components: Componentes reutilizables de la interfaz.
  -   src/redux: Gestión del estado de la aplicación.
  - src/services: Comunicación con el backend mediante llamadas API.

##Backend
Repositorio: Support App Backend

- Tecnologías utilizadas: Java, Spring Boot, MySQL.
- Descripción: API RESTful que maneja la lógica de negocio y las operaciones de la base de datos.
- Estructura del proyecto:
  -  src/main/java: Código fuente del backend.
  -  src/main/resources: Configuraciones y recursos del proyecto.
 -  pom.xml: Archivo de configuración de Maven para la gestión de dependencias.
 
 ##Configuración y Despliegue
###Requisitos previos
- Node.js y npm instalados para el frontend.
- Java y Maven instalados para el backend.
- MySQL instalado y configurado.
###Instrucciones de instalación
####Frontend
-  Clonar el repositorio: git clone https://github.com/piperiver97/piperiver97-Project-Support-App-FRONTED
- Navegar al directorio del proyecto: cd piperiver97-Project-Support-App-FRONTED
- Instalar las dependencias: npm install
- Iniciar la aplicación: npm start
####Backend
- Clonar el repositorio: git clone https://github.com/piperiver97/Project-Support-App-BACKEND
- Navegar al directorio del proyecto: cd Project-Support-App-BACKEND
- Configurar la base de datos en el archivo application.properties.
- Compilar y ejecutar el proyecto: mvn spring-boot:run
##Contribuciones
Las contribuciones son bienvenidas. Por favor, sigue los pasos siguientes para contribuir:

 - Haz un fork del repositorio.
 - Crea una nueva rama: git checkout -b feature/nueva-funcionalidad
 - Realiza tus cambios y commitea: git commit -m 'Añadir nueva funcionalidad'
 -  Empuja tus cambios a la rama: git push origin feature/nueva-funcionalidad
 - Abre un pull request.
##Licencia
Este proyecto está licenciado bajo la Licencia MIT. Ver el archivo LICENSE para más detalles.
