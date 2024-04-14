# N-Capas | Repaso Parcial 1

## Programación por capas

El objetivo de la programación por capas es separar la lógica de negocios de la lógica de diseño.

## Concepto de desarrollo Web

### Web App

* Accedido desde computadoras -> Navegadores
* Interacción con el usuario
* Alojada en un servidor y procesador por un servidor web
* Comunicación con servicios

### Servidor Web

* Software
* Procesa Peticiones HTTP (HyperText Transfer Protocol)
    * Hipertexto
        * texto plano
        * Enriquecimiento

#### Tipos de Servidores

- __SSH (Secure Shell)__: Servidor carente de interfaz gráfica. Es un protocolo que se utiliza en el manejo de servidores de forma remota. Toda la información viaja de forma __encriptada__, lo que lo convierte en uno de los medios más seguros.

- __FTP (File Transfer Protocol)__: Servicio utilizado para enviar y obtener archivos entre dos equipos remotos. Los casos más usuales son transferencias entre equipo local de un cliente y el servidor del proveedor.

- __DNS (Domain Name System):__ Software de servidor que utiliza una base de datos DNS para responder consultas relativas al DNS.

## Algoritmo de búsqueda de un navegador web

1. Pedir URL (Uniform Resource Locator).
2. Enviar al DNS la IP \[x.x.x.x/y\].
3. Enviar IP al servidor.
4. Petición HTTP (Web) -> respuesta.
5. Renderizar según el tipo de response.
    - Texto -> HTML / CSS / JS.
    - HTML -> render().

URL != URI

URI: Uniform Resource Identifier

## Peticiones - Respuestas

### Request

* URI
* Headers (Enriquecimiento)
* Method (HTTP Verb)
* Body (Puede no ser enviado, se utiliza mayormente en peticiones de tipo POST/PUT/PATCH)

### Response
* Status Code
* Headers
* Body

### HTTP Verbs

* GET
* PUT
* POST
* PATCH
* DELETE
* OPTIONS

### HTTP Status Code 

* 200 (OK = Request Succeeded) 
* 400 (Bad Request por mala sintaxis o mal formato)
* 500 (Internal Server Error = Error inesperado por parte del servidor)

## Arquitectura de Software

Un arquitecto de software es aquel que dicta el cómo hacer las cosas. Diseñan el software a medida del cliente

Características de un arquitecto de software:

* Conocimiento amplio de software
* Capacidad de resolución de problemas
* Creatividad
* Habilidades blandas (Comunicación)
* Liderazgo
* Conocimiento previo del negocio

## Capas de estructura de aplicación

### Interfaz de usuario (User Interface - UI)

Capa con la cual el usuario interactúa. Se dan inputs e instrucciones mediante esta capa y el resutlado también se muestra en ella.

### Capa de Lógica de Negocios

Esta capa contiene todos los modelos y lógcas que proporcionan la funcionalidad a la aplicación. También actúa como <ins>intermediario</ins> entre la __interfaz de usuario (UI)__ y la __capa de base de datos (DB)__.

### Capa de Backend o Base de Datos (DB)

Esta capa almacena todos los datos que el software necesita y los datos que recibe después de la manipulación.

Cada vez que se da alguna intrcucción por parte del usuario en la capa de __interfaz de usuario__, la __capa de negocios__ utiliza sús logicas predefinidas para que la manipulación de la __base de datos__ se pueda realizar y se devuelva la salida en la <ins>interfaz de usuario</ins>

## Tipos de Arquitectura

### 1 Tier Architecture (Single-tier architecture)

Todos los componentes requeridos para el trabajo de la aplicación están en el mismo paquete. Tanto la UI como la lógica de negocios se acceden desde el mismo cliente local. Tanto el cliente como el servidor residen en el mismo dispositivo.

Esta capa es adecuada para una aplicación web, ya que solo puede acceder a la data disponible en un solo computador o servidor

#### Ventajas

* __Fácil Desarrollo:__ Desarrollo sencillo por falta de complejidad ya que todos los componentes están integrados en una sola etapa, lo que se traduce como fácil navegación y modificaciones de código
* __Bajo Costo:__ La falta de implementar cambios y capas adicionales puede disminuir costos, así como ejecutar todo en hardware menos potente.
* __Rápido acceso:__ Tanto la UI como la lógica están juntas, por lo que acceder se realiza de manera inmediata y sin intermediarios.

#### Desventajas

* __Alto acoplamiento de componentes:__ Todos los componentes están interconectados y dependen uno de otros para funcionar correctamente (Dificultad para el mantenimiento, escalabilidad limitada, pruebas complicadas).
* __Viabilidad empresarial nula:__ Falta de flexibilidad y adaptabilidad del sistema. Dificultad para hacer ambios que puede que lleven a que el sistema no pueda evolucionar con el negocio.
* __Baja escalabilidad:__ Por el hecho de la falta de separación entre los componentes puede ser difícil escalar partes específicas del sistema.
* __Documentación excesiva:__ Puede resultar de documentar en detalle cómo todos los componentes funcionan entre sí.
* Puede ser usado por un usuario a la vez.

### 2 Tier architecture

La capa de UI y la de lógica están separadas en dos computadoras distintas. El cliente da la instrucción para que el servidor, que contiene toda la data, provea un resultado o haga cambio en la data ya existente. La lógica de negocios está presente en la computadora que contiene el servidor. Se hace uso del Internet, Transfer Control Protocol (TCP) e Internet Protocol (IP) para comunicarse entre ambas capas.

#### Ventajas

* __Fácil desarrollo:__ Al ser un nivel básico de separación, es más fácil enfocarse en áreas específicas del sistema. Facilita la prueba y depuración.
* __Permite separar los datos de la vista:__ Facilita la gestión y manipulación. Al haber un cambio, este puede hacerse sin afectar a la otra capa.
* __Más o menos escalable:__ Si la capa de datos está bien estructurada, puede escalar horizontalmente y manejar carga de datos crecientes. Bajo acoplamiento entre componentes. Facilita el cambio de características.

#### Desventajas

* __Problemas en el despliegue:__ Pueden haber actualizaciones en ambas capas. Además, si ambas capas están altamentes interconectadas entre sí, al depender una de la otra, se dificulta hacer cambios.
* __Seguridad comprometida:__ Si no se implementan medidas de seguridad como autenticación y autorización, pueden haber problemas de seguridad. Por ejemplo, si se explota una vulnerabilidad desde la UI, se puede acceder a la base de datos.

### Three Tier Architecture

La UI, la lógica de negocio y la base de datos están en computadoras separadas. 2 servidores realizan la mayoría de las tareas en la aplicación. Un servidor es para la capa de lógica  de negocio y otra para la capa donde está la DB. Esta arquitectura se usa para diseñar una aplicación web.

La capa de cliente solicita los datos. El servidor de la aplicación actúa como una interfaz entre el cliente y la capa de servidor. La capa de aplicación ayuda al cliente a comunicarse con el servidor. Recibe la solicitud del cliente y la envía a la base de datos, que luego responde y la información requerida lelga al cliente psando por la capa de aplicación.

La capa de basde de datos almacena todos los datos y responde según la lógica de la capa de aplicación. Esta capa tambipen aumenta la seguridad de la aplicación. El objetivo principal de la arquitectura de 3 capas es separar la __aplicación del cliente__ y la __base de datos física__, lograr independencia de datos del programa y admitir múltiples vistas.

### N-Tier Architecture

Los componentes del software se encuentran separados tanto lógica como físicamente.

#### Ventajas

* __El desarrollo puede separarse:__ Presentación, lógica de negocios y acceso a datos de forma separada para que cada equipo de desarrollo se centre en áreas específicas del sistema.
* __Los cambios en un componente no afecta al resto:__ Los componentes están modularizados y separados.
* __Seguridad a escala local:__ La seguridad se implementa a nivel de cada capa, lo que permite una mayor protección de datos y recursos de sistema. Cada capa puede tener sus propias medidas de seguridad como controles de acceso, encriptación y validación de datos.
* __Escalable y flexible:__ Cada capa se puede escalar y modificar de forma independiente. Esto significa que se pueden agregar o quitar capas según sea necesario.

#### Desventajas

* __Desarrollo y despliegue más costoso:__ Por la separación de responsabilidades, el despliegue suele ser más costoso y requerir más recursos y tiempo.
* __Aumento de complejidad de software:__ En términos de diseño, implementación y mantenimiento. La interacción entre las capas se puede dificultar si no se definen interfaces entre ellas.
* __Rendimiento de la app por factores externos:__ Como la latencia de red (entornos distribuidos o en la nube) y la disponibilidad de serivios externos como bases de datos o APIs de terceros.

## Factores a tomar en cuenta al momento del desarrollo ed un sistema

### Alta Disponibilidad

* Alta disponibilidad, accesibilidad.
* Implementar redundancia en componentes críticos del sistema (servidores, DB y redes) como respaldo.
* Replicación de datos, balanceo de carga e implementación de sistemas de recuperación.

### Buen Rendimiento

* Maximizar cantidad de peticiones, rapidez de consuimo = experiencia de usuario.
* Optimización de algoritmos y procesos lógicos.
* Implementación de técnicas como almacenamiento en caché, optimización de consultas y uso de tecnologías de procesamiento paralelo.

### Integridad garantizada

* Coherencia e integridad de datos.
* Procesos de copia de seguridad y restauración de datos en caso de pérdida o corrupción.

### Costo regulado

* Los costos de hardware y software deben de adecuarse a los requerimientos planteados en el análisis para evitar inflación de presupuestos.
* Utilizar técnicas ed gestión de proyectos y análisis de costos para optimizar gastos.

### Escalabildiad

* Crecimiento vertical y horizontal del software respecto a las necesidades del negocio.
    * <ins>Vertical:</ins> Aumento de recursos en un servidor.
    * <ins>Horizontal:</ins> Distribución de carga en múltiples servidores.

