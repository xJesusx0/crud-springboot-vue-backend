# CRUD SpringBoot y Vue

API de SpringBoot que interactua con una base de datos de productos con los siguientes campos: `id`, `nombre`, `precio`, `cantidad`.

## Requisitos

- **MySQL**: Base de datos
- **Java 17**: Requerido para ejecutar Spring Boot

## Endpoints

### GET /productos

- **Ruta:**         /productos
- **Metodo:**       GET
- **Descripcion**   Devuelve un array con todos los productos 
### GET /productos/{id}

- **Ruta:**         /productos/{id}
- **Metodo:**       GET
- **Descripcion**   Devuelve la información de un producto basado en su id

#### Posibles respuestas
##### 200 - Producto encontrado
```
{
	"data": {
		"id": 6,
		"nombre": "Pc de escritorio",
		"precio": 400.0,
		"cantidad": 10
	},
	"message": "Producto encontrado",
	"success": true
}
```

##### 404 - Producto no encontrado
```
{
  "data": null,
  "message": "Producto no encontrado",
  "success": false
}
```

### POST /productos

- **Ruta:**         /productos
- **Metodo:**       POST
- **Descripcion**   Inserta un nuevo producto en la base de datos

#### Cuerpo de la solicitud
```
{
	"nombre":"Pc de escritorio",
	"precio":400,
	"cantidad":10
}
```
#### Posibles respuestas

##### 200 - Producto insertado correctamente
```
{
	"data": null,
	"message": "Producto guardado correctamente",
	"success": true
}
```
##### 400 - Los datos enviados en el cuerpo son invalidos
```
{
  "data": null,
  "message": "Producto invalido",
  "success": false
}
```

##### 500 - En caso de que haya un nombre duplicado

##### 500 - Otro error interno del servidor 
```
{
  "data": null,
  "message": "Ha ocurrido un error al insertar el producto",
  "success": false
}
```

### PUT /productos/{id}

- **Ruta:**         /productos/{id}
- **Metodo:**       PUT
- **Descripcion**   Actualiza un producto

#### Cuerpo de la solicitud
```
{
	"nombre":"Pc de escritorio",
	"precio":400,
	"cantidad":10
}
```
#### Posibles respuestas

##### 200 - Producto actualizado correctamente
```
{
	"data": {
		"id": 6,
		"nombre": "Pc de escritorio",
		"precio": 400.0,
		"cantidad": 10
	},
	"message": "Producto actualizado correctamente",
	"success": true
}
```

##### 404 - Producto no encontrado
```
{
  "data": null,
  "message": "Producto no encontrado",
  "success": false
}
```

##### 400 - No se envio id en la peticion
```
{
  "data": null,
  "message": "El parámetro id no debe estar vacío",
  "success": false
}
```

##### 400 - Los datos enviados en el cuerpo son invalidos
```
{
  "data": null,
  "message": "Producto invalido",
  "success": false
}
```
##### 500 - En caso de que haya un nombre duplicado

### DELETE /productos/{id}

- **Ruta:**         /productos/{id}
- **Metodo:**       DELETE
- **Descripcion**   Elimina el producto con el id pasado en la url

#### Posibles respuestas

##### 200 - Producto eliminado correctamente
```
{
  "data": null,
  "message": "Producto eliminado correctamente",
  "success": true
}
```

##### 400 - No se envio id en la peticion
```
{
  "data": null,
  "message": "El parámetro id no debe estar vacío",
  "success": false
}
```

##### 404 - No se encontro el producto a eliminar
```
{
  "data": null,
  "message": "Producto no encontrado",
  "success": false
}
```

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
```sh
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

> [!WARNING]
> En caso de tener un error de que el puerto 8080 está en uso agrega esta linea en el archivo application.properties
````
server.port=8081
```
Asegurate de configurar el frontend para que haga las peticiones en el puerto correcto