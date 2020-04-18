CREATE SEQUENCE virus_reporting_codes_id_sequence;
CREATE TABLE virus_reporting_codes (
   id BIGINT PRIMARY KEY,
   date_time_used TIMESTAMP,
   date_time_tested TIMESTAMP,
   code VARCHAR (50) UNIQUE
);

