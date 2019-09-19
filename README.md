# facultadRest api

## Descripcion de servicio:

Esta aplicacion se encarga de manejar los empleados y estudiantes de una Facultad <br /> 
con base de datos mongo. El usuario dispone de tres tipos de empleados: <br />
Personal de servicio, Profesores y Administrativos. <br />
A partir de estas clases podra buscar y borrar empleados. <br />
Dentro de cada clase tambien dispone de las herramientas para crear y actualizar empleados en base a su cargo. <br />
Esta aplicacion es capaz de ustilizar otro servicio con una base de datos de empleados, y a partir de este buscar un <br /> empleado y/o agregar este a su base de datos para luego actualizar su informacion correspondiente a su cargo. <br />

## Prerequisitos-Setup:

-Git: https://git-scm.com/downloads <br />
-Maven 3.0: https://maven.apache.org/download.cgi <br />
-MongoDB 3.6: https://www.mongodb.com/download-center#community <br />
-Java 8: https://www.java.com/es/download/ <br />
-PostMan: https://www.getpostman.com/downloads/ <br />

## Body:

-Empleado:  <br />

{ <br />
	  "id": "5d7f8a24a7986c0b1a0d04ce", <br />
    "nombre": "sandro", <br />
    "apellido": "lopez", <br />
    "dni": "36254521", <br />
    "cargo": "ADMINISTRATIVO", <br />
    "anioDeIncorpora": "2009", <br />
    "salario": 25000.0, <br />
    "sector": "2c", <br />
    "seccion": "4a", <br />
    "materia": "Matematicas", <br />
    "catedra": "Saens Peña" <br />
} <br />

-Estudiante: <br />

{<br />
"nombre": "Pedro",<br />
"apellido": "Ramirez",<br />
"dni": "40506073",<br />
"curso": "1°A",<br />
"accesoComedor":"true"<br />
}<br />

## Endpoints:

Leyenda:<br />
1- Endpoint.<br />
2- Descripcion.<br />

Metodos GET:<br />

Empleados Facultad: <br />

1- http://10.2.19.18:8080/v1/api/empleados/ <br />
2- "Busca la lista de los empleados pertentecientes a la facultad."<br />

1- http://10.2.19.18:8080/v1/api/empleados/administrativos <br />
2- "Busca la lista de los empleados pertentecientes a la facultad con cargo de administrativo."<br />

1- http://10.2.19.18:8080/v1/api/empleados/profesores <br />
2- "Busca la lista de los empleados pertentecientes a la facultad con cargo de profesor."<br />

1- http://10.2.19.18:8080/v1/api/empleados/personaldeservicios <br />
2- "Busca la lista de los empleados pertentecientes a la facultad con cargo de personal de servicio."<br />

1- http://10.2.19.18:8080/1/api/empleados? <br />
2- "Busca lista de empleados a partir de parametros ingresados por el usuario, en el caso de no ingresarlos <br />
trae todos los empleados de la facultad. <br />
*Ej:http://10.2.19.18:8080/api/empleados??cargo=PROFESOR&nombre=Alfredo, buscar todos los empleados que tengan <br />
cargo profesor y nombre Alfredo.* <br />

1- http://10.2.19.18:8080/v1/api/empleados/salario?salarioMin="a"&salarioMax="b"<br />
2- "Busca los empleados que tienen un sueldo comprendido entre el valor "a" y "b"."<br />

1- http://10.2.19.18:8080/v1/api/empleados/{dni} <br />
2- "Busca un empleado a partir de su Dni." <br />

Empleados Empresa: <br />

1- http://10.2.19.18:8080/v1/api/empresa <br />
2- "Busca lista de empleados que pertenecen a la empresa." <br />

1- http://10.2.19.18:8080/v1/api/empresa/{dni} <br />
2- "Busca y muestra un empleado perteneciente a la empresa a partir de su Dni." <br />

1- http://10.2.19.18:8080/v1/api/empresa?
2- "Busca y muestra a partir de parametros ingresados por el usuario una lista que cumpla con los requisitos. <br />
En caso de no tener parametros ingresados muestra la lista de todos los empleados de la empresa." 
*Ej: http://10.2.19.18:8080/api/empresa??cargo=VENDEDOR&nombre=sandro, buscar todos los empleados que tengan <br />
cargo vendedor y tmabien nombre sandro.* <br />

Estudiantes:

1- http://10.2.19.18:8080/v1/api/estudiantes/{dni}  <br />
2- "Busca un estudiante a partir de su dni."  <br />

1- http://10.2.19.18:8080/v1/api/estudiantes? <br />
2- "Busca a partir de parametros ingresados por el usuario un estudiante o una lista de <br />
estudiantes que cumplan con los paramentros ingresados. En caso de no haber parametros devuelve<br />
la lista completa de estudiantes" <br />


Método Delete:<br />

Empleados:<br />

1- http://10.2.19.18/v1/api/empleados/{dni} <br />
2- "Borra empleado a partir de su Dni." <br />

Estudiantes:<br />

1- http://10.2.19.18/v1/api/estudiantes/{dni} <br />
2- "Borra estudiante a partir de su Dni."

Métodos Post:<br />

Empleados Facultad: <br />

Importante-Disclaimer: <br />
¡El usuario solo podra crear empleados a partir de su cargo! Los cargos posibles son: <br />
ADMINISTRATIVO,PROFESOR,PERSONAL_DE_SERVICIO. <br />

1- http://10.2.19.18:8080/v1/api/empleados/administrativos <br />
2- "Crea empleado a partir de los atributos de un empleado administrativo.(Recordar utilizar el cargo correcto) <br />
 
1- http://10.2.19.18:8080/v1/api/empleados/profesores <br />
2- "Crea empleado a partir de los atributos de un empleado profesor.(Recordar utilizar el cargo correcto) <br />

1- http://10.2.19.18:8080/v1/api/empleados/personaldeservicios <br />
2- "Crea empleado a partir de los atributos de un empleado del personal de servicio. <br />
(Recordar utilizar el cargo correcto) <br />

Estudiantes : <br />

1- http://10.2.19.18:8080/v1/api/estudiantes
2- "Crea y agrega a la base un estudiante de la facultad."

Empleados Empresa: <br />

1- http://10.2.19.18:8080/v1/api/empresa/personaldeservicios/{dni} <br />
2- "Busca empleado perteneciente a empresa y lo guarda con cargo de personal de servicio en la facultad." <br />

1- http://10.2.19.18:8080/v1/api/empresa/administrativos/{dni} <br />
2- "Busca empleado perteneciente a empresa y lo guarda con cargo de administrativo en la facultad." <br />

1- http://10.2.19.18:8080/v1/api/empresa/administrativos/{dni} <br />
2- "Busca empleado perteneciente a empresa y lo guarda con cargo de profesor en la facultad." <br />

Métodos PUT: 

Importante-Disclaimer: <br />
¡El usuario solo podra actualizar empleados a partir de su cargo! Los cargos posibles son: <br />
ADMINISTRATIVO,PROFESOR,PERSONAL_DE_SERVICIO. Si el cargo del empleado es diferente <br />
al cargo ingresado por el usuario, este no podra actualizar al empleado.Por ultimo <br />
el Dni de cada empleado es unico e no intercambiable." <br />

1- http://10.2.19.18:8080/v1/api/empleados/personaldeservicios/{dni} <br />
2- "Actualiza al empleado con cargo personal de servicio a partir de su Dni, puede agregar y/o cambiar atributos." <br />

1- http://10.2.19.18:8080/v1/api/empleados/profesores/{dni} <br />
2- "Actualiza al empleado con cargo profesor a partir de su Dni, puede agregar <br />
y/o cambiar atributos." <br />

1- http://10.2.19.18:8080/v1/api/empleados/administrativos/{dni} <br />
2- "Actualiza al empleado con cargo administrativo a partir de su Dni, puede agregar y/o cambiar <br /> atributos." <br />

Estudiantes: <br />

1- http://10.2.19.18:8080/v1/api/estudiantes/{dni}<br />
2- "Actualiza los atributos de un estudiante de la facultad, no es posible cambiar su Dni." <br />


















