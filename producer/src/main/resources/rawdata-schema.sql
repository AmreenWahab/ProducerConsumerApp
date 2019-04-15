DROP TABLE IF EXISTS RawData;

CREATE TABLE RawData(
  id INT(25) NOT NULL,
  target TEXT(10) NOT NULL,
  data TEXT(50) NULL,
  processed varchar(1) NOT NULL DEFAULT 'N'
)