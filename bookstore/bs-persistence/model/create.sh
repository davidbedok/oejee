#!/bin/sh

psql -d postgres -h localhost -p 5432 -U postgres -f create-database.sql
psql -d postgres -h localhost -p 5432 -U postgres -f create-role.sql
psql -d postgres -h localhost -p 5432 -U postgres -f create-user.sql
psql -d bookstoredb -h localhost -p 5432 -U postgres -f create-schema.sql
psql -d bookstoredb -h localhost -p 5432 -U postgres -f grant-access.sql
psql -d bookstoredb -h localhost -p 5432 -U postgres -f initial-data.sql