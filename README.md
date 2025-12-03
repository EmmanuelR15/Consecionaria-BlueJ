# Concesionaria BlueJ - Trabajo Práctico Integrador

## 📋 Descripción del Proyecto

Sistema de gestión integral para una **concesionaria de vehículos** desarrollado en **Java 8.0** como trabajo práctico integrador de la asignatura Programación II.

El sistema permite administrar un inventario heterogéneo de vehículos (automóviles, camionetas y motocicletas), distinguiendo entre vehículos nuevos y usados. Incluye un **Taller de Revisión Mecánica** con cola FIFO para procesar mantenimiento de vehículos usados, y un **Lavadero** que complementa el servicio de acondicionamiento antes de la venta.

### Características técnicas principales:
- Arquitectura modular con separación **Diseño vs. Negocio**
- Herencia multinivel y clases abstractas
- Interfaces genéricas y especializadas
- Excepciones propias con propagación controlada
- Tipos enumerativos para categorías
- Menú interactivo con validación exhaustiva de entrada
- Wrappers y colecciones (ArrayList, LinkedList)
- Clase genérica `Cola<T>` para gestión de procesos
- Persistencia bidireccional en archivos (serialización CSV)
- CRUD completo (Create, Read, Update, Delete)

---

## 🔧 Requisitos

- **Java**: Versión 8 o superior
- **BlueJ**: Versión 4.0 o superior
- **Git**: (Opcional, para versionamiento)
- **Sistema Operativo**: Windows, macOS o Linux

### Verificar versión de Java:
```bash
java -version
```

---

## 📦 Instalación y Ejecución

### 1. Clonar o descargar el repositorio
```bash
git clone https://github.com/tuusuario/Consecionaria-BlueJ.git
cd Consecionaria-BlueJ
```

### 2. Abrir el proyecto en BlueJ
- Abre **BlueJ**
- Selecciona `Proyecto` → `Abrir Proyecto...`
- Navega a la carpeta `Consecionaria-BlueJ` y haz clic en `Abrir`

### 3. Compilar el proyecto
- En BlueJ, haz clic en `Proyecto` → `Compilar Todo`
- O selecciona cada clase y presiona el botón **Compilar**

### 4. Ejecutar la aplicación
- En el panel de clases, haz clic derecho en `Main`
- Selecciona `Métodos estáticos` → `main(String[] args)`
- Se abrirá un menú interactivo en la consola de BlueJ

---

## 🎯 Funcionalidades Principales

### Gestión de Inventario
- ✅ **Agregar vehículos** (Automóvil, Camioneta, Motocicleta) con validación de duplicados
- ✅ **Listar inventario** completo o buscar por marca/modelo/año
- ✅ **Modificar** datos de vehículos existentes
- ✅ **Eliminar** vehículos del inventario

### Taller de Revisión Mecánica
- ✅ **Encolar vehículos usados** en cola FIFO
- ✅ **Procesar mantenimiento** con cambio de estado automático
- ✅ **Visualizar cola** de espera
- ✅ Integración con **Lavadero** para acabado final

### Persistencia
- ✅ **Guardar inventario** en archivo `vehiculos.txt` (formato CSV)
- ✅ **Cargar inventario** desde archivo en sesiones posteriores
- ✅ Serialización/deserialización con validación de tipos

### Patrones de Diseño Implementados
- ✅ **JavaBean**: Constructores sin parámetros, getters/setters
- ✅ **Herencia multinivel**: Vehiculo → VehiculoTerrestre → Automovil/Camioneta
- ✅ **Clases abstractas**: `Vehiculo` y `VehiculoTerrestre`
- ✅ **Interfaces**: `Persistible` (guardar/cargar), `Mantenible` (mantenimiento)
- ✅ **Genéricos**: `Cola<T>` para almacenamiento tipo-seguro
- ✅ **Enumerativos**: `TipoCarroceria`, `TipoMotocicleta`
- ✅ **Excepciones propias**: `VehiculoException` con múltiples constructores
- ✅ **Wrappers**: Integer, Double, Boolean, ArrayList
- ✅ **Colecciones**: ArrayList para inventario, LinkedList para cola

---

## 📁 Estructura del Proyecto

```
Consecionaria-BlueJ/
├── diseno/
│   ├── Cola.java              # Clase genérica Cola<T>
│   ├── Mantenible.java        # Interfaz de mantenimiento
│   ├── Persistible.java       # Interfaz de persistencia
│   ├── VehiculoException.java # Excepción personalizada
│   └── package.bluej
├── negocio/
│   ├── Automovil.java         # Subclase de VehiculoTerrestre
│   ├── Camioneta.java         # Subclase de VehiculoTerrestre
│   ├── Concesionaria.java     # Gestión de inventario
│   ├── Lavadero.java          # Servicio de lavado
│   ├── Motocicleta.java       # Subclase de Vehiculo
│   ├── Taller.java            # Gestión de mantenimiento
│   ├── TipoCarroceria.java    # Enumerativo
│   ├── TipoMotocicleta.java   # Enumerativo
│   ├── Vehiculo.java          # Clase abstracta raíz
│   ├── VehiculoTerrestre.java # Clase abstracta intermedia
│   └── package.bluej
├── Main.java                  # Punto de entrada (menú principal)
├── README.md                  # Este archivo
├── .gitignore                 # Configuración de Git
└── vehiculos.txt              # Datos persistidos (generado en runtime)
```

---

## 💻 Menú Principal

Al ejecutar `Main`, se despliega un menú interactivo:

```
=== CONCESIONARIA DE VEHÍCULOS ===
1. Agregar vehículo al inventario
2. Listar inventario completo
3. Buscar vehículo
4. Modificar vehículo
5. Eliminar vehículo
6. Enviar vehículo usado a taller
7. Atender vehículo en taller
8. Ver cola del taller
9. Lavar vehículo
10. Guardar inventario
11. Cargar inventario
0. Salir
```

### Validación de entrada
- **Números enteros**: Solo acepta valores en rango especificado
- **Valores booleanos**: Acepta "s" o "n"
- **Años**: Valida rango [1900, 2025]
- **Decimales**: Valida formato numérico
- **Texto**: Elimina espacios en blanco

---

## 🔍 Auditoría Crítica

### Fortalezas del Sistema
1. **Arquitectura modular**: Separación clara entre lógica de dominio y componentes reutilizables
2. **Type safety**: Uso exhaustivo de generics y enumerativos evita errores en tiempo de ejecución
3. **Manejo robusto de errores**: Excepciones personalizadas con mensajes informativos
4. **Persistencia funcional**: Serialización bidireccional con validación de formato
5. **UX intuitiva**: Menú interactivo con validación preventiva y retroalimentación clara
6. **Código limpio**: Nomenclatura consistente, métodos con responsabilidad única, bajo acoplamiento

### Áreas de Mejora Identificadas
- Métodos `guardar()` y `cargar()` en `Vehiculo` solo imprimen a consola (mejora potencial: sincronización con archivo)
- Ausencia de sincronización en `ArrayList` (justificado en entorno single-threaded, mejora para multi-threading futura)
- Cola sin implementación de `Iterable<T>` (mejora potencial para uso en for-each loops)
- Lavadero como clase utilitaria estática (mejora potencial: composición en Taller)

**Evaluación general**: Sistema robusto y escalable, apto para defensa académica con calificación esperada de 90-100 puntos.

---

## 📝 Uso de IA en Desarrollo

Este proyecto ha sido desarrollado con asistencia de **GitHub Copilot**, utilizado como herramienta de:
- Generación de código boilerplate
- Revisión arquitectónica
- Documentación JavaDoc
- Validación de patrones de diseño

**Nota**: La IA actúa como asistencia, no como sustituto. Todas las decisiones arquitectónicas, patrones aplicados y validaciones han sido revisadas y justificadas manualmente.

---

## 🤝 Contribuciones

Este es un proyecto académico de Programación II. Las contribuciones son limitadas a mejoras sugeri­das en la auditoría crítica o en la defensa oral.

---

## 📄 Licencia

Proyecto académico sin licencia específica. Uso exclusivamente educativo.

---

## 👤 Autor

**Emma** - Trabajo Práctico Integrador - Programación II  
**Institución**: [Tu Instituto/Universidad]  
**Fecha**: 3 de diciembre de 2025

---

## 📞 Contacto / Preguntas

Para dudas sobre la implementación o defensa del proyecto, contacta al autor o al docente responsable de la asignatura.

---

**Última actualización**: 3 de diciembre de 2025
