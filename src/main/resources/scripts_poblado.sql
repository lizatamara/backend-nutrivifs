-- Datos base de la tienda (alternativa manual).
-- El backend ya los carga solo al iniciar (config/DatosIniciales.java) cuando las tablas están vacías.
-- Usar este script SOLO con las tablas recién creadas y vacías, para no duplicar datos.
-- Roles y usuarios no van aquí: sus contraseñas se cifran con BCrypt desde Java.
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
('Amoxibay 250mg', 'Amoxicilina — Blíster 10 comp.', 'Perro / Gato', 4200, 45, 20, '/img/Amoxibay.png', 1),
('Enrox 50mg', 'Enrofloxacino — Blíster 10 comp.', 'Perro / Gato', 6800, 30, 20, '/img/enrrox.jpg', 1),
('Metrobay 250mg', 'Metronidazol — Blíster 10 comp.', 'Perro / Gato', 3900, 28, 20, '/img/Metrobay.jpg', 1),
('Nexgard', 'Afoxolaner — Masticable 1 unid.', 'Perro', 9500, 60, 20, '/img/Nexgard.jpg', 2),
('Bravecto', 'Fluralaner — Masticable 1 unid.', 'Perro', 18900, 40, 20, '/img/Bravecto.jpg', 2),
('Revolution Plus', 'Selamectina + Sarolaner — Pipeta 1 unid.', 'Gato', 14500, 35, 20, '/img/RevolutionPlus.jpg', 2),
('Drontal Plus', 'Praziquantel + Pamoato — Comprimido 1 unid.', 'Perro', 3200, 80, 20, '/img/DrontalPlus.jpg', 2),
('Milbemax Gato', 'Milbemicina + Praziq. — Comprimido 2 unid.', 'Gato', 6800, 50, 20, '/img/MilbemaxGato.jpg', 2),
('Meloxicam 1mg', 'Meloxicam — Blíster 10 comp.', 'Perro / Gato', 4500, 55, 20, '/img/Metrobay.jpg', 3),
('Carprofen 50mg', 'Carprofeno — Blíster 10 comp.', 'Perro', 9800, 30, 20, '/img/apoquel.jpg', 3),
('Clorhexidina shampoo', 'Clorhexidina 2% — Frasco 250ml', 'Perro / Gato', 8900, 25, 20, '/img/Omeprazol.jpg', 4),
('Malaseb shampoo', 'Miconazol + Clorhex. — Frasco 250ml', 'Perro / Gato', 12500, 20, 20, '/img/Tramadol.jpg', 4),
('Apoquel 16mg', 'Oclacitinib — Blíster 10 comp.', 'Perro', 22000, 18, 20, '/img/apoquel.jpg', 4),
('Probifor', 'Bacillus clausii — Sobre 5ml x10', 'Perro / Gato', 5600, 40, 20, '/img/probifor.jpg', 5),
('Omeprazol 10mg vet', 'Omeprazol — Blíster 10 comp.', 'Perro / Gato', 3800, 35, 20, '/img/Omeprazol.jpg', 5),
('Vetmedin 2.5mg', 'Pimobendan — Blíster 10 comp.', 'Perro', 28000, 15, 20, '/img/Vetmedin.jpg', 6),
('Tramadol 50mg vet', 'Tramadol — Blíster 10 comp.', 'Perro', 5200, 22, 20, '/img/Tramadol.jpg', 7),
('Nobivac DHPPi', 'Vacuna polivalente — Vial 1 dosis', 'Perro', 8500, 48, 20, '/img/NobivacDHPPi.jpg', 8),
('Nobivac Rabies', 'Vacuna antirrábica — Vial 1 dosis', 'Perro / Gato', 5800, 60, 20, '/img/NobivacRabies.jpg', 8),
('Felocell CVR', 'Vacuna triple felina — Vial 1 dosis', 'Gato', 7200, 36, 20, '/img/FelocellCVR.jpg', 8),
('Omega vet 3-6-9', 'Ácidos grasos omega — Frasco 100ml', 'Perro / Gato', 9900, 30, 20, '/img/Omegavet%203-6-9.jpg', 9),
('Condrovet forte', 'Condroitín + Glucos. — Blíster 30 comp.', 'Perro', 14500, 25, 20, '/img/Condrovetforte.jpg', 9);

INSERT INTO servicios
(nombre, descripcion, especie, duracion, observacion, precio, consulta, image, alt, enlace, id_categoria)
VALUES
('Consulta general', 'Revisión médica completa para evaluar el estado de salud de tu mascota.', 'Perro / Gato', 30, 'Atención veterinaria general.', 15000, 'Consulta general', '/img/consulta-general.jpg', 'Consulta general veterinaria', FALSE, 1),
('Consulta de urgencia', 'Atención veterinaria para situaciones que requieren atención inmediata.', 'Perro / Gato', 30, 'Disponible 24/7, sin hora previa.', 25000, 'Consulta de urgencia', '/img/consulta-urgencia.jpg', 'Consulta de urgencia veterinaria', FALSE, 1),
('Control postoperatorio', 'Seguimiento médico después de una intervención quirúrgica.', 'Perro / Gato', 20, 'Control y seguimiento de recuperación.', 10000, 'Control postoperatorio', '/img/Control-postoperatorio.jpg', 'Control postoperatorio', FALSE, 1),
('Consulta ave / conejo', 'Atención veterinaria especializada para aves y conejos.', 'Ave / Conejo', 30, 'Atención para animales pequeños.', 18000, 'Consulta ave / conejo', '/img/Consulta-conejo.jpg', 'Consulta para aves y conejos', FALSE, 1),
('Segunda opinión médica', 'Evaluación adicional de un diagnóstico o tratamiento veterinario.', 'Todas', 40, 'Requiere ficha previa.', 20000, 'Segunda opinión médica', '/img/evaluacion.jpg', 'Segunda opinión médica', FALSE, 1),
('Vacuna antirrábica canina', 'Vacunación contra la rabia para perros.', 'Perro', 10, 'Obligatoria por ley.', 12000, 'antirrabica-canina', '/img/Vacuna-antirrábica.png', 'Vacuna antirrábica canina', TRUE, 2),
('Vacuna séxtuple canina', 'Protección preventiva contra diferentes enfermedades caninas.', 'Perro', 10, 'Refuerzo anual.', 18000, 'sextuple-canina', '/img/Vacuna-séxtuple.jpg', 'Vacuna séxtuple canina', TRUE, 2),
('Vacuna bivalente felina', 'Protección preventiva para gatos.', 'Gato', 10, 'Refuerzo anual.', 15000, 'bivalente-felina', '/img/Vacuna-bivalente.jpg', 'Vacuna bivalente felina', TRUE, 2),
('Vacuna triple felina', 'Protección preventiva mediante vacunación para gatos.', 'Gato', 10, 'Refuerzo anual.', 17000, 'triple-felina', '/img/Vacuna-triple.jpg', 'Vacuna triple felina', TRUE, 2),
('Vacuna Bordetella canina', 'Prevención de enfermedades respiratorias en perros.', 'Perro', 10, 'Tos de las perreras.', 14000, 'bordetella-canina', '/img/Vacuna-Bordetella.jpg', 'Vacuna Bordetella canina', TRUE, 2),
('Vacuna antirrábica felina', 'Vacunación contra la rabia para gatos.', 'Gato', 10, 'Protección preventiva.', 12000, 'antirrabica-felina', '/img/antirrabica.png', 'Vacuna antirrábica felina', TRUE, 2),
('Esterilización hembra canina', 'Procedimiento de esterilización para hembras caninas.', 'Perra', 90, 'Incluye anestesia y hospitalización 24h.', 80000, 'Esterilización hembra canina', '/img/Esterilización-hembra.jpg', 'Esterilización hembra canina', FALSE, 3),
('Esterilización macho canino', 'Procedimiento de esterilización para perros machos.', 'Perro', 60, 'Incluye anestesia.', 60000, 'Esterilización macho canino', '/img/esterilizacion-perro.jpg', 'Esterilización macho canino', FALSE, 3),
('Esterilización hembra felina', 'Procedimiento de esterilización para gatas.', 'Gata', 60, 'Incluye anestesia y hospitalización 12h.', 65000, 'Esterilización hembra felina', '/img/castracion-gato.jpg', 'Esterilización hembra felina', FALSE, 3),
('Esterilización macho felino', 'Procedimiento de esterilización para gatos machos.', 'Gato', 45, 'Incluye anestesia.', 50000, 'Esterilización macho felino', '/img/Esterilización-gatoHjpg.jpg', 'Esterilización macho felino', FALSE, 3),
('Extirpación de tumor cutáneo', 'Procedimiento quirúrgico para retirar tumores cutáneos.', 'Perro / Gato', 60, 'Precio referencial; varía según tamaño.', 120000, 'Extirpación de tumor cutáneo', '/img/extirpacion_cancer.jpg', 'Extirpación de tumor cutáneo', FALSE, 3),
('Cesárea de urgencia', 'Procedimiento quirúrgico de emergencia para hembras gestantes.', 'Perra / Gata', 120, 'Atención de urgencia.', 180000, 'Cesárea de urgencia', '/img/cesarea.jpg', 'Cesárea de urgencia', FALSE, 3),
('Desparasitación interna pequeños', 'Tratamiento antiparasitario para perros pequeños de menos de 10 kg.', 'Perro', 5, 'Menos de 10 kg.', 8000, 'Desparasitación interna pequeños', '/img/DrontalPlus.jpg', 'Desparasitación interna pequeños', FALSE, 4),
('Desparasitación interna medianos', 'Tratamiento antiparasitario para perros de peso mediano.', 'Perro', 5, 'Entre 10 y 25 kg.', 9500, 'Desparasitación interna medianos', '/img/DrontalPlus.jpg', 'Desparasitación interna medianos', FALSE, 4),
('Desparasitación interna grandes', 'Tratamiento antiparasitario para perros de gran tamaño.', 'Perro', 5, 'Más de 25 kg.', 11000, 'Desparasitación interna grandes', '/img/DrontalPlus.jpg', 'Desparasitación interna grandes', FALSE, 4),
('Desparasitación interna felina', 'Tratamiento antiparasitario interno para gatos.', 'Gato', 5, 'Aplicación según peso.', 8000, 'Desparasitación interna felina', '/img/DrontalPlus.jpg', 'Desparasitación interna felina', FALSE, 4),
('Antiparasitario externo', 'Aplicación de pipeta para el control de parásitos externos.', 'Perro / Gato', 5, 'Incluye aplicación.', 7500, 'Antiparasitario externo (pipeta)', '/img/Bravecto.jpg', 'Antiparasitario externo', FALSE, 4),
('Hemograma completo', 'Análisis de sangre para evaluar el estado general de salud.', 'Perro / Gato', 30, 'Resultado en 24-48 h.', 22000, 'Hemograma completo', '/img/hemograma.jpg', 'Hemograma completo', FALSE, 5),
('Perfil bioquímico completo', 'Evaluación de parámetros bioquímicos importantes para la salud.', 'Perro / Gato', 30, 'Resultado en 24-48 h.', 35000, 'Perfil bioquímico completo', '/img/perfill-bioquimico.jpg', 'Perfil bioquímico completo', FALSE, 5),
('Radiografía', 'Estudio radiográfico para apoyar el diagnóstico veterinario.', 'Perro / Gato', 20, '1 proyección.', 28000, 'Radiografía (1 proyección)', '/img/Radiografía.jpg', 'Radiografía', FALSE, 5),
('Ecografía abdominal', 'Evaluación mediante imagen de órganos y estructuras abdominales.', 'Perro / Gato', 30, 'Examen abdominal.', 45000, 'Ecografía abdominal', '/img/ecografia.jpg', 'Ecografía abdominal', FALSE, 5),
('Test de leishmaniasis', 'Prueba diagnóstica para detectar leishmaniasis en perros.', 'Perro', 15, 'Prueba rápida.', 18000, 'Test de leishmaniasis', '/img/leishmaniasis.jpg', 'Test de leishmaniasis', FALSE, 5);
