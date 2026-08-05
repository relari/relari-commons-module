# shared-commons

Librería compartida centralizada para manejo de errores y utilidades comunes en microservicios.

## 📦 Módulos

### 1. **error** - Manejo Centralizado de Errores
Proporciona una estrategia consistente de manejo de excepciones en toda la aplicación.

**Características:**
- ✅ YAML como única fuente de verdad (sin duplicación)
- ✅ ApiExceptionFactory para creación consistente de excepciones
- ✅ GlobalExceptionHandler para manejo centralizado
- ✅ Categorías dinámicas (sin recompilación)
- ✅ Respuestas HTTP estructuradas y consistentes

**Documentación:**
- 📖 [Guía de Uso](error/USAGE_GUIDE.md)
- 🔄 [Guía de Migración](error/MIGRATION_GUIDE.md)

**Ejemplo Rápido:**
```java
@Service
@RequiredArgsConstructor
public class EmployeeService {
  private final ApiExceptionFactory exceptionFactory;
  
  public Employee getEmployee(Long id) {
    return repo.findById(id)
      .orElseThrow(() -> 
          exceptionFactory.create(
              ErrorCategoryName.EMPLOYEE_NOT_FOUND,
              "Empleado con ID " + id + " no existe"
          )
      );
  }
}
```

### 2. **support** - Utilidades Comunes
Herramientas compartidas: constantes, mapas genéricos, utilidades de fecha/JSON.

---

## 🏗️ Estructura del Proyecto

```
shared-commons/
├── error/
│   ├── src/main/java/.../error/
│   │   ├── category/          → Categorías de error
│   │   ├── config/            → Configuración Spring
│   │   ├── exception/         → ApiException
│   │   ├── handler/           → GlobalExceptionHandler
│   │   ├── model/             → DTOs y records
│   │   └── util/              → ApiExceptionFactory
│   ├── src/main/resources/
│   │   └── application.yml    → Configuración de errores
│   ├── USAGE_GUIDE.md         → Cómo usar el módulo
│   └── MIGRATION_GUIDE.md     → Cómo migrar código antiguo
│
└── support/
    ├── src/main/java/.../support/
    │   ├── constant/          → Constantes globales
    │   ├── generic/           → Tipos genéricos
    │   ├── model/             → DTOs comunes
    ���   └── utility/           → Utilidades varias
    └── pom.xml
```

---

## 🚀 Instalación

### Maven

```xml
<dependency>
    <groupId>pe.com.relari</groupId>
    <artifactId>error</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>

<dependency>
    <groupId>pe.com.relari</groupId>
    <artifactId>support</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

---

## 🔧 Configuración Mínima

### 1. Agregar application.yml

```yaml
application:
  errors:
    code: EM  # Prefijo para códigos de error
    categories:
      EMPLOYEE_NOT_FOUND:
        code: '002'
        description: 'Empleado no encontrado.'
        http-status: 404
      EMPLOYEE_SAVE_FAILED:
        code: '001'
        description: 'Error al guardar al empleado.'
        http-status: 500
```

### 2. Inyectar en servicios

```java
@Service
@RequiredArgsConstructor
public class MyService {
  private final ApiExceptionFactory exceptionFactory;
  
  // Usar en métodos
}
```

---

## 📚 Documentación Detallada

| Tema | Ubicación |
|------|-----------|
| **Cómo usar el módulo error** | [error/USAGE_GUIDE.md](error/USAGE_GUIDE.md) |
| **Cómo migrar código antiguo** | [error/MIGRATION_GUIDE.md](error/MIGRATION_GUIDE.md) |
| **Respuestas HTTP ejemplo** | [error/USAGE_GUIDE.md#-respuesta-http](error/USAGE_GUIDE.md) |
| **Todas las excepciones soportadas** | [error/USAGE_GUIDE.md#-manejo-de-excepciones-soportadas](error/USAGE_GUIDE.md) |

---

## 🎯 Arquitetura de Errores

```
Aplicación
    ↓
GlobalExceptionHandler (Spring)
    ├─→ ApiException ─→ ErrorProperties ─→ application.yml
    ├─→ MethodArgumentNotValidException ─→ ValidationErrorDetail[]
    ├─→ MissingServletRequestParameterException ─→ ErrorResponse(400)
    ├─→ MethodArgumentTypeMismatchException ─→ ErrorResponse(400)
    ├─→ HttpMessageNotReadableException ─→ ErrorResponse(400)
    ├─→ Exception (catch-all) ─→ ErrorResponse(500)
    ↓
ErrorResponse (JSON)
    {
      "description": "...",
      "code": "EM-XXX",
      "status": 400,
      "timestamp": "2024-01-15T10:30:00",
      "catalog": "EMPLOYEE_NOT_FOUND",
      "metadata": {...}
    }
```

---

## 🔐 Seguridad

- ✅ Stack traces **NO** se envían a clientes en producción (futura mejora)
- ✅ Información sensible no expuesta
- ✅ Categorías validadas dinámicamente
- ✅ Respuestas estructuradas y predecibles

---

## 🆕 ¿Cambios Recientes?

### v1.0.0 (Actual)
- ✅ Eliminada duplicación ErrorCategory enum ↔ YAML
- ✅ Introducida ApiExceptionFactory
- ✅ YAML como única fuente de verdad
- ✅ Separación de ErrorDetail en ValidationErrorDetail
- ✅ Documentación completa

**→ Lee [MIGRATION_GUIDE.md](error/MIGRATION_GUIDE.md) si actualizas desde versiones antiguas**

---

## 📖 Ejemplos de Uso

### Ejemplo 1: Excepción Simple
```java
throw exceptionFactory.create(
    ErrorCategoryName.EMPLOYEE_NOT_FOUND,
    "Empleado con ID 123 no existe"
);
```
**Respuesta HTTP 404**

### Ejemplo 2: Excepción con Causa Raíz
```java
try {
  employeeRepository.save(employee);
} catch (DataIntegrityViolationException e) {
  throw exceptionFactory.create(
      ErrorCategoryName.EMPLOYEE_SAVE_FAILED,
      e,
      null,
      metadata
  );
}
```
**Respuesta HTTP 500**

### Ejemplo 3: Validación Manual
```java
List<String> errors = validateEmployee(dto);
if (!errors.isEmpty()) {
  throw exceptionFactory.create(
      ErrorCategoryName.EMPLOYEE_SAVE_FAILED,
      "Validaciones fallidas",
      errors,
      metadata
  );
}
```

---

## 🔌 Extensibilidad

### Agregar Nueva Categoría de Error

1. **Editar `application.yml`**
   ```yaml
   categories:
     MY_NEW_ERROR:
       code: '999'
       description: 'Mi nuevo error'
       http-status: 400
   ```

2. (**Opcional**) **Agregar constante en `ErrorCategoryName.java`**
   ```java
   public static final String MY_NEW_ERROR = "MY_NEW_ERROR";
   ```

3. **¡Listo!** No hay recompilación necesaria

---

## 🐛 Troubleshooting

| Problema | Solución |
|----------|----------|
| `IllegalArgumentException: Categoría no existe` | Agregar categoría a `application.yml` |
| `NullPointerException` en ErrorProperties | Verificar que `application.yml` fue compilado |
| Stack trace visible en producción | Implementar filtro por ambiente |

---

## 📝 Licencia

Código propietario © 2024

---

## 👥 Contribuciones

Para cambios:
1. Actualizar YAML en lugar de enums
2. Usar ApiExceptionFactory
3. Documentar cambios
4. Ejecutar tests
