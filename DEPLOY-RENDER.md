# 🚀 GUÍA DE DESPLIEGUE EN RENDER

## ✅ Archivos Configurados

- ✅ `Dockerfile` - Multi-stage build optimizado
- ✅ `.dockerignore` - Optimización del build
- ✅ `application-prod.properties` - Configuración de producción

---

## 📋 VARIABLES DE ENTORNO PARA RENDER

Cuando crees el **Web Service** en Render, agrega estas variables **exactas**:

### BASE DE DATOS (OBLIGATORIAS)

```
Key: SPRING_DATASOURCE_URL
Value: jdbc:postgresql://dpg-d54rvaali9vc73en9b6g-a/asociaciontitular

Key: SPRING_DATASOURCE_USERNAME
Value: asociaciontitular_user

Key: SPRING_DATASOURCE_PASSWORD
Value: dyOCUkqKRSKQbd8kgj811WeKAwKbv3Y9

Key: SPRING_PROFILES_ACTIVE
Value: prod

Key: JAVA_OPTS
Value: -Xms256m -Xmx512m
```

### EMAIL (OPCIONAL)

```
Key: SPRING_MAIL_USERNAME
Value: joseph.zegarra.g@gmail.com

Key: SPRING_MAIL_PASSWORD
Value: eniw nfxm cpit ngme
```

---

## 🎯 PASOS PARA DESPLEGAR

### 1. Subir a GitHub

```bash
git add .
git commit -m "Docker configuration for Render"
git push origin main
```

### 2. Crear Web Service en Render

1. Ve a https://dashboard.render.com/
2. Click **"New +"** → **"Web Service"**
3. Conecta tu repositorio de GitHub
4. Configuración:
   - **Name**: `asociacion-app` (o el que prefieras)
   - **Region**: **Oregon (US West)** ⚠️ Misma que tu BD
   - **Branch**: `main` (o tu rama principal)
   - **Runtime**: **Docker** ⚠️ MUY IMPORTANTE
   - **Root Directory**: (dejar vacío)
   - **Docker Build Context**: (dejar vacío)
   - **Docker Command**: (dejar vacío)

5. **Environment Variables**: Agregar las 5 variables obligatorias de arriba

6. **Instance Type**: Free

7. Click **"Create Web Service"**

### 3. Esperar el Deploy

- Tardará 5-10 minutos la primera vez
- Verás logs en tiempo real
- Cuando diga **"Live"** (verde), estará listo

### 4. Verificar

- Accede a tu URL: `https://tu-app.onrender.com`
- Intenta iniciar sesión con tus credenciales
- ✅ ¡Debería funcionar!

---

## ⚙️ Información de tu Base de Datos

Tu base de datos YA ESTÁ configurada en Render con todos los datos:

```
Database: asociaciontitular
Host: dpg-d54rvaali9vc73en9b6g-a
User: asociaciontitular_user
Region: Oregon (US West)
Status: ✅ Disponible con 27 socios, 7 roles, y todos los datos
```

---

## 🔍 Troubleshooting

### Error: "Application failed to start"

**Verificar:**
1. Variables de entorno están correctas
2. `SPRING_PROFILES_ACTIVE=prod` está configurado
3. Runtime es **Docker** (no Java)

### Error: "Database connection failed"

**Verificar:**
1. URL usa `jdbc:postgresql://` (no solo `postgres://`)
2. Host es el interno: `dpg-d54rvaali9vc73en9b6g-a` (sin .render.com)
3. Usuario y contraseña correctos

### Ver Logs

En Render Dashboard → tu servicio → pestaña **"Logs"**

---

## 📝 Notas Importantes

- ✅ El puerto 8080 se detecta automáticamente desde el Dockerfile
- ✅ No necesitas configurar nada más en Render
- ✅ Los datos ya están en Render (27 socios + todos los registros)
- ⚠️ Plan Free: app se suspende tras 15 min inactivo
- ⚠️ Primera carga tras suspensión: 30-60 segundos

---

¡Tu proyecto está listo para Render! Solo sube a GitHub y crea el Web Service 🚀
