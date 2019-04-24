-- ------------------------------------------------------------------------------
-- - Reconstruction de la base de données                                     ---
-- ------------------------------------------------------------------------------
DROP DATABASE IF EXISTS Bank;
CREATE DATABASE Bank;
USE Bank;

-- -----------------------------------------------------------------------------
-- - Construction de la table des clients                               ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Customers (
	IdCust				int(4)		PRIMARY KEY AUTO_INCREMENT,
	Name				varchar(20)	NOT NULL,
	FirstName			varchar(20)	NOT NULL
) ENGINE = InnoDB;

--INSERT INTO T_Customers (IdCust, Name, FirstName) VALUES ( 1, 'Dupont' , 'henri' );
--INSERT INTO T_Customers (IdCust, Name, FirstName) VALUES ( 2, 'Durand' , 'robert' );

--SELECT * FROM T_Customers;

-- -----------------------------------------------------------------------------
-- - Construction de la table des comptes                            ---
-- -----------------------------------------------------------------------------

CREATE TABLE T_Accounts (
	NumAct				int(4)		PRIMARY KEY AUTO_INCREMENT,
	DateCreation		datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Balance				float(4)	NOT NULL DEFAULT 0,
	TypeAccount			varchar(20)	NOT NULL,
	Decouvert			float(4)	NOT NULL DEFAULT 0,	
	TauxEpargne			float(4)	NOT NULL DEFAULT 0,
	IdCust				int(4)		NOT NULL REFERENCES T_Customers(IdCust)
) ENGINE = InnoDB;

--insert into T_Accounts (NumAct,DateCreation,Balance,TypeAccount,Decouvert,IdCust)  values(1,now(),500,'Courant',250, 1);
--insert into T_Accounts (NumAct,DateCreation,Balance,TypeAccount,TauxEpargne,IdCust)  values(2,now(),1500,'Epargne',5.5, 1);
--insert into T_Accounts (NumAct,DateCreation,Balance,TypeAccount,Decouvert,IdCust)  values(3,now(),5500,'Courant',400, 2);

--SELECT * FROM T_Accounts;

-- -----------------------------------------------------------------------------
-- - Construction de la tables des opérations		                       ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Operations (
	NumOp			int(4)		PRIMARY KEY AUTO_INCREMENT,
	Amount			float(4)	NOT NULL,
	DateOperation	datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
	TypeOperation	varchar(20)	NOT NULL,
	NumAct			int(4)		NOT NULL REFERENCES T_Accounts(NumAct)
) ENGINE = InnoDB;

--insert into T_Operations (NumOp, Amount, DateOperation, TypeOperation ,NumAct)  values(1,250,now(),'virement',1);
--insert into T_Operations (NumOp, Amount, DateOperation, TypeOperation ,NumAct)  values(2,150,now(),'retrait',1);
--insert into T_Operations (NumOp, Amount, DateOperation, TypeOperation ,NumAct)  values(3,3000,now(),'virement',2);	

--SELECT * FROM T_Operations;


-- -----------------------------------------------------------------------------
-- - Construction de la table des utilisateurs                               ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Users (
	IdUser				int(4)		PRIMARY KEY AUTO_INCREMENT,
	Login				varchar(20)	NOT NULL,
	Password			varchar(20)	NOT NULL,
	ConnectionNumber		int(4)		NOT NULL DEFAULT 0
) ENGINE = InnoDB;

INSERT INTO T_Users (IdUser, Login, Password) VALUES ( 1, 'El babili' ,	'mohamed' );
INSERT INTO T_Users (IdUser, Login, Password) VALUES ( 2, 'Cerveau',	'Allan' );

-- -----------------------------------------------------------------------------
-- - Construction de la table des administrateurs                            ---
-- -----------------------------------------------------------------------------

CREATE TABLE T_Admins (
	IdUser				int(4)			NOT NULL REFERENCES T_Users(IdUser),
	Rights				varchar(10) 	NOT NULL
) ENGINE = InnoDB;

INSERT INTO T_Admins VALUES ( 1, "-VR-----" );
