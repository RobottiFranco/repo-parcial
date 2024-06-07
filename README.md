ut7 el archivo alternitivo de grafo dirigido tiene floyd con predecesores
centro del grafo y excentricidad no funcionan

dique  si es posible volar desde la ciudad x a 
la ciudad y”.  esto es "todos los caminos"

Algoritmo de Floyd-Warshall
•	Propósito: Encuentra las rutas más cortas entre todos los pares de nodos. (calcular los costos mínimos de conexiones entre todo par de ciudades)

•	Complejidad: (n^3), donde n es el número de nodos (ciudades) en el grafo.

•	Ventajas: Muy efectivo para grafos densos y grafos donde necesitas conocer las distancias entre todos los pares de nodos.

Algoritmo de Dijkstra
•	Propósito: Encuentra la ruta más corta desde un nodo fuente a todos los demás nodos.

•	Complejidad: O((n+e)log⁡n) usando una cola de prioridad (heap), donde nnn es el número de nodos y eee es el número de aristas.

•	Ventajas: Muy eficiente para grafos con muchas aristas (grafos densos) cuando solo necesitas las distancias desde un nodo fuente.
