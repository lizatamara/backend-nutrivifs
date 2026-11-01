USE nutrivifs;

INSERT INTO categorias_productos (nombre) VALUES
('Antibióticos'),
('Antiparasitarios'),
('Antiinflamatorios'),
('Dermatología'),
('Digestivo'),
('Cardíaco'),
('Analgésicos'),
('Vacunas'),
('Suplementos');


INSERT INTO categorias_servicios (nombre) VALUES
('Consultas'),
('Vacunación'),
('Cirugías'),
('Desparasitación'),
('Exámenes');


INSERT INTO productos
(nombre, descripcion, especies, precio, stock, stock_critico, image, id_categoria)
VALUES
('Amoxibay 250mg', 'Amoxicilina - Blíster 10 comp.', 'Perro / Gato', 4200, 45, 20, NULL, 1),
('Enrox 50mg', 'Enrofloxacino - Blíster 10 comp.', 'Perro / Gato', 6800, 30, 20, NULL, 1),
('Metrobay 250mg', 'Metronidazol - Blíster 10 comp.', 'Perro / Gato', 3900, 28, 20, NULL, 1),
('Nexgard', 'Afoxolaner - Masticable 1 unid.', 'Perro', 9500, 60, 20, NULL, 2),
('Bravecto', 'Fluralaner - Masticable 1 unid.', 'Perro', 18900, 40, 20, NULL, 2),
('Revolution Plus', 'Selamectina + Sarolaner - Pipeta 1 unid.', 'Gato', 14500, 35, 20, NULL, 2),
('Drontal Plus', 'Praziquantel + Pamoato - Comprimido 1 unid.', 'Perro', 3200, 80, 20, NULL, 2),
('Milbemax Gato', 'Milbemicina + Praziquantel - Comprimido 2 unid.', 'Gato', 6800, 50, 20, NULL, 2),
('Meloxicam 1mg', 'Meloxicam - Blíster 10 comp.', 'Perro / Gato', 4500, 55, 20, NULL, 3),
('Carprofen 50mg', 'Carprofeno - Blíster 10 comp.', 'Perro', 9800, 30, 20, NULL, 3),
('Clorhexidina shampoo', 'Clorhexidina 2% - Frasco 250ml', 'Perro / Gato', 8900, 25, 20, NULL, 4),
('Malaseb shampoo', 'Miconazol + Clorhexidina - Frasco 250ml', 'Perro / Gato', 12500, 20, 20, NULL, 4),
('Apoquel 16mg', 'Oclacitinib - Blíster 10 comp.', 'Perro', 22000, 18, 20, NULL, 4),
('Probifor', 'Bacillus clausii - Sobre 5ml x10', 'Perro / Gato', 5600, 40, 20, NULL, 5),
('Omeprazol 10mg vet', 'Omeprazol - Blíster 10 comp.', 'Perro / Gato', 3800, 35, 20, NULL, 5),
('Vetmedin 2.5mg', 'Pimobendan - Blíster 10 comp.', 'Perro', 28000, 15, 20, NULL, 6),
('Tramadol 50mg vet', 'Tramadol - Blíster 10 comp.', 'Perro', 5200, 22, 20, NULL, 7),
('Nobivac DHPPi', 'Vacuna polivalente - Vial 1 dosis', 'Perro', 8500, 48, 20, NULL, 8),
('Nobivac Rabies', 'Vacuna antirrábica - Vial 1 dosis', 'Perro / Gato', 5800, 60, 20, NULL, 8),
('Felocell CVR', 'Vacuna triple felina - Vial 1 dosis', 'Gato', 7200, 36, 20, NULL, 8),
('Omega vet 3-6-9', 'Ácidos grasos omega - Frasco 100ml', 'Perro / Gato', 9900, 30, 20, NULL, 9),
('Condrovet forte', 'Condroitín + Glucosamina - Blíster 30 comp.', 'Perro', 14500, 25, 20, NULL, 9);


INSERT INTO servicios
(nombre, descripcion, especie, duracion, observacion, precio, image, alt, enlace, id_categoria)
VALUES
('Consulta general', 'Revisión médica completa para evaluar el estado de salud de tu mascota.', 'Perro / Gato', 30, 'Atención veterinaria general.', 15000, NULL, 'Consulta general veterinaria', FALSE, 1),
('Consulta de urgencia', 'Atención veterinaria para situaciones que requieren atención inmediata.', 'Perro / Gato', 30, 'Disponible 24/7, sin hora previa.', 25000, NULL, 'Consulta de urgencia veterinaria', FALSE, 1),
('Control postoperatorio', 'Seguimiento médico después de una intervención quirúrgica.', 'Perro / Gato', 20, 'Control y seguimiento de recuperación.', 10000, NULL, 'Control postoperatorio', FALSE, 1),
('Consulta ave / conejo', 'Atención veterinaria especializada para aves y conejos.', 'Ave / Conejo', 30, 'Atención para animales pequeños.', 18000, NULL, 'Consulta para aves y conejos', FALSE, 1),
('Segunda opinión médica', 'Evaluación adicional de un diagnóstico o tratamiento veterinario.', 'Todas', 40, 'Requiere ficha previa.', 20000, NULL, 'Segunda opinión médica', FALSE, 1),
('Vacuna antirrábica canina', 'Vacunación contra la rabia para perros.', 'Perro', 10, 'Obligatoria por ley.', 12000, NULL, 'Vacuna antirrábica canina', TRUE, 2),
('Vacuna séxtuple canina', 'Protección preventiva contra diferentes enfermedades caninas.', 'Perro', 10, 'Refuerzo anual.', 18000, NULL, 'Vacuna séxtuple canina', TRUE, 2),
('Vacuna bivalente felina', 'Protección preventiva para gatos.', 'Gato', 10, 'Refuerzo anual.', 15000, NULL, 'Vacuna bivalente felina', TRUE, 2),
('Vacuna triple felina', 'Protección preventiva mediante vacunación para gatos.', 'Gato', 10, 'Refuerzo anual.', 17000, NULL, 'Vacuna triple felina', TRUE, 2),
('Vacuna Bordetella canina', 'Prevención de enfermedades respiratorias en perros.', 'Perro', 10, 'Tos de las perreras.', 14000, NULL, 'Vacuna Bordet