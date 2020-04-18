CREATE SEQUENCE users_id_sequence;
CREATE TABLE users (
id BIGINT PRIMARY KEY ,
major BIGINT NOT NULL,
minor BIGINT NOT NULL,
virus_report_id BIGINT,
UNIQUE (major, minor)
);

CREATE SEQUENCE virus_reports_id_sequence;
CREATE TABLE virus_reports (
id BIGINT PRIMARY KEY ,
date_time_created TIMESTAMP NOT NULL,
date_time_tested TIMESTAMP
);

ALTER TABLE users
ADD FOREIGN KEY (virus_report_id) REFERENCES virus_reports(id);

CREATE SEQUENCE meet_events_id_sequence;
CREATE TABLE meet_events (
id BIGINT PRIMARY KEY ,
user_id BIGINT NOT NULL,
seen_user_id BIGINT NOT NULL,
start_time TIMESTAMP NOT NULL,
end_time TIMESTAMP NOT NULL
);
ALTER TABLE meet_events
ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE meet_events
ADD FOREIGN KEY (seen_user_id) REFERENCES users(id);