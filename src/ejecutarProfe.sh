#!/bin/bash

# Verificar que se proporcionó un archivo de entrada
if [ $# -ne 1 ]; then
    echo "Uso: $0 nombreFicheroEntrada.plx"
    exit 1
fi

# Obtener el nombre del archivo sin la extensión
nombreFicheroEntrada=$1
nombreBase="${nombreFicheroEntrada%.plx}"

# Comandos
echo "Ejecutando plxcProfe..."
./plxcProfe "$nombreFicheroEntrada" "$nombreBase.ctd"

if [ $? -ne 0 ]; then
    echo "Error al ejecutar plxcProfe."
    exit 2
fi

echo "Ejecutando ctd con el archivo generado..."
./ctd -v "$nombreBase.ctd"

if [ $? -ne 0 ]; then
    echo "Error al ejecutar ctd."
    exit 3
fi

echo "Ejecución completada correctamente."

