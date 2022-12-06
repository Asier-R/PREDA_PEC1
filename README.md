# PREDA_PEC1
Práctica 1 de Programación y estructuras de datos avanzadas:
https://asier-r.github.io/mochila_voraz_JavaDoc/

UNED - GRADO EN INGENIERÍA EN INFORMÁTICA Y EN TECNOLOGÍAS DE LA INFORMACIÓN

PROGRAMACIÓN Y ESTRUCTURAS DE DATOS AVANZADAS

ENUNCIADO PRÁCTICA 1 Curso 2022-2023


1.- ENUNCIADO DE LA PRÁCTICA
Se tiene una mochila con una capacidad máxima M y n objetos con pesos p = (p1, p2, …,pn) y beneficios b = (b1,b2, …,bn). El objetivo de este problema es encontrar una selección de objetos cuya suma de pesos no supere la capacidad máxima de la mochila, y de forma que la suma de
beneficios sea máxima. En esta versión del problema los objetos son fraccionables, por lo que se debe aplicar un algoritmo voraz, tal y como está planteado en el apartado 3.5 del texto base.



2.- REALIZACIÓN DE LA PRÁCTICA

2.1.- Diseño del algoritmo
La práctica constará de una memoria y de un programa en java original que resuelva el problema aplicando el esquema indicado.


2.2.- Argumentos y parámetros
La práctica se invoca usando la siguiente sintaxis:
java mochila-voraz [-t][-h] [fichero entrada] [fichero salida]
o
java –jar mochila-voraz.jar [-t][-h] [fichero entrada] [fichero salida]

Los argumentos son los siguientes:
• -t: traza cada paso de manera que se describa la aplicación del algoritmo utilizado.
• -h: muestra una ayuda y la sintaxis del comando.
• fichero_entrada: es el nombre del fichero del que se leen los datos de entrada.
• fichero_salida: es el nombre del fichero que se creará para almacenar la salida.

Por ejemplo:
$ java mochila-voraz -h <ENTER>

SINTAXIS: mochila-voraz [-t][-h] [fichero entrada]
-t Traza el algoritmo
-h Muestra esta ayuda
[fichero entrada] Nombre del fichero de entrada
[fichero salida] Nombre del fichero de salida


2.3- Datos de entrada
El fichero de entrada consta de:
• Una primera línea que indica el número de objetos n.
• Tantas líneas como objetos conteniendo dos datos separados por espacio: el peso de cada uno y su beneficio.
• Una última línea con el valor M que indica la capacidad máxima de la mochila.

La entrada termina cuando se llega al final del fichero.
Por ejemplo, para un conjunto de 3 objetos de pesos 18, 15 y 10, beneficios de 25, 24 y 15, respectivamente, y una mochila de capacidad máxima 20, la entrada sería:
3
18 25
15 24
10 15
20
En caso de que el fichero de entrada no exista, se leerán los datos por la entrada estándar.


2.4- Datos de salida
La salida constará de:
• Tantas líneas como objetos seleccionados, conteniendo cada línea tres datos: peso del objeto, fracción del objeto almacenada en la mochila y beneficio aportado por dicho objeto.
• Beneficio total de la mochila.
En el ejemplo anterior la salida sería:
15 1 24
10 0.5 7.5
31.5
En caso de que el fichero de salida no se indique en la llamada al programa, se escribirá el resultado por la salida estándar.


2.5.- Implementación del algoritmo
El programa se desarrollará en Java siguiendo un diseño orientado a objetos. Los detalles del entorno recomendado se encuentran en la guía de la asignatura. Se valorará el diseño OO y la eficiencia del desarrollo.



3.- CUESTIONES TEÓRICAS DE LA PRÁCTICA
1) Indica y razona sobre el coste temporal y espacial del algoritmo.
2) Explica en qué consiste la función de selección utilizada y demuestra su optimalidad.
3) Explica qué otros esquemas pueden resolver el problema y razona sobre su idoneidad.



4.- ENTREGA DE LA PRÁCTICA

4.1 Material que hay que entregar al Tutor
Se confeccionará una memoria, en PDF con el siguiente índice:
1. Portada de la memoria con nombre, apellidos, dni y dirección de correo.
2. Respuesta a las cuestiones teóricas planteadas en este enunciado.
3. Un ejemplo de ejecución para distintos tamaños del problema.
4. Un listado del código fuente completo.


4.2 Normativa de las prácticas en relación con el Centro Asociado:
1. La asistencia a las sesiones de prácticas es obligatoria.
2. El calendario y procedimiento para asistir a las sesiones de prácticas está publicado en su Centro Asociado o bien aparece en el foro correspondiente a su centro en el curso virtual.
3. El plazo de entrega de la documentación y de la práctica lo establece el Tutor de prácticas de cada Centro Asociado o Campus.
4. El Tutor califica la práctica, informa al alumno y en su caso la revisa de acuerdo con los horarios y procedimiento que establezca el Centro Asociado.
5. Todos los alumnos deberán registrarse a través del Curso Virtual en el grupo del Tutor/a con el que hayan asistido a las sesiones presenciales obligatorias a fin de que su práctica pueda ser calificada.
6. La práctica se debe aprobar en la misma o anterior convocatoria para que se pueda calificar la asignatura. En caso contrario la calificación será de suspenso.
7. La práctica se entregará tanto en el entorno virtual como al Tutor. La falta de cualquiera de ellas será motivo suficiente para quedar excluida de la convocatoria.

El alumno debe asegurarse de que no se da ninguna de las siguientes circunstancias, ya que implican automáticamente una calificación de suspenso:
• Código: el código no compila, no está desarrollado en Java, no se corresponde con el pseudocódigo recogido en la documentación, no es original, está copiado de la red, academia, compañero, etc., o no sigue un diseño OO encapsulado o modular.
• Ejecutable: el ejecutable no termina, se queda sin memoria con ejemplares pequeños o aborta sin justificación. El ejecutable no lee los ficheros previstos en el formato adecuado. No trata los argumentos o no se ajusta a las especificaciones.
• Documentación: No se presenta en el soporte indicado por el tutor o está incompleta.
• Soporte: No se puede leer, o contiene un virus de cualquier tipo. A este respecto, las prácticas en las que se detecte cualquier tipo de virus estarán suspensas.
Los alumnos estudiando en el EXTRANJERO se deberán poner en contacto con el profesor tutor que se indicará en los foros.