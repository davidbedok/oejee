GRANT SELECT, INSERT, UPDATE, DELETE ON magazinecategory, magazine TO magazine_role;
GRANT USAGE, SELECT, UPDATE ON magazine_magazine_id_seq TO magazine_role;

GRANT SELECT, INSERT, UPDATE, DELETE ON appuser, role, userrole TO magazine_role;
GRANT USAGE, SELECT, UPDATE ON appuser_appuser_id_seq, role_role_id_seq, userrole_userrole_id_seq TO magazine_role;
