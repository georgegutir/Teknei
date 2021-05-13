CREATE TABLE Marcas(id BIGINT(10) PRIMARY KEY AUTO_INCREMENT,
marca VARCHAR(45) NOT NULL);

CREATE TABLE Coches(id BIGINT(10) PRIMARY KEY AUTO_INCREMENT,
modelo VARCHAR(45) NOT NULL,
matricula CHAR(8) NOT NULL,
marca_id BIGINT(10) NOT NULL,
FOREIGN KEY (marca_id) REFERENCES Marcas(id));

INSERT INTO Marcas(marca) Values ('Seat');
INSERT INTO Marcas(marca) Values ('Renault');
INSERT INTO Marcas(marca) Values ('Citroen');

INSERT INTO Coches(modelo,matricula,marca_id) Values ('Berlingo','5426-CGD',3);
INSERT INTO Coches(modelo,matricula,marca_id) Values ('Ibiza','7345-HEV',1);
INSERT INTO Coches(modelo,matricula,marca_id) Values ('Alhambra','4165-AGV',1);
INSERT INTO Coches(modelo,matricula,marca_id) Values ('Clio','3642-DUH',2);
INSERT INTO Coches(modelo,matricula,marca_id) Values ('C4','2976-NBF',3);
INSERT INTO Coches(modelo,matricula,marca_id) Values ('Megane','2853-LEV',2);