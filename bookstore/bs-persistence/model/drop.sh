#!/bin/sh

psql -d bookstoredb -h localhost -p 5432 -U postgres -f drop-schema.sql
psql -d postgres -h localhost -p 5432 -U postgres -f drop-user.sql
psql -d postgres -h localhost -p 5432 -U postgres -f drop-role.sql
psql -d postgres -h localhost -p 5432 -U postgres -f drop-database.sql