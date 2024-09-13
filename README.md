# CRUD SpringBoot y Vue

API de SpringBoot que interactua con una base de datos de productos con los siguientes campos: `id`, `nombre`, `precio`, `cantidad`.

## Requisitos

- **MySQL**: Base de datos
- **Java 17**: Requerido para ejecutar Spring Boot


## Poner en marcha el proyecto

1. Clonar el repositorio
```sh
git clone https://github.com/xJesusx0/crud-springboot-vue-backend.git
```

2. Muevete al directorio donde clonaste el repositorio
```sh
cd crud-springboot-vue-backend
```

3. Crea el archivo `application.properties` en la ruta `src/main/resources`
```
mkdir src/main/resources/
touch src/main/resources/application.properties
```

4. Modifica el archivo `application.properties` con tu configuracion de conexion a mysql
```
spring.application.name=pruebatecnica
spring.datasource.url=jdbc:mysql://localhost:3306/base_de_datos
spring.datasource.username=usuario_mysql
spring.datasource.password=contraseña_mysql 
spring.jpa.hibernate.ddl-auto=update
```
**Nota**: asegurate de cambiar `usuario_mysql`, `contraseña_mysql` y `base_de_datos` con tus datps de conexion a mysql

**Nota**: asegurate de tener la sintaxis correcta
```
# NO
spring.datasource.username= root

# SI
spring.datasource.username=root
```

5. Abre el proyecto con tu IDE favorito (IntelliJ, Eclipse, VS Code, etc.)

6. Espera que se instalen las dependencias necesarias

7. Corre la aplicacion desde el IDE

> [!WARNING]
> Asegurate que mysql esté corriendo y que los datos de conexion, la base de datos exista y que el nombre de la base de datos sea correcto