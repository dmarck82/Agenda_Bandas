CREATE TABLE usuario (
	codigo BIGINT(20) PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao (
	codigo BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao (
	codigo_usuario BIGINT(20) NOT NULL,
	codigo_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (codigo_usuario, codigo_permissao),
	FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo),
	FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (codigo, nome, email, senha) values (1, 'Administrador', 'admin@tdsapi.com', '$2a$10$BrrvQGkWOB5T1FsNC.nBGODTK16j1LP2ux/zSr.MOjb.ujgjpX0na');
INSERT INTO usuario (codigo, nome, email, senha) values (2, 'ArubaCafe', 'aruba@tdsapi.com', '$2a$10$YP5O4A6PEfal3ZK.Dtyc5.SpFLphuEcZ038dlZ23Z0p89YkdHQjrm');
INSERT INTO usuario (codigo, nome, email, senha) values (3, 'OrionLounge', 'orion@tdsapi.com', '$2a$10$W2sRzc/DVPidzPpSD5LXtOQda.W/y9kocGG/P.vCBjHBPBE/x0Ix6');
INSERT INTO usuario (codigo, nome, email, senha) values (4, 'Danilo', 'danilo@tdsapi.com', '$2a$10$yhzUAoZ3wgYcgAz3MLSEteA380.SlrRLBb1bgHMQVA1cvQqNdlL8y');
INSERT INTO usuario (codigo, nome, email, senha) values (5, 'Joaquim', 'joaquim@tdsapi.com', '$2a$10$KM6Co/L1mRPWpEvwuk5ffOWpz6HiGO6cWI.P9IEMn.VFzqQ1VbBPC');
INSERT INTO usuario (codigo, nome, email, senha) values (6, 'Douglas', 'douglas@tdsapi.com', '$2a$10$RlN4XrL5uniVg3ozWz3hROm2DLM76rkvQG6ErzUh5NobAUYjuqFbq');
INSERT INTO usuario (codigo, nome, email, senha) values (7, 'AcademicosDoSamba', 'academicosdosamba@tdsapi.com', '$2a$10$1T9jqtHC04YbKiq2YrQdEe5J2KJbGSOsVwEm.SNOGc75gmPfqHsJe');
INSERT INTO usuario (codigo, nome, email, senha) values (8, 'CavaleirosDoForro', 'cavaleirosdoforro@tdsapi.com', '$2a$10$4vxvrWypqJVjcC1QkqNIBulEyVn.fRLQY8qX31CBgO.LqUHVRhchu');
INSERT INTO usuario (codigo, nome, email, senha) values (9, 'OsSemTalento', 'ossemtalento@tdsapi.com', '$2a$10$JP0QQ7jSS6AeXQP4ZB/BKeidYMMtQOvrRVNZnw3UOZtHzaia84Vqu');


INSERT INTO permissao (codigo, descricao) values (1, 'ROLE_CADASTRAR_CASA_DE_SHOW');
INSERT INTO permissao (codigo, descricao) values (2, 'ROLE_REMOVER_CASA_DE_SHOW');
INSERT INTO permissao (codigo, descricao) values (3, 'ROLE_PESQUISAR_CASA_DE_SHOW');

INSERT INTO permissao (codigo, descricao) values (4, 'ROLE_CADASTRAR_INTEGRANTE');
INSERT INTO permissao (codigo, descricao) values (5, 'ROLE_REMOVER_INTEGRANTE');
INSERT INTO permissao (codigo, descricao) values (6, 'ROLE_PESQUISAR_INTEGRANTE');

INSERT INTO permissao (codigo, descricao) values (7, 'ROLE_CADASTRAR_BANDA');
INSERT INTO permissao (codigo, descricao) values (8, 'ROLE_REMOVER_BANDA');
INSERT INTO permissao (codigo, descricao) values (9, 'ROLE_PESQUISAR_BANDA');

INSERT INTO permissao (codigo, descricao) values (10, 'ROLE_CADASTRAR_AGENDA_DE_SHOW');
INSERT INTO permissao (codigo, descricao) values (11, 'ROLE_REMOVER_AGENDA_DE_SHOW');
INSERT INTO permissao (codigo, descricao) values (12, 'ROLE_PESQUISAR_AGENDA_DE_SHOW');

-- admin
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 1);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 4);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 6);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 7);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 8);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 9);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 10);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 11);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 12);

-- ArubaCafe
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 9);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 10);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 11);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 12);

-- OrionLounge
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (3, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (3, 9);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (3, 10);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (3, 11);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (3, 12);

-- danilo
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (4, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (4, 6);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (4, 9);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (4, 12);

-- joaquim
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (5, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (5, 6);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (5, 9);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (5, 12);

-- douglas
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (6, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (6, 6);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (6, 9);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (6, 12);

-- AcademicosDoSamba
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (7, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (7, 4);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (7, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (7, 6);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (7, 9);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (7, 12);

-- CavaleirosDoForro
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (8, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (8, 4);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (8, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (8, 6);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (8, 9);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (8, 12);

-- OsSemTalento
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (9, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (9, 4);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (9, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (9, 6);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (9, 9);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (9, 12);