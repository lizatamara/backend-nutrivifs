# Backend Clínica Veterinaria NutriVifs

Versión del backend original entregado por la usuaria, complementada con módulos para citas, mensajes, reseñas y carga de imágenes.

## Inicio rápido
1. Crear la base `nutrivifs` con `crear_base_datos.sql`.
2. Revisar credenciales MySQL en `src/main/resources/application.properties`.
3. Abrir esta carpeta en IntelliJ como proyecto Maven.
4. Ejecutar `NutrivifsApplication.java`.
5. Revisar Swagger en `http://localhost:8080/swagger-ui/index.html`.

Spring JPA usa `ddl-auto=update`; por ello crea/actualiza las tablas según las entidades. `DatosIniciales.java` inserta los datos de ejemplo si las tablas de datos iniciales están vacías.

## Módulos agregados
- `cita/`: solicitudes de horas.
- `mensaje/`: formulario de contacto.
- `resena/`: reseñas por producto.
- `archivo/`: carga de imágenes (solo administrador).
- `config/ArchivosWebConfig.java`: publicación de las imágenes guardadas en `/uploads`.

Ver `LEEME_PRIMERO.txt` y `GUIA_PARA_DEFENSA.md` para instrucciones y preparación de defensa.

## Importante sobre la integración
Las páginas React incluidas en el ZIP original aún usan `localStorage` para citas, mensajes y reseñas. Este backend ya dispone de endpoints para esas funciones, pero falta cambiar el frontend para que llame a la API y comprobar el flujo completo con MySQL.

## Estado de verificación
No se pudo ejecutar la compilación Maven durante la preparación porque el entorno no pudo descargar Maven desde Maven Central. Abrir en IntelliJ, dejar que Maven resuelva las dependencias y ejecutar/compilar antes de presentar. No afirmar que está probado hasta hacerlo.
