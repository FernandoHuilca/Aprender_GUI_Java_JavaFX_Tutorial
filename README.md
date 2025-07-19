# 🎨 Aprender GUI Java - JavaFX Tutorial

> **Un proyecto educativo completo para aprender a crear interfaces gráficas de usuario en Java usando JavaFX, Maven y Scene Builder.**

## 📋 Tabla de Contenidos

- [🎯 ¿Qué es este proyecto?](#-qué-es-este-proyecto)
- [🏗️ Estructura del Proyecto](#️-estructura-del-proyecto)
- [⚙️ Configuración del Entorno](#️-configuración-del-entorno)
- [🚀 Cómo Empezar](#-cómo-empezar)
- [📚 Conceptos Clave](#-conceptos-clave)
- [🔧 Solución de Problemas Comunes](#-solución-de-problemas-comunes)
- [📖 Recursos Adicionales](#-recursos-adicionales)

---

## 🎯 ¿Qué es este proyecto?

Este repositorio contiene un proyecto educativo completo para aprender a desarrollar aplicaciones con interfaz gráfica de usuario (GUI) en Java usando **JavaFX**. El proyecto está diseñado para separar claramente la lógica de negocio (backend) de la interfaz gráfica (frontend).

### ✨ Características

- **JavaFX** para interfaces gráficas modernas
- **Scene Builder** para diseño visual de GUIs
- **Maven** para gestión de dependencias
- **FXML** para separar vista y lógica
- **Sistema de módulos Java 9+** para mejor organización
- **Navegación entre ventanas** con controladores

---

## 🏗️ Estructura del Proyecto

```
nombreProyecto/
│
├─ .idea/                  # Configuración de IntelliJ IDEA
├─ .mvn/                   # Archivos internos de Maven
├─ src/
│   └─ main/
│       ├─ java/
│       │   └─ com.example.prueba_gui_video/
│       │        └─ ... (clases Java)
│       │        └─ module-info.java
│       └─ resources/
│            └─ com.example.prueba_gui_video/
│                 └─ hello-view.fxml
├─ pom.xml                 # Configuración de Maven
├─ mvnw, mvnw.cmd          # Scripts de Maven
└─ .gitignore              # Archivos ignorados por Git
```

### 📁 Archivos Importantes

| Archivo | Descripción | ¿Se puede borrar? |
|---------|-------------|:-----------------:|
| `pom.xml` | Configuración de Maven | ❌ **No** |
| `module-info.java` | Descriptor de módulos Java | ❌ **No** |
| `src/main/java/` | Código fuente Java | ❌ **No** |
| `src/main/resources/` | Recursos (FXML, imágenes) | ❌ **No** |
| `.idea/` | Configuración de IntelliJ | ✅ **Sí** |
| `.mvn/`, `mvnw*` | Scripts de Maven | ✅ **Sí** |

---

## ⚙️ Configuración del Entorno

### 📋 Prerrequisitos

- **Java JDK 11+** (recomendado JDK 17)
- **IntelliJ IDEA** (Community o Ultimate)
- **Scene Builder** (para diseño visual)
- **Maven** (incluido con IntelliJ)

### 🔧 Configurar Scene Builder en IntelliJ

1. **Abrir Configuración**
   - `File` → `Settings` (o `Ctrl + Alt + S`)
   - `Languages & Frameworks` → `JavaFX`

2. **Configurar ruta de Scene Builder**
   - Buscar **Scene Builder executable path**
   - Hacer clic en **Browse**
   - Navegar a la instalación de Scene Builder:
     - **Windows:** `C:\Users\[Usuario]\AppData\Local\SceneBuilder\bin\SceneBuilder.exe`
     - **macOS:** `/Applications/SceneBuilder.app/Contents/MacOS/SceneBuilder`
     - **Linux:** `/opt/SceneBuilder/bin/SceneBuilder`

3. **Verificar**
   - `Apply` → `OK`
   - Doble clic en archivo `.fxml` para abrir con Scene Builder

---

## 🚀 Cómo Empezar

### 1. 📥 Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/aprender-gui-java.git
cd aprender-gui-java
```

### 2. 🔧 Configurar el Proyecto

1. Abrir el proyecto en **IntelliJ IDEA**
2. Esperar a que Maven descargue las dependencias
3. Configurar Scene Builder (ver sección anterior)

### 3. 🎯 Ejecutar la Aplicación

```bash
# Usando Maven
mvn clean javafx:run

# O desde IntelliJ
# Hacer clic derecho en la clase principal → Run
```

---

## 📚 Conceptos Clave

### 🎭 JavaFX Application

```java
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Cargar FXML
        FXMLLoader fxmlLoader = new FXMLLoader(
            HelloApplication.class.getResource("hello-view.fxml")
        );
        
        // Crear escena
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        
        // Configurar ventana
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
```

### 🎨 FXML - Separación de Vista y Lógica

```xml
<?xml version="1.0" encoding="UTF-8"?>

<Pane xmlns:fx="http://javafx.com/fxml" 
      fx:controller="com.example.HelloController">
    <children>
        <Button fx:id="miBoton" 
                layoutX="50" layoutY="50" 
                text="Haz clic" 
                onAction="#handleButtonClick"/>
        <Label fx:id="miLabel" 
               layoutX="50" layoutY="100" 
               text="¡Hola Mundo!"/>
    </children>
</Pane>
```

### 🎮 Controladores Java

```java
public class HelloController {
    @FXML
    private Button miBoton;
    
    @FXML
    private Label miLabel;
    
    @FXML
    private void handleButtonClick() {
        miLabel.setText("¡Botón presionado!");
    }
}
```

### 🔄 Navegación entre Ventanas

```java
public class MainController {
    @FXML
    private Button btnPerfil;
    
    @FXML
    private void cambiarAPerfil() {
        MetodosFrecuentes.cambiarVentana(
            (Stage) btnPerfil.getScene().getWindow(),
            "/Modulo_Ejercicios/views/GUIPerfil.fxml",
            "Ventana Perfil"
        );
    }
}
```

---

## 🔧 Sistema de Módulos Java

### 📦 `module-info.java`

```java
module MiModulo {
    requires javafx.controls;
    requires javafx.fxml;
    
    // Para APIs públicas
    exports com.miapp.utilidades;
    
    // Para frameworks (JavaFX FXML)
    opens com.miapp.controladores to javafx.fxml;
}
```

### 🔍 `exports` vs `opens`

| Aspecto | `exports` | `opens` |
|---------|-----------|---------|
| **Tipo de acceso** | Compilación | Reflexión |
| **Cuándo usar** | APIs públicas | Frameworks |
| **Ejemplo** | Librerías | JavaFX FXML |
| **Seguridad** | Más restrictivo | Menos restrictivo |

### 🔮 ¿Qué es la Reflexión?

La **reflexión** permite que un programa Java examine y modifique su propia estructura en tiempo de ejecución.

**Ejemplo con JavaFX FXML:**
```java
// JavaFX internamente hace esto por reflexión:
Class<?> controladorClass = Class.forName("com.miapp.MiControlador");
Object controlador = controladorClass.getDeclaredConstructor().newInstance();

// Busca campos con @FXML
Field campo = controladorClass.getDeclaredField("miBoton");
campo.setAccessible(true);
campo.set(controlador, botonCreado);
```

---

## 🔧 Solución de Problemas Comunes

### ❌ Error: "Location is not set"

**Causa:** El `FXMLLoader` no encuentra el archivo FXML.

**Solución:**
```java
// ❌ Incorrecto
FXMLLoader loader = new FXMLLoader();
loader.load();

// ✅ Correcto
FXMLLoader loader = new FXMLLoader(
    getClass().getResource("/Modulo_Ejercicios/views/GUIEntrada.fxml")
);
Parent root = loader.load();
```

### ❌ Error: "No controller specified"

**Causa:** El FXML no tiene especificado un controlador.

**Solución:**
```xml
<!-- Agregar fx:controller en el elemento raíz -->
<Pane xmlns:fx="http://javafx.com/fxml" 
      fx:controller="com.example.MiControlador">
```

### ❌ Error: "Cannot access class... because module does not export"

**Causa:** El módulo no permite acceso por reflexión.

**Solución:**
```java
module MiModulo {
    requires javafx.fxml;
    opens com.miapp.controladores to javafx.fxml;
}
```

### ❌ Error: "ClassNotFoundException: com.gluonhq.charm.glisten.control.TextField"

**Causa:** Scene Builder agregó componentes de librerías externas.

**Solución:**
- Usar solo componentes estándar de JavaFX
- Revisar imports en el FXML
- Cambiar `<com.gluonhq.charm.glisten.control.TextField>` por `<TextField>`

---

## 📖 Recursos Adicionales

### 📚 Documentación Oficial
- [JavaFX Documentation](https://openjfx.io/)
- [Scene Builder](https://gluonhq.com/products/scene-builder/)
- [Maven Documentation](https://maven.apache.org/)

### 🎥 Tutoriales Recomendados
- [JavaFX Tutorial for Beginners](https://www.youtube.com/watch?v=FLkOX4Eez6o)
- [Scene Builder Tutorial](https://www.youtube.com/watch?v=FLkOX4Eez6o)

### 🔧 Herramientas Útiles
- **IntelliJ IDEA** - IDE recomendado
- **Scene Builder** - Diseño visual de GUIs
- **Maven** - Gestión de dependencias

---

## 🤝 Contribuir

¡Las contribuciones son bienvenidas! 

1. Fork el proyecto
2. Crear una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir un Pull Request

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

---

## 👨‍💻 Autor

**Tu Nombre** - [@tu-usuario](https://github.com/tu-usuario)

---

<div align="center">

### ⭐ ¡No olvides dar una estrella si te fue útil!

</div>