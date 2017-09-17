CREATE TABLE diskcategory (
	diskcategory_id INTEGER NOT NULL,
	diskcategory_name CHARACTER VARYING(100) NOT NULL,
	CONSTRAINT PK_DISKCATEGORY_ID PRIMARY KEY (diskcategory_id)
);

ALTER TABLE diskcategory OWNER TO postgres;

CREATE TABLE disk (
	disk_id SERIAL NOT NULL,
	disk_reference CHARACTER VARYING(100) NOT NULL,
	disk_author CHARACTER VARYING(100) NOT NULL,
	disk_title CHARACTER VARYING(100) NOT NULL,
	disk_diskcategory_id INTEGER NOT NULL,
	disk_price REAL NOT NULL,
	disk_number_of_songs INTEGER NOT NULL,
	CONSTRAINT PK_DISK_ID PRIMARY KEY (disk_id),
	CONSTRAINT FK_DISK_DISKCATEGORY FOREIGN KEY (disk_diskcategory_id)
	  REFERENCES diskcategory (diskcategory_id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT
);

ALTER TABLE disk OWNER TO postgres;

CREATE UNIQUE INDEX UI_DISK_REFERENCE ON disk USING btree (disk_reference);