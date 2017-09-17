CREATE TABLE magazinecategory (
	magazinecategory_id INTEGER NOT NULL,
	magazinecategory_name CHARACTER VARYING(100) NOT NULL,
	CONSTRAINT PK_MAGAZINECATEGORY_ID PRIMARY KEY (magazinecategory_id)
);

ALTER TABLE magazinecategory OWNER TO postgres;

CREATE TABLE magazine (
	magazine_id SERIAL NOT NULL,
	magazine_reference CHARACTER VARYING(100) NOT NULL,
	magazine_publisher CHARACTER VARYING(100) NOT NULL,
	magazine_title CHARACTER VARYING(100) NOT NULL,
	magazine_magazinecategory_id INTEGER NOT NULL,
	magazine_price REAL NOT NULL,
	magazine_number_of_pages INTEGER NOT NULL,
	CONSTRAINT PK_MAGAZINE_ID PRIMARY KEY (magazine_id),
	CONSTRAINT FK_MAGAZINE_MAGAZINECATEGORY FOREIGN KEY (magazine_magazinecategory_id)
	  REFERENCES magazinecategory (magazinecategory_id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT
);

ALTER TABLE magazine OWNER TO postgres;

CREATE UNIQUE INDEX UI_MAGAZINE_REFERENCE ON magazine USING btree (magazine_reference);

CREATE TABLE appuser (
	appuser_id SERIAL NOT NULL,
	appuser_name CHARACTER VARYING(100) NOT NULL,
	appuser_password CHARACTER VARYING(100) NOT NULL,
	CONSTRAINT PK_APPUSER_ID PRIMARY KEY (appuser_id) 
);

ALTER TABLE appuser OWNER TO postgres;

CREATE UNIQUE INDEX UI_APPUSER_NAME ON appuser USING btree (appuser_name);

CREATE TABLE role (
	role_id SERIAL NOT NULL,
	role_name CHARACTER VARYING(100) NOT NULL,
	CONSTRAINT PK_ROLE_ID PRIMARY KEY (role_id) 
);

ALTER TABLE role OWNER TO postgres;

CREATE TABLE userrole (
	userrole_id SERIAL NOT NULL,
	userrole_appuser_id INTEGER NOT NULL,
	userrole_role_id INTEGER NOT NULL,
	CONSTRAINT PK_USERROLE_ID PRIMARY KEY (userrole_id),
	CONSTRAINT FK_USERROLE_USER FOREIGN KEY (userrole_appuser_id)
	  REFERENCES appuser (appuser_id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT FK_USERROLE_ROLE FOREIGN KEY (userrole_role_id)
	  REFERENCES role (role_id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT
);

ALTER TABLE userrole OWNER TO postgres;
