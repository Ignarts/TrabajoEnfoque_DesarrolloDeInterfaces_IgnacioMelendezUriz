# 📂 SmartOcupation (Gestión de Alquileres)

## Resumen Del Proyecto

- **Descripción:** Aplicación de escritorio en Java (Swing) para la gestión y consulta de alquileres de viviendas. Permite consultar un histórico de alquileres por rango de fechas y generar un informe en PDF.
- **Propósito:** Trabajo académico/práctico sobre desarrollo de interfaces gráficas en Java, acceso a base de datos (MySQL) y generación de informes (PDF).

## Estructura Del Repositorio

- **Gestor de build:** `pom.xml` (Maven)
- **Clase principal / Lanzador:** `src/main/java/com/example/Main.java`
- **DAO de acceso a datos:** `src/main/java/com/example/dao/AlquilerDAO.java`
- **Modelos:** `src/main/java/com/example/model/Alquiler.java`, `src/main/java/com/example/model/Vivienda.java`
- **Interfaz / Vista:** `src/main/java/com/example/view/VentanaPrincipal.java`
- **Scripts de base de datos:** `src/main/database/createDatabaseAndTables.sql`
- **Tests:** `src/test/java/` (contiene pruebas unitarias para el DAO)

**Árbol de carpetas (resumen):**

```text
smart-ocupation/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── database/
│   │   │   └── createDatabaseAndTables.sql
│   │   └── java/com/example/
│   │       ├── Main.java
│   │       ├── dao/AlquilerDAO.java
│   │       ├── model/Alquiler.java
│   │       └── view/VentanaPrincipal.java
│   └── test/
│       └── java/
│           └── dao/AlquilerDaoTest.java
└── target/
```

## Requisitos

- **Java / JDK:** JDK 17 (el proyecto está configurado para Java 17 en `pom.xml`).
- **Maven:** Para compilar y ejecutar tests.
- **MySQL:** Opcionalmente para ejecutar con la base de datos real (el DAO usa MySQL).
- **Dependencias:** `LGoodDatePicker`, `mysql-connector-j`, `iText` (definidas en `pom.xml`).

## Instalación Y Ejecución

1. Abrir una terminal y situarse en la carpeta del proyecto:

```bash
cd smart-ocupation
```

2. Compilar el proyecto y ejecutar las pruebas:

```bash
mvn -DskipTests=false test
mvn -DskipTests=false package
```

3. Ejecutar la aplicación desde Maven (se descargará el plugin `exec` si hace falta):

```bash
mvn exec:java -Dexec.mainClass="com.example.Main"
```

Alternativas:

- Abrir el proyecto en un IDE (Eclipse/IntelliJ/NetBeans) y ejecutar la clase `com.example.Main`.
- Si prefieres ejecutar desde la clase compilada en `target/classes` con dependencias, usa un `fat-jar` o configura `maven-dependency-plugin`.

## Configuración de la Base de Datos

- El proyecto incluye un script SQL con datos de ejemplo en `src/main/database/createDatabaseAndTables.sql`.
- El `AlquilerDAO` usa por defecto la URL:

```
jdbc:mysql://localhost:3306/SmartOcupationDB
usuario: root
contraseña: (vacía)
```

- Si tu entorno tiene credenciales distintas, modifica las constantes en `src/main/java/com/example/dao/AlquilerDAO.java`:

- Importa y ejecuta el SQL de `createDatabaseAndTables.sql` en tu servidor MySQL antes de usar la aplicación.

## Lógica y Componentes Principales

- **`Main` :** Punto de entrada; configura el look-and-feel y lanza la `VentanaPrincipal` en el hilo de Swing.
- **`VentanaPrincipal` :** Interfaz gráfica principal. Permite seleccionar un rango de fechas con `LGoodDatePicker`, consultar alquileres y generar un PDF con los resultados (usa `iText`).
- **`AlquilerDAO` :** Realiza la conexión JDBC a MySQL y contiene el método `buscarPorFechas(LocalDate inicio, LocalDate fin)` que devuelve una lista de `Alquiler`.
- **Modelos (`Alquiler`, `Vivienda`) :** Representan las entidades leídas desde la base de datos.

Resumen del flujo:

- El usuario elige un rango de fechas en la UI y pulsa `Consultar Histórico`.
- `VentanaPrincipal` llama a `AlquilerDAO.buscarPorFechas(...)` y muestra los resultados en una tabla.
- El usuario puede pulsar `Generar Informe PDF` para exportar la tabla a un archivo `Informe_Alquileres.pdf`.

## Ejemplo de Uso

1. Ejecutar la aplicación.
2. En la ventana principal, seleccionar `Desde` y `Hasta` (fechas).
3. Pulsar `Consultar Histórico` — la tabla mostrará los alquileres que coincidan.
4. Pulsar `Generar Informe PDF` para exportar los datos mostrados a un PDF.

## Tests

- Las pruebas unitarias del DAO están en `src/test/java/dao/AlquilerDaoTest.java` y dependen de que la base de datos de prueba (o la real con datos de ejemplo) esté disponible.
- Ejecuta `mvn test` para lanzar los tests.

## Notas Adicionales

- Cambia las credenciales de la base de datos en `AlquilerDAO` si no usas `root`/vacío.
- El proyecto genera PDFs usando `iText` (versión 5); respeta la licencia correspondiente para usos fuera del ámbito académico.
- El diseño de la UI está implementado con Swing y `LGoodDatePicker` para selección de fechas.
- Para empaquetados o despliegues sin IDE, considera generar un `fat-jar` que incluya dependencias.

## Autor

- Ignacio Meléndez Uriz
- Módulo: Desarrollo de Interfaces / Programación de Interfaces
- Curso: 2025–2026
- Centro: Instituto Virtual DAVANTE

## Bibliografía y Recursos

- [Documentación oficial de Java](https://docs.oracle.com/en/java/)
- [Documentación de Maven](https://maven.apache.org/)
- [LGoodDatePicker (GitHub)](https://github.com/LGoodDatePicker/LGoodDatePicker)
- [iText (Documentación)](https://itextpdf.com/en/resources)
- Recursos y referencias sobre SQL y JDBC.
