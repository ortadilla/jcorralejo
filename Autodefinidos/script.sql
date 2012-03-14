INSERT INTO palabra (palabra, definicion, dificultad) select 'A', 'Vocal', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='A' and definicion='Vocal' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'A', 'Amperio', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='A' and definicion='Amperio' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'B', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='B' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'B', 'Boro', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='B' and definicion='Boro' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'C', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='C' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'C', '100 en N.Romano', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='C' and definicion='100 en N.Romano' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'C', 'Carbono', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='C' and definicion='Carbono' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'C', 'Columbio', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='C' and definicion='Columbio' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'C', 'Constante de la Velocidad de la Luz', 'DIFICL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='C' and definicion='Constante de la Velocidad de la Luz' and dificultad='DIFICL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'D', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='D' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'D', '500 en N.Romano', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='D' and definicion='500 en N.Romano' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'E', 'Vocal', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='E' and definicion='Vocal' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'E', 'Base número neperiano', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='E' and definicion='Base número neperiano' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'E', 'Y', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='E' and definicion='Y' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'E', 'Este', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='E' and definicion='Este' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'E', 'Electrón', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='E' and definicion='Electrón' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'E', 'Energía', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='E' and definicion='Energía' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'F', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='F' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'F', 'Fuerza', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='F' and definicion='Fuerza' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'F', 'Grado Fahrenheit', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='F' and definicion='Grado Fahrenheit' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'F', 'Faradio', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='F' and definicion='Faradio' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'F', 'Flúor', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='F' and definicion='Flúor' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'F', 'Constante de Faraday', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='F' and definicion='Constante de Faraday' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'G', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='G' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'G', 'Gramo', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='G' and definicion='Gramo' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'G', 'Constante de Gravitación Universal', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='G' and definicion='Constante de Gravitación Universal' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'H', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='H' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'H', 'Hidrógeno', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='H' and definicion='Hidrógeno' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'H', 'Constante de Planck', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='H' and definicion='Constante de Planck' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'I', 'Vocal', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='I' and definicion='Vocal' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'I', '1 en N.Romano', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='I' and definicion='1 en N.Romano' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'I', 'Nº Imaginario', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='I' and definicion='Nº Imaginario' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'I', 'Intensidad', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='I' and definicion='Intensidad' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'I', 'Yodo', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='I' and definicion='Yodo' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'J', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='J' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'J', 'Julio', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='J' and definicion='Julio' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'K', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='K' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'K', 'Kilo', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='K' and definicion='Kilo' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'K', 'Kelvin', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='K' and definicion='Kelvin' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'K', 'Potasio', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='K' and definicion='Potasio' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'K', 'Constante de Coulomb', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='K' and definicion='Constante de Coulomb' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'K', 'Constante de Boltzmann', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='K' and definicion='Constante de Boltzmann' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'L', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='L' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'L', '50 en N.Romano', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='L' and definicion='50 en N.Romano' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'L', 'Longitud', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='L' and definicion='Longitud' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'M', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='M' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'M', 'Masa', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='M' and definicion='Masa' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'M', 'Metro', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='M' and definicion='Metro' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'M', 'Minuto', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='M' and definicion='Minuto' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'M', '1000 en N.Romano', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='M' and definicion='1000 en N.Romano' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'N', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='N' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'N', 'Norte', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='N' and definicion='Norte' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'N', 'Nitrógeno', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='N' and definicion='Nitrógeno' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'N', 'Mol', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='N' and definicion='Mol' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'N', 'Newton', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='N' and definicion='Newton' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'N', 'Número indeterminado', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='N' and definicion='Número indeterminado' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'Ñ', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='Ñ' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'O', 'Vocal', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='O' and definicion='Vocal' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'O', 'Oeste', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='O' and definicion='Oeste' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'O', 'Proposición', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='O' and definicion='Proposición' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'O', 'Oxígeno', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='O' and definicion='Oxígeno' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'P', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='P' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'P', 'Peso', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='P' and definicion='Peso' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'P', 'Fósforo', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='P' and definicion='Fósforo' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'Q', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='Q' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'R', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='R' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'R', 'Radio', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='R' and definicion='Radio' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'S', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='S' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'S', 'Segundo', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='S' and definicion='Segundo' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'S', 'Sur', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='S' and definicion='Sur' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'S', 'Siemens', 'DIFICL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='S' and definicion='Siemens' and dificultad='DIFICL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'S', 'Azufre', 'DIFICL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='S' and definicion='Azufre' and dificultad='DIFICL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'T', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='T' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'T', 'Tiempo', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='T' and definicion='Tiempo' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'T', 'Temperatura', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='T' and definicion='Temperatura' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'T', 'Tesla', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='T' and definicion='Tesla' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'U', 'Vocal', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='U' and definicion='Vocal' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'V', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='V' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'V', '5 en N.Romano', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='V' and definicion='5 en N.Romano' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'V', 'Velocidad', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='V' and definicion='Velocidad' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'V', 'Voltio', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='V' and definicion='Voltio' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'V', 'Volumen', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='V' and definicion='Volumen' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'V', 'Vanadio', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='V' and definicion='Vanadio' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'W', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='W' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'W', 'Watio', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='W' and definicion='Watio' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'X', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='X' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'X', 'Incógnita', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='X' and definicion='Incógnita' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'X', 'Marca el lugar', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='X' and definicion='Marca el lugar' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'X', 'Por', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='X' and definicion='Por' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'X', '10 en N.Romano', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='X' and definicion='10 en N.Romano' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'Y', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='Y' and definicion='Consonante' and dificultad='FACIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'Y', 'Conjunción', 'NORMAL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='Y' and definicion='Conjunción' and dificultad='NORMAL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'Y', 'Itrio', 'DIFICIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='Y' and definicion='Itrio' and dificultad='DIFICIL');

INSERT INTO palabra (palabra, definicion, dificultad) select 'Z', 'Consonante', 'FACIL' 
WHERE NOT EXISTS (select 1 from palabra where palabra='Z' and definicion='Consonante' and dificultad='FACIL');

